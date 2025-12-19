package com.sssys.grn.framework.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Description:
 * @Author: captain
 * @Date: 18:14 2025/12/18
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有接口跨域（也可指定特定路径：/api/**）
        registry.addMapping("/**")
                // 允许所有来源
                .allowedOriginPatterns("*")
                // 允许的请求方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许的请求头
                .allowedHeaders("*")
                // 允许携带Cookie（如需跨域传递认证信息，需开启）
                .allowCredentials(true)
                // 预检请求缓存时间（秒），减少OPTIONS请求次数
                .maxAge(3600);
    }

    /**
     * 扩展消息转换器 (影响swagger使用)
     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //自定义Jackson对象映射器，解决日期序列化、Long精度丢失等问题
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // 配置LocalDateTime序列化格式
//        JavaTimeModule module = new JavaTimeModule();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        // 序列化
//        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
//        //反序列化
//        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
//
//        // 2.解决Long/BigInteger前端精度丢失问题（转字符串）
//        module.addSerializer(Long.class, ToStringSerializer.instance);
//        module.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        module.addSerializer(BigInteger.class, ToStringSerializer.instance);
//
//        // 3. 注册模块并配置全局反序列化规则
//        objectMapper.registerModule(module);
//        // 忽略JSON中不存在的字段（避免前端传多余字段导致报错）
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        // 4. 将自定义ObjectMapper注入到Spring的消息转换器中
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(objectMapper);
//
//        // 优先使用自定义转换器
//
//        converters.add(0, converter);
//    }
}
