package org.example.provider.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Author: chenqi
 * Created: 2023/9/6 13:20
 * Description:
 */

@Getter
@AllArgsConstructor
public enum ErrorCode {

    OK(200, "success"),
    FAIL(500, "系统错误"),
    NOT_FOUND(404, "没有找到"),
    UNAUTHORIZED(401, "没有权限"),
    VALIDATE_ERROR(408, "参数校验失败");

    private Integer code;
    private String msg;


    public static ErrorCode getErrorCode(Integer code) {
        return Arrays.stream(ErrorCode.values())
                .filter(errorCode -> errorCode.getCode().equals(code))
                .findFirst().orElse(null);
    }

}
