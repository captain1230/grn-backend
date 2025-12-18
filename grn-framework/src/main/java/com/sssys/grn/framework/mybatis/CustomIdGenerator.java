package com.sssys.grn.framework.mybatis;


import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: captain
 * @Date: 2025/12/17
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    private final Snowflake snowflake;
    @Autowired
    public CustomIdGenerator(Snowflake snowflake) {
        this.snowflake = snowflake;
    }

    @Override
    public Long nextId(Object entity) {
        return snowflake.nextId();
    }
}
