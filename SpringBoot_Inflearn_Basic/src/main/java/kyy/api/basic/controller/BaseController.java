package kyy.api.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
    HTML 템플릿인 thymeleaf 를 사용할 것이기 때문에 템플릿을 반환하는 @Controller를 사용한다.
 */
@Controller
public class BaseController {
    @GetMapping("main")
    public String hello(Model model) {
        model.addAttribute("data", "Guest !");
        return "main";
    }
}
