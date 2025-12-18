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
@TableName("system_user_role")
public class SysUserRole {
    /** 主键ID */
    @TableId
    private Long id;

    /** 用户ID */
    private Long userId;

    /** 角色ID */
    private Long roleId;

    /** 创建者 */
    private Long creator;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 是否删除 */
    @TableLogic
    private Boolean deleted;
}
