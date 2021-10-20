package com.example.assignment.controller;

import com.example.assignment.DAO.*;
import com.example.assignment.Helper.HelperCtrl;
import com.example.assignment.module.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/myacount")
public class AcountController {

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
    @Autowired
    Customer customer;
    @Autowired
    Oderdao oderdao;
    @Autowired
    Oder oder;
    @Autowired
    Oderdetall oderdetall;
    @Autowired
    OderdetallDao oderdetallDao;
    List<Oderdetall> oderdetalls = new ArrayList<>();

    @GetMapping("/cart")
    public String getCart(Model model){
        customer = customerDAO.findById(1).get();
        if (oderdao.getOderByCustomer(customer)==null){
            oder.setStaff(staffDAO.findById(1).get());
            oder.setCustomer(customer);
            oderdao.save(oder);
        }else {
            oder = oderdao.getOderByCustomer(customer);
        }
        model.addAttribute("oder",oder.getLstOder());
        oderdetalls = oderdetallDao.getOderdetallsByOder(oder);
        for (Oderdetall x:oderdetalls){
            System.out.println(x.getProduct().getName());
        }
        return "/Template/Website/Cart";
    }

}
