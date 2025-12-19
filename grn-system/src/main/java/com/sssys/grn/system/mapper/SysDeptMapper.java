package com.sssys.grn.system.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sssys.grn.system.entity.SysDept;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: captain
 * @Date: 10:18 2025/12/18
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {
}
