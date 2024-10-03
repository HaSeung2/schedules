package com.sparta.scheduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String doIndex() {
        return "contentView/index";
    }

    @GetMapping("/index")
    public String index() {
        return "contentView/index";
    }
}
