package com.sssys.grn.framework.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.core.IdentifierGenerator;
import org.springframework.data.util.TypeInformation;

/**
 * @Description:
 * @Author: captain
 * @Date: 2025/12/17
 */

public class CustomIdGenerator implements IdentifierGenerator {
    @Autowired
    private Snowflake snowflake;

    @Override
    public Long nextId(Object entity) {
        long id = snowflake.nextId();
        //返回生成的id值即可.
        return id;
    }

    public void setSnowflake(Snowflake snowflake) {
        this.snowflake = snowflake;
    }

    @Override
    public <T> T generateIdentifierOfType(TypeInformation<T> type) {
        return null;
    }
}
