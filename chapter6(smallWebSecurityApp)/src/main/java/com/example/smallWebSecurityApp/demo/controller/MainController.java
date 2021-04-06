package com.example.smallWebSecurityApp.demo.controller;

import com.example.smallWebSecurityApp.demo.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private ProductService productService;

    @GetMapping("/main")
    private String main(Authentication a, Model theModel){
        theModel.addAttribute("username", a.getName());
        theModel.addAttribute("products", productService.findAll());
        return "main_page";
    }
}
