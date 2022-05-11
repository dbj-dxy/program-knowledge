package cn.dbj.knowledge.validation.controller;

import cn.dbj.knowledge.validation.dto.UserDTO;
import cn.dbj.knowledge.validation.vo.ResultCodeEnum;
import cn.dbj.knowledge.validation.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Slf4j
// 校验URL中的请求参数时（@PathVariable / @RequestParam），该注解需要放在这里
@Validated
@RestController
@RequiredArgsConstructor
public class GlobalValidController {

    @GetMapping("/path-variable/{id}")
    public ResultVO<?> pathVariable(@PathVariable @Max(value = 5) @NotNull Integer id) {
        log.info("param: {}", id);
        return ResultVO.fail(ResultCodeEnum.FAIL);
    }

    @GetMapping("/request-param")
    public ResultVO<?> requestParam(@RequestParam @Min(value = 5) @NotNull Integer id) {
        log.info("param: {}", id);
        return ResultVO.fail(ResultCodeEnum.FAIL);
    }

    @PostMapping("/request-body")
    public ResultVO<?> requestBody(@RequestBody @Validated UserDTO dto) {
        log.info("param: {}", dto);
        return ResultVO.fail(ResultCodeEnum.FAIL);
    }

    @PostMapping("/form")
    public ResultVO<?> form(@Validated UserDTO dto) {
        log.info("param: {}", dto);
        return ResultVO.fail(ResultCodeEnum.FAIL);
    }

    @PostMapping("/binding-result")
    public ResultVO<?> bindingResult(@Validated UserDTO dto, BindingResult validResult) {
        log.info("param: {}", dto);
        if (validResult.hasErrors()) {
            log.error(validResult.getAllErrors().toString());
        }
        return ResultVO.fail(ResultCodeEnum.FAIL);
    }
}
