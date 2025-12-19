package com.sssys.grn.common.pojo;

import lombok.Data;

/**
 * @Description:
 * @Author: captain
 * @Date: 2025/12/19
 */

@Data
public class CommonResult<T> {
    /**
     * 响应码（200=成功，其他=失败）
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    // ========== 静态构造方法 ==========
    /**
     * 成功（无数据）
     */
    public static <T> CommonResult<T> success() {
        return success(null);
    }

    /**
     * 成功（有数据）
     */
    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setCode(200);
        commonResult.setMsg("操作成功");
        commonResult.setData(data);
        return commonResult;
    }

    /**
     * 失败
     */
    public static <T> CommonResult<T> error(Integer code, String msg) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setCode(code);
        commonResult.setMsg(msg);
        commonResult.setData(null);
        return commonResult;
    }


    // ========== 常用错误快捷方法 ==========
    /**
     * 参数错误（400）
     */
    public static <T> CommonResult<T> paramError(String msg) {
        return error(400, msg);
    }

    /**
     * 认证失败（401）
     */
    public static <T> CommonResult<T> authError(String msg) {
        return error(401, msg);
    }

    /**
     * 权限不足（403）
     */
    public static <T> CommonResult<T> forbiddenError(String msg) {
        return error(403, msg);
    }

    /**
     * 服务器错误（500）
     */
    public static <T> CommonResult<T> serverError(String msg) {
        return error(500, msg);
    }
}
