package com.example.assignment.controller;

import com.example.assignment.DAO.CustomerDAO;
import com.example.assignment.module.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomePageController {
    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    HttpSession session;

    @RequestMapping("/home")
    public String getHome(){
        return "/Template/Website/signin";
    }

    @RequestMapping("/")
    public String getHomes(){
        Customer x = customerDAO.findById(1).get();
        session.setAttribute("customer",x);
        return "/Template/Website/signin";
    }
}
