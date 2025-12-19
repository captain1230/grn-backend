import com.sssys.grn.GrnServerApplication;
import com.sssys.grn.framework.redis.RedisUtils;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: captain
 * @Date: 2025/12/19
 */
@SpringBootTest(classes = GrnServerApplication.class)
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    // ========== 测试Lettuce基础缓存 ==========
    @Test
    public void testBasicCache() {
        // 存缓存
        redisUtils.set("user:1001", "张三");
        // 取缓存
        String userName = redisUtils.get("user:1001");
        System.out.println("缓存获取用户：" + userName); // 输出：张三

        // 计数器
        redisUtils.incr("login:count:1001", 1);
        Integer count = redisUtils.get("login:count:1001");
        System.out.println("登录次数：" + count); // 输出：1

        // 删除缓存
        redisUtils.delete("user:1001");
        System.out.println("删除后缓存：" + redisUtils.get("user:1001")); // 输出：null
    }

    // ========== 测试Redisson分布式锁 ==========
    @Test
    public void testDistributedLock() {
        String lockKey = "order:create:1001";

        // 简化写法：加锁执行业务
        String result = redisUtils.executeWithLock(lockKey, () -> {
            System.out.println("获取锁成功，执行下单逻辑");
            return "用户1001下单成功";
        });
        System.out.println(result); // 输出：用户1001下单成功

        // 手动锁写法（灵活控制）
        RLock lock = redisUtils.tryLock(lockKey, 1, 30, TimeUnit.SECONDS);
        if (lock != null) {
            try {
                System.out.println("手动获取锁成功");
            } finally {
                redisUtils.unlock(lock);
            }
        }
    }
}
