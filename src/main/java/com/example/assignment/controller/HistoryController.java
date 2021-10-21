package com.example.assignment.controller;

import com.example.assignment.DAO.*;
import com.example.assignment.Helper.HelperCtrl;
import com.example.assignment.module.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HistoryController {

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
    List<Product> _productList = new ArrayList<>();
    List<Oderdetall> oderdetalls = new ArrayList<>();
    int _page = 0;

    @GetMapping("/mybought")
    public String getdamuas(Model model){
        customer = customerDAO.findById(1).get();
        List<Oder> oders = oderdao.getBought(customer);
        model.addAttribute("oders",oders);
        return "/Template/Website/ListOder";
    }
    @GetMapping("/oder{id}")
    public String getoderd(@RequestParam("id")int id,Model model){
        customer = customerDAO.findById(1).get();
        oder = oderdao.findById(id).get();
        oderdetalls = oderdetallDao.getOderdetallsByOder(oder);
        model.addAttribute("oderdetalls",oderdetalls);
        model.addAttribute("oder",oder);
        return "/Template/Website/Oder";
    }
    @GetMapping("/oder/confirm{remove}")
    public String getProduct(@RequestParam("remove") String confirm){
        if (confirm.equals("true")){
            oder.setStatus(3);
        }else if (confirm.equals("false")){
            oderdetallDao.deleteAll(oderdetalls);
            oderdao.delete(oder);
        }
        return "redirect:/mybought";
    }
}
