package com.example.chapter10_scrf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @PostMapping("/hello")
    public String postHello() {
        return "Post Hello!";
    }
    @PostMapping("/ciao")
    public String postCiao() {
        return "Post Ciao";
    }

    @GetMapping("/hello")
    public String getHello() {
        return "Get Hello!";
    }
}


