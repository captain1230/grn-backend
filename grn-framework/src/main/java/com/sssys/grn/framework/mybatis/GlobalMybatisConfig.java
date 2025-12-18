package com.sssys.grn.framework.mybatis;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: gaolikang
 * @Date: 09:39 2025/12/18
 */
@Configuration
@MapperScan("com.sssys.grn.**.mapper") // 扫描所有模块的mapper接口
public class GlobalMybatisConfig {

    private final CustomIdGenerator customIdGenerator;

    @Autowired
    public GlobalMybatisConfig(CustomIdGenerator customIdGenerator) {
        this.customIdGenerator = customIdGenerator;
    }



    /**
     * 分页插件配置
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加MySQL分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 全局配置（指定ID生成器等）
     */
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        // 设置全局默认主键生成器
        dbConfig.setIdType(IdType.ASSIGN_ID);
        globalConfig.setDbConfig(dbConfig);
        // 设置自定义ID生成器
        globalConfig.setIdentifierGenerator(customIdGenerator);
        return globalConfig;
    }
}
