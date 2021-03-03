package com.example.common.support;

/**
 * @Author wxy
 * @Date 2020/9/22 11:46
 * @Version 1.0
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    DOWNGRADE(-1, "服务降级"),
    PERMISSION_ERROR(-2, "网关验证权限错误，非法的参数头!"),
    ;

    private final long code;
    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
