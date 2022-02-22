package cn.dbj.knowledge.validation.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ResultVO<T> {
    @JSONField(name = "aaa")
    private Integer code;
    private String message;
    private T data;

    private ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultVO<T> success() {
        return new ResultVO<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), null);
    }

    public static <T> ResultVO<T> success(String message) {
        return new ResultVO<>(ResultCodeEnum.SUCCESS.getCode(), message, null);
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(ResultCodeEnum.SUCCESS.getCode(), null, data);
    }

    public static <T> ResultVO<T> success(String message, T data) {
        return new ResultVO<>(ResultCodeEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> ResultVO<T> fail(Integer code) {
        return new ResultVO<>(code, ResultCodeEnum.getMessageByCode(code), null);
    }

    public static <T> ResultVO<T> fail(Integer code, String message) {
        return new ResultVO<>(code, message, null);
    }

    public static <T> ResultVO<T> fail(Integer code, String message, T data) {
        return new ResultVO<>(code, message, data);
    }

    public static <T> ResultVO<T> fail(ResultCodeEnum resultCodeEnum) {
        return new ResultVO<>(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), null);
    }

    public static <T> ResultVO<T> fail(ResultCodeEnum resultCodeEnum, String message) {
        return new ResultVO<>(resultCodeEnum.getCode(), message, null);
    }

    public static <T> ResultVO<T> fail(ResultCodeEnum resultCodeEnum, String message, T data) {
        return new ResultVO<>(resultCodeEnum.getCode(), message, data);
    }
}
