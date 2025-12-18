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
@TableName("system_user")
public class SysUser {
    /** 用户ID */
    @TableId
    private Long id;

    /** 用户账号 */
    private String username;

    /** 密码 */
    private String password;

    /** 用户昵称 */
    private String nickname;

    /** 备注 */
    private String remark;

    /** 部门ID */
    private Long deptId;

    /** 岗位编号数组 */
    private String postIds;

    /** 用户邮箱 */
    private String email;

    /** 手机号码 */
    private String mobile;

    /** 用户性别 */
    private Integer sex;

    /** 头像地址 */
    private String avatar;

    /** 帐号状态（1正常 0停用） */
    private Integer status;

    /** 最后登录IP */
    private String loginIp;

    /** 最后登录时间 */
    private LocalDateTime loginDate;

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
