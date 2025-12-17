package com.sssys.grn.framework.redis;

import jakarta.annotation.Resource;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类（基于Redisson + RedisTemplate）
 * 包含：基础增删改查 + 分布式锁（可重入锁、读写锁、公平锁）
 */
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    // ====================== 基础增删改查（String类型） ======================
    /**
     * 设置缓存（永久有效）
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置缓存（带过期时间）
     */
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 获取缓存
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        return value == null ? null : (T) value;
    }

    /**
     * 删除缓存
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 批量删除缓存
     */
    public Long delete(Set<String> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 设置过期时间
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取剩余过期时间
     */
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    /**
     * 判断key是否存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    // ====================== Hash类型操作 ======================
    /**
     * Hash设置值
     */
    public void hset(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * Hash批量设置值
     */
    public void hmset(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * Hash获取值
     */
    @SuppressWarnings("unchecked")
    public <T> T hget(String key, String hashKey) {
        Object value = redisTemplate.opsForHash().get(key, hashKey);
        return value == null ? null : (T) value;
    }

    /**
     * Hash获取所有值
     */
    public Map<Object, Object> hgetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * Hash删除值
     */
    public Long hdel(String key, Object... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    // ====================== List类型操作 ======================
    /**
     * List左侧添加元素
     */
    public Long lpush(String key, Object... values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * List右侧添加元素
     */
    public Long rpush(String key, Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * List获取指定范围元素
     */
    public List<Object> lrange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * List弹出左侧元素
     */
    public Object lpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    // ====================== 分布式锁 ======================
    /**
     * 获取可重入锁（默认30秒过期，避免死锁）
     * @param lockKey 锁名称
     * @return 锁对象（需手动释放，或用try-with-resources）
     */
    public RLock getLock(String lockKey) {
        return redissonClient.getLock(lockKey);
    }

    /**
     * 获取公平锁（按请求顺序获取锁）
     */
    public RLock getFairLock(String lockKey) {
        return redissonClient.getFairLock(lockKey);
    }

    /**
     * 获取读写锁（读共享、写排他）
     */
    public RReadWriteLock getReadWriteLock(String lockKey) {
        return redissonClient.getReadWriteLock(lockKey);
    }

    /**
     * 加锁（阻塞等待，直到获取锁）
     * @param lockKey 锁名称
     * @param leaseTime 锁自动释放时间
     * @param unit 时间单位
     */
    public void lock(String lockKey, long leaseTime, TimeUnit unit) {
        RLock lock = getLock(lockKey);
        lock.lock(leaseTime, unit);
    }

    /**
     * 尝试加锁（非阻塞，指定时间内获取不到则返回false）
     * @param lockKey 锁名称
     * @param waitTime 最大等待时间
     * @param leaseTime 锁自动释放时间
     * @param unit 时间单位
     * @return 是否获取到锁
     */
    public boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit) {
        try {
            RLock lock = getLock(lockKey);
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    /**
     * 释放锁（必须确保是当前线程持有的锁，否则抛异常）
     */
    public void unlock(String lockKey) {
        RLock lock = getLock(lockKey);
        // 只有当前线程持有锁时才释放
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    /**
     * 安全释放锁（避免释放未持有的锁）
     */
    public void safeUnlock(String lockKey) {
        try {
            unlock(lockKey);
        } catch (Exception e) {
            // 记录日志，不抛出异常
            org.slf4j.LoggerFactory.getLogger(RedisUtils.class)
                    .warn("释放锁失败，lockKey:{}", lockKey, e);
        }
    }

    // ====================== 红锁（多Redis实例分布式锁） ======================
    /**
     * 获取红锁（适用于多Redis实例部署，提高锁可靠性）
     * 需提前配置多Redis实例的RedissonClient，此处简化为单实例示例
     */
    public RLock getRedLock(String... lockKeys) {
        RLock[] locks = new RLock[lockKeys.length];
        for (int i = 0; i < lockKeys.length; i++) {
            locks[i] = redissonClient.getLock(lockKeys[i]);
        }
        return redissonClient.getRedLock(locks);
    }
}

