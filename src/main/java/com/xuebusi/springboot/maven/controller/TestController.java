package com.xuebusi.springboot.maven.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TestController {
    
    @GetMapping(value = "/test")
    public String test() {
        return "hello world";
    }
}
