package cn.dbj.knowledge.validation.config;

import cn.dbj.knowledge.validation.exception.BaseException;
import cn.dbj.knowledge.validation.vo.ResultCodeEnum;
import cn.dbj.knowledge.validation.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * system exception
     */
    @ExceptionHandler(Exception.class)
    public ResultVO<?> systemExceptionHandler(Exception e) {
        log.error("systemException = {}", e.getMessage());
        return ResultVO.fail(ResultCodeEnum.SERVER_ERROR);
    }

    /**
     * customize exception
     */
    @ExceptionHandler(BaseException.class)
    public ResultVO<?> customizeExceptionHandler(BaseException e) {
        String msg = e.getMessage();
        if (StringUtils.hasText(msg)) {
            log.error("customizeException = {}", msg);
            return ResultVO.fail(e.getCode(), msg);
        } else {
            log.error("customizeException = {}", ResultCodeEnum.getMessageByCode(e.getCode()));
            return ResultVO.fail(e.getCode());
        }
    }

    /**
     * get params exception
     */
    @ExceptionHandler(BindException.class)
    public ResultVO<?> bindExceptionHandler(BindException e) {
        String msg = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.error("bindException = {}", msg);
        return ResultVO.fail(ResultCodeEnum.PARAMS_ERROR, msg);
    }

    /**
     * request body(form) argument exception
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVO<?> constraintViolationExceptionHandler(ConstraintViolationException e) {
        String msg = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        log.error("constraintViolationException = {}", msg);
        return ResultVO.fail(ResultCodeEnum.PARAMS_ERROR, msg);
    }

    /**
     * request body(json) argument exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.error("methodArgumentNotValidException = {}", msg);
        return ResultVO.fail(ResultCodeEnum.PARAMS_ERROR, msg);
    }
}
