package com.thxtdy.smtp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MappingTestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
