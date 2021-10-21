package com.example.assignment.controller;


import com.example.assignment.DAO.*;
import com.example.assignment.Helper.HelperCtrl;
import com.example.assignment.module.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    int _page = 0;

    @RequestMapping("/store")
    public String getService(Model model) {
        String page = req.getParameter("page");
        int idx = 0;
        if (req.getParameter("category")!=null){
            System.out.println("alo");
           idx = Integer.parseInt(req.getParameter("category"));
        }
            if (page!=null){
                if (page.equals("next")){
                    _page++;
                }else if (page.equals("prev")){
                    _page--;
                }else if(page.equals("0")){
                    _page=0;
                }
            }
        System.out.println("alo");
        Pageable pageable = PageRequest.of(_page, 6);
        if (idx==0){
            _productList = productDAO.findLimit(pageable);
        }else {
            _productList = productDAO.findByCategoryandLimit(idx, pageable);
        }
        for (Product x : _productList) {
            System.out.println(x.getName());
        }
        model.addAttribute("productList", _productList);
        System.out.println("alo");
        return "/Template/Website/service";
    }

    @GetMapping("/store/product{id}")
    public String getServiceByproduct(@RequestParam("id")int idx,Model model){
        product = productDAO.findById(idx).get();
        model.addAttribute("product",product);
        return "/Template/Website/productInf";
    }

    @PostMapping("/store/product")
    public String postStoreProduct(Model model){
        int quantity = Integer.parseInt(req.getParameter("number"));
        if (session.getAttribute("customer")==null){
            return "redirect:/sign?erorr=Dang nhap di";
        }
        customer = (Customer) session.getAttribute("customer");
        if (oderdao.getOderByCustomer(customer)==null){ //toi tim 1 oder theo khach hang neu k co thi tao moi
            oder.setStaff(staffDAO.findById(1).get());
            oder.setCustomer(customer);
            oderdao.save(oder);
        }else { // co rôi thi lay luon
            oder = oderdao.getOderByCustomer(customer);
        } //tao oderdetall va add oder va add san pham
        oderdetall.setOder(oder); // oderdetall add oder
        oderdetall.setProduct(product); //oderdetall add product
        oderdetall.setActive(true); //cai nay can cung dc
        oderdetall.setQuantity(quantity); //add so luong cua khach hang
        oderdetallDao.save(oderdetall); //roi luu lai
        return "redirect:/myacount/cart";
    }

    @GetMapping("/bought")
    public String getdamuas(Model model){
        customer = customerDAO.findById(1).get();
        List<Oder> oders = oderdao.getBought(customer);
        model.addAttribute("oders",oders);
        return "/Template/Website/ListOder";
    }

}
