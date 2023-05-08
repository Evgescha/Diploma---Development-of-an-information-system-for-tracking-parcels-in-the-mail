package com.hescha.mailtracking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/service")
    public String service(){
        return "service";
    }
    @GetMapping("/single")
    public String single(){
        return "single";
    }
}
