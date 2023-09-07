package com.cloud.practice.controller;

import com.cloud.practice.HelloDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @ResponseBody
    @RequestMapping("/hello")
    public HelloDto responseJSON() {
        HelloDto helloDto = new HelloDto("hello-world");
        fddfdfsdkfdjsljkds
        return helloDto;
    }
}
