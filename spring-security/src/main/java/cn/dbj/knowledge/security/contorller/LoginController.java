package cn.dbj.knowledge.security.contorller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Value("${spring.security.user.roles}")
    private List<String> roles;

    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("/roles")
    public String roles() {
        return JSON.toJSONString(roles);
    }
}
