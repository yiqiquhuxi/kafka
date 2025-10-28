package org.example.provider.conf;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.example.provider.common.ResultObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: chenqi
 * Created: 2023/10/18 17:02
 * Description:
 */

@Slf4j
@ControllerAdvice
public class ValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultObject handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        String errors = result.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResultObject.fail(errors);
    }

    private String getFirstErrorMessage(List<FieldError> fieldErrors) {
        if (CollectionUtil.isNotEmpty(fieldErrors)) {
            FieldError fieldError = fieldErrors.get(0);
            if (fieldError != null) {
                return fieldError.getDefaultMessage();
            }
        }
        return null;
    }


    /**
     * 处理runtimeException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultObject handleLoginException(RuntimeException e) {
        e.printStackTrace();
//        log.info("RuntimeException error,{}", JSON.toJSONString(e));
        return ResultObject.fail(e.getMessage());
    }


    // 处理全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultObject handleException(Exception e) {
        e.printStackTrace();
        log.info("ControllerAdvice error,{}", JSON.toJSONString(e));
        return ResultObject.fail(e.getMessage());
    }


    // 处理绑定异常（通常发生在表单数据绑定到对象时）
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultObject handleBindException(BindException e) {
        e.printStackTrace();
        String errors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        // Log the error details for debugging purpose
        log.info("Validation errors: {}", errors);
        return ResultObject.fail(errors);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultObject handleJsonParseError(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getMostSpecificCause();
        String message = "请求体格式错误";
        if (cause instanceof InvalidFormatException invalidFormat) {
            String field = invalidFormat.getPath().stream()
                    .map(ref -> ref.getFieldName() == null ? String.valueOf(ref.getIndex()) : ref.getFieldName())
                    .collect(Collectors.joining("."));
            message = String.format("字段 `%s` 解析失败: \"%s\" 数据类型不对",
                    field,
                    invalidFormat.getValue(),
                    invalidFormat.getTargetType().getSimpleName());
        } else if (cause != null) {
            message = "请求体格式错误: " + cause.getMessage();
        }
        return ResultObject.fail(message);
    }



}
