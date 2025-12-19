package com.sssys.grn.common.exception;
import lombok.Getter;

/**
 * @Description: 自定义业务异常
 * @Author: captain
 * @Date: 2025/12/19
 */
@Getter
public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 构造方法
     *
     * @param msg  错误消息
     * @param code 错误码
     */
    public BusinessException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    /**
     * 快捷构造（默认500码）
     */
    public BusinessException(String msg) {
        this(msg, 500);
    }

    /**
     * 快捷构造：参数错误（400）
     */
    public static BusinessException paramError(String msg) {
        return new BusinessException(msg, 400);
    }

    /**
     * 快捷构造：认证错误（401）
     */
    public static BusinessException authError(String msg) {
        return new BusinessException(msg, 401);
    }

    /**
     * 快捷构造：权限错误（403）
     */
    public static BusinessException forbiddenError(String msg) {
        return new BusinessException(msg, 403);
    }
}
