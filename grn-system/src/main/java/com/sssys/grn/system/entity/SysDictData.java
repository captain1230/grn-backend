package com.sssys.grn.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: captain
 * @Date: 10:18 2025/12/18
 */
@Data
@TableName("system_dict_data")
public class SysDictData {
    /** 字典编码 */
    @TableId
    private Long id;

    /** 字典排序 */
    private Integer sort;

    /** 字典标签 */
    private String dictLabel;

    /** 字典值 */
    private String dictValue;

    /** 字典类型 */
    private String dictType;

    /** 状态（1正常 0停用） */
    private Integer status;

    /** 备注 */
    private String remark;

    /** 创建者 */
    private Long creator;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 更新者 */
    private Long updater;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /** 是否删除 */
    @TableLogic
    private Boolean deleted;
}
