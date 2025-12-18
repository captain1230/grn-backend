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
@TableName("system_dept")
public class SysDept {
    /** 部门id */
    @TableId
    private Long id;

    /** 部门名称 */
    private String name;

    /** 父部门id */
    private Long parentId;

    /** 显示顺序 */
    private Integer sort;

    /** 负责人 */
    private Long leaderUserId;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态（1正常 0停用） */
    private Integer status;

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
