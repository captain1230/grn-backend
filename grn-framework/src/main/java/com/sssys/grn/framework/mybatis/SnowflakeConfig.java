package com.sssys.grn.framework.mybatis;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: captain
 * @Date: 09:16 2025/12/18
 */
@Slf4j
@Component
public class SnowflakeConfig {

    // 雪花算法workId的最大限制（原逻辑20）
    private static final int MAX_WORK_ID = 20;
    private final Environment environment;
    @Autowired
    private SnowflakeConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Snowflake snowflake() {
        // 1. 基于本机IP生成唯一workId 可以把id生成搞一个服务器
        Long workId = generateWorkIdByIp();
        // 2. 校验workId范围
        if (workId > MAX_WORK_ID) {
            workId = workId % MAX_WORK_ID;
        }
        // 保证workId至少为1
        if (workId < 1) {
            workId = 1L;
        }

        // 2. 生成dataCenterId（从environment获取应用名）
        String appName = environment.getProperty("spring.application.name", "default-app");
        long dataCenterId = (HashUtil.elfHash(appName) % 21 + 1);

        // 3. 日志打印
        log.info("SnowflakeConfig.snowflake workId:{}, dataCenterId:{}", workId, dataCenterId);

        // 4. 创建雪花算法实例
        return IdUtil.getSnowflake(workId, dataCenterId);
    }

    /**
     * 基于本机IP哈希生成workId
     */
    private Long generateWorkIdByIp() {
        String localIp = NetUtil.getLocalhostStr();
        if (localIp == null) {
            localIp = "127.0.0.1";
        }
        return (long) Math.abs(HashUtil.elfHash(localIp));
    }
}
