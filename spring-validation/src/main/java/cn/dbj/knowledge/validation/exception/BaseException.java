package cn.dbj.knowledge.validation.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private final int code;

    public BaseException(int code) {
        super();
        this.code = code;
    }

    public BaseException(String message, int code) {
        super(message);
        this.code = code;
    }
}
