package com.sssys.grn.framework.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Description: 单机Redis通用工具类
 * 整合：1. 基础缓存（Lettuce） 2. 分布式锁（Redisson）
 * @Author: captain
 * @Date: 10:01 2025/12/19
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    // 自定义配置
    @Value("${custom.redis.key-prefix:biz_}")
    private String keyPrefix;
    @Value("${custom.redis.lock-prefix:lock_}")
    private String lockPrefix;
    @Value("${custom.redis.default-expire:3600}")
    private long defaultExpire;
    @Value("${custom.redis.lock-expire:30}")
    private long defaultLockExpire;

    // ========== 基础缓存操作（基于Lettuce） ==========
    /**
     * 拼接业务key前缀
     */
    private String getFullKey(String key) {
        return keyPrefix + key;
    }

    /**
     * 存缓存（默认过期时间）
     */
    public void set(String key, Object value) {
        set(key, value, defaultExpire, TimeUnit.SECONDS);
    }

    /**
     * 存缓存（自定义过期时间）
     */
    public void set(String key, Object value, long expire, TimeUnit unit) {
        redisTemplate.opsForValue().set(getFullKey(key), value, expire, unit);
    }

    /**
     * 获取缓存
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(getFullKey(key));
    }

    /**
     * 删除缓存
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(getFullKey(key));
    }

    /**
     * 原子递增（计数器）
     */
    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(getFullKey(key), delta);
    }

    // ========== 分布式锁操作（基于Redisson） ==========
    /**
     * 拼接锁key前缀
     */
    private String getFullLockKey(String lockKey) {
        return lockPrefix + lockKey;
    }

    /**
     * 获取分布式锁（非阻塞，默认过期时间）
     * @return 锁对象（null=获取失败）
     */
    public RLock tryLock(String lockKey) {
        return tryLock(lockKey, 0, defaultLockExpire, TimeUnit.SECONDS);
    }

    /**
     * 获取分布式锁（自定义等待/过期时间）
     */
    public RLock tryLock(String lockKey, long waitTime, long expireTime, TimeUnit unit) {
        String fullLockKey = getFullLockKey(lockKey);
        RLock lock = redissonClient.getLock(fullLockKey);
        try {
            boolean success = lock.tryLock(waitTime, expireTime, unit);
            return success ? lock : null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }

    /**
     * 释放分布式锁（安全释放，防止释放他人锁）
     */
    public void unlock(RLock lock) {
        if (lock != null && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    /**
     * 加锁执行业务（简化模板：获取锁→执行业务→释放锁）
     * @param lockKey 锁key
     * @param supplier 业务逻辑（返回执行结果）
     * @return 业务结果（null=获取锁失败）
     */
    public <T> T executeWithLock(String lockKey, Supplier<T> supplier) {
        RLock lock = tryLock(lockKey);
        if (lock == null) {
            return null;
        }
        try {
            return supplier.get();
        } finally {
            unlock(lock);
        }
    }
}
