package com.example.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

    @RequestMapping("/home")
    public String getHome(){
        return "/Template/Website/signin";
    }
}
