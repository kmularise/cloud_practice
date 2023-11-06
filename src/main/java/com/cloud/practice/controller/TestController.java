package com.cloud.practice.controller;

import com.cloud.practice.dto.HelloDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @ResponseBody
    @RequestMapping("/hello")
    public HelloDto responseJSON() {
        return new HelloDto("hello-world");
    }
}
