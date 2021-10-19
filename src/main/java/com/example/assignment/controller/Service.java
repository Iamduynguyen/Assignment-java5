package com.example.assignment.controller;


import com.example.assignment.DAO.CategoryDAO;
import com.example.assignment.DAO.CustomerDAO;
import com.example.assignment.DAO.ProductDAO;
import com.example.assignment.DAO.StaffDAO;
import com.example.assignment.Helper.HelperCtrl;
import com.example.assignment.module.Product;
import com.example.assignment.module.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Service {

    @Autowired
    HttpServletRequest req;
    @Autowired
    ProductDAO productDAO;
    @Autowired
    CategoryDAO categoryDAO;
    @Autowired
    HelperCtrl helper;
    @Autowired
    Product product;
    @Autowired
    StaffDAO staffDAO;
    @Autowired
    Staff _staff;
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    HttpSession session;
    List<Product> _productList = new ArrayList<>();

    @GetMapping("/a")
    public String getService(Model model) {
        Pageable pageable = PageRequest.of(0, 2);
        _productList = productDAO.findByCategoryandLimit(1, pageable);
        for (Product x : _productList) {
            System.out.println(x.getName());
        }
        model.addAttribute("productList", _productList);
        System.out.println("alo");
        return "/Template/Website/service";
    }


}
