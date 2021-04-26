package com.als.score.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Value("${springdoc.swagger-ui.path}")
    private String swaggetUiPath;

    @GetMapping("/")
    public String index() {
        return "redirect:" + swaggetUiPath;
    }
}
