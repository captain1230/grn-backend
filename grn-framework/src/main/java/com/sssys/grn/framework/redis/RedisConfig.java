//package com.sssys.grn.framework.redis;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.redisson.spring.data.connection.RedissonConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
///**
// * Redisson + RedisTemplate 整合配置（适配Spring Boot 3，移除废弃API）
// */
//@Configuration
//public class RedisConfig {
//
//    @Value("${spring.redis.host:localhost}")
//    private String redisHost;
//
//    @Value("${spring.redis.port:6379}")
//    private int redisPort;
//
//    @Value("${spring.redis.password:}")
//    private String redisPassword;
//
//    @Value("${spring.redis.database:0}")
//    private int redisDatabase;
//
//    /**
//     * 配置ObjectMapper（统一序列化规则）
//     */
//    private ObjectMapper buildObjectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        // 注册Java 8时间模块（解决LocalDateTime序列化）
//        objectMapper.registerModule(new JavaTimeModule());
//        // 可选：关闭时间戳序列化（LocalDateTime序列化为字符串而非时间戳）
//        // objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        // 可选：忽略未知字段（反序列化时避免字段不一致报错）
//        // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        return objectMapper;
//    }
//
//    /**
//     * 创建RedissonClient Bean
//     */
//    @Bean(destroyMethod = "shutdown")
//    public RedissonClient redissonClient() {
//        Config config = new Config();
//        config.useSingleServer()
//                .setAddress("redis://" + redisHost + ":" + redisPort)
//                .setPassword(redisPassword.isEmpty() ? null : redisPassword)
//                .setDatabase(redisDatabase)
//                .setConnectionPoolSize(10)
//                .setConnectionMinimumIdleSize(2);
//        return Redisson.create(config);
//    }
//
//    /**
//     * RedisTemplate配置（移除废弃的setObjectMapper方法）
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedissonConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//
//        // 1. Key/HashKey序列化（String）
//        RedisSerializer<String> stringSerializer = RedisSerializer.string();
//        template.setKeySerializer(stringSerializer);
//        template.setHashKeySerializer(stringSerializer);
//
//        // 2. Value/HashValue序列化（JSON）：构造函数直接传入ObjectMapper（替代废弃的setObjectMapper）
//        ObjectMapper objectMapper = buildObjectMapper();
//        Jackson2JsonRedisSerializer<Object> jsonSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
//
//        template.setValueSerializer(jsonSerializer);
//        template.setHashValueSerializer(jsonSerializer);
//
//        // 初始化模板
//        template.afterPropertiesSet();
//        return template;
//    }
//}
