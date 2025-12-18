package com.sssys.grn.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: gaolikang
 * @Date: 10:18 2025/12/18
 */
@Data
@TableName("system_role")
public class SysRole {
    /** 角色ID */
    @TableId
    private Long id;

    /** 角色名称 */
    private String roleName;

    /** 角色权限 */
    private String roleKey;

    /** 角色排序 */
    private Integer sort;

    /** 角色状态（1正常 0停用） */
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
