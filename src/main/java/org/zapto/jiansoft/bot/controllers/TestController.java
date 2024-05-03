package org.zapto.jiansoft.bot.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String test() {
        System.out.println("Hi!");
        return "Hello World";
    }
}
