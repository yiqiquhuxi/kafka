package org.example.provider.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author: chenqi
 * Created: 2023/9/6 13:20
 * Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultObject<S> implements Serializable {

    private static final long serialVersionUID = 1L;
	static ErrorCode ok = ErrorCode.OK;
    static ErrorCode fail = ErrorCode.FAIL;
    private volatile Integer code;
    private volatile String message;
    private volatile S data;

    public static <S> ResultObject<S> result(Integer t, String k, S s) {
        return new ResultObject<>(t, k, s);
    }

    public static ResultObject<?> ok() {
        return result(ok.getCode(), ok.getMsg(), null);
    }

    public static <S> ResultObject<S> ok(S s) {
        return result(ok.getCode(), ok.getMsg(), s);
    }

    public static <S>ResultObject<S> fail() {
        return result(fail.getCode(), fail.getMsg(), null);
    }

    public static <S> ResultObject<S> fail(String msg) {
        return result(fail.getCode(), msg, null);
    }

    public static <S> ResultObject<S> fail(Integer code, String msg) {
        return result(code, msg, null);
    }

    public static <S> ResultObject<S> fail(Integer code, String msg, S s) {
        return result(code, msg, s);
    }
}
