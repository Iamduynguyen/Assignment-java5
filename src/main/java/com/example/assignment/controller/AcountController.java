package com.example.assignment.controller;

import com.example.assignment.DAO.*;
import com.example.assignment.Helper.HelperCtrl;
import com.example.assignment.module.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
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
    int sale = 0;


    @GetMapping("/cart")
    public String getCart(Model model, @ModelAttribute("customer")Customer customer){
        customer = customerDAO.findById(1).get();
        if (req.getParameter("delete")!=null){
            int oderdtID = Integer.parseInt(req.getParameter("delete"));
            Oderdetall oderdetalls = oderdetallDao.findById(oderdtID).get();
            oderdetallDao.delete(oderdetalls);
        }
        if (oderdao.getOderByCustomer(customer)==null){
            Oder newoder = new Oder();
            newoder.setStaff(staffDAO.findById(1).get());
            newoder.setCustomer(customer);
            newoder.setStatus(0);
            oderdao.save(newoder);
        }else {
            oder = oderdao.getOderByCustomer(customer);
        }
        oderdetalls = oderdetallDao.getOderdetallsByOder(oder);

        model.addAttribute("sale",sale);
        model.addAttribute("amount",oder.getAmouttotals(oderdetalls));
        model.addAttribute("Amouttotals",oder.getAmouttotals(oderdetalls)-sale);
        model.addAttribute("oderdetalls",oderdetalls);
        return "/Template/Website/Cart";
    }

    @GetMapping("/cart/confirm")
    public String getCart(){
        oder.setCreated(new Date());
        oder.setStatus(1);
        oderdao.save(oder);
        System.out.println("ok");
        return "redirect:/myacount/cart";
    }




}
