package cn.dbj.knowledge.validation.controller;

import cn.dbj.knowledge.validation.dto.UserDTO;
import cn.dbj.knowledge.validation.vo.ResultCodeEnum;
import cn.dbj.knowledge.validation.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Slf4j
// 校验URL中的请求参数时，该注解需要放在这里
@Validated
@RestController
@RequiredArgsConstructor
public class GlobalValidController {



    @GetMapping("/get")
    public ResultVO<?> globalGetValid(@NotBlank String name, BindingResult validResult) {
        log.info("param: {}", name);
        log.error(validResult.getAllErrors().toString());
        return ResultVO.fail(ResultCodeEnum.FAIL);
    }

    @PostMapping("/json")
    public ResultVO<?> globalJsonValid(@Validated @RequestBody UserDTO dto, BindingResult validResult) {
        log.info("param: {}", dto);
        log.error(validResult.getAllErrors().toString());
        return ResultVO.fail(ResultCodeEnum.FAIL);
    }

    @PostMapping("/form")
    public ResultVO<?> globalFormValid(@Validated UserDTO dto, BindingResult validResult) {
        log.info("param: {}", dto);
        log.error(validResult.getAllErrors().toString());
        return ResultVO.fail(ResultCodeEnum.FAIL);
    }
}
