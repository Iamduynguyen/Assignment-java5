package com.example.assignment.controller;


import com.example.assignment.DAO.CategoryDAO;
import com.example.assignment.DAO.CustomerDAO;
import com.example.assignment.DAO.ProductDAO;
import com.example.assignment.DAO.StaffDAO;
import com.example.assignment.Helper.HelperCtrl;
import com.example.assignment.module.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Sign {
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
    int _code;
    List<Product> productList = new ArrayList<>();
    Category category = new Category();
    List<Staff> staffList = new ArrayList<>();
    List<Customer> customerList = new ArrayList<>();

    @GetMapping("/sign")
    public String getSign(Model model) {
        List<Category> categoryList = categoryDAO.findAll();
        model.addAttribute("categoryList", categoryList);
        return "/Template/Website/signin";
    }

    @PostMapping("/sign")
    public String postSign(@RequestParam("email")String email,@RequestParam("password") String password) {
        System.out.println("alof");
        staffList = staffDAO.findAll();
        customerList = customerDAO.findAll();
        if (req.getParameter("admin")!=null){
            for (Staff x : staffList) {
                if (x.getEmail().equals(email) && x.getPassword().equals(password)) {
                    _staff = x;
                    session.setAttribute("staff", _staff);
                    return "redirect:/admin/dashboard";
                }
            }
        }else {
            for (Customer x : customerList) {
                if (x.getEmail().equals(email) && x.getPassword().equals(password)) {
                    customer = x;
                    session.setAttribute("customer", customer);
                    System.out.println(customer.getName());
                    return "redirect:/store";
                }
            }
        }
        return "/Template/Website/signin";
    }


    @ModelAttribute("categoryList")
    public List<Category> getCategories() {
        List<Category> categoryList = categoryDAO.findAll();
        return categoryList;
    }

    @GetMapping("/signupadmin")
    public String getSignupAdmin() {
        return "/Template/Website/signupadmin";
    }

    @PostMapping("/signupadmin")
    public String postSignupAdmin(Model model, @ModelAttribute("staff") Staff staff) {
        session.setAttribute("newStaff", staff);
        return "redirect:/checkstaff";
    }

    @GetMapping("/signup")
    public String getSignup() {
        return "/Template/Website/signup";
    }

    @PostMapping("/signup")
    public String postSignup(Model model, @ModelAttribute("customer") Customer customer) {
        session.setAttribute("newCustomer", customer);
        System.out.println(customer.getName());
        return "redirect:/checkcustomercode";
    }

    @GetMapping("checkcustomercode")
    public String getcheckcustomercode() {
        if (session.getAttribute("newCustomer") == null) {
            return "redirect:/signupa";
        }
        _code = (int) (Math.random() * 1000000);
        Customer customer = (Customer) session.getAttribute("newCustomer");
        Mail mail = new Mail();
        mail.setFromEmail("Duyshop<nguyenducduy96tn@gmail.com>");
        mail.setToEmail(customer.getEmail());
        mail.setSubject("Xác nhận tài khoản của bạn");
        mail.setBody("Mã xác nhận:" + _code);
        helper.sendMailTo(mail);
        return "/Template/Website/checkcode";
    }

    @PostMapping("checkcustomercode")
    public String postCheckCustomercode(Model model) {
        if (session.getAttribute("newCustomer") == null) {
            return "redirect:/signupa";
        }
        Customer customer = (Customer) session.getAttribute("newCustomer");
        if (req.getParameter("code").equals(_code + "")) {
            customerDAO.save(customer);
            System.out.println("alo");
        } else {
            model.addAttribute("error", "Bạn nhập sai mã code");
            return "";
        }
        return "redirect:/sign";
    }

    @GetMapping("/checkstaff")
    public String getCheckstaff() {
        if (session.getAttribute("newStaff") == null) {
            return "redirect:/signupadmin";
        }
        _code = (int) (Math.random() * 1000000);
        Staff staff = (Staff) session.getAttribute("newStaff");
        Mail mail = new Mail();
        mail.setFromEmail("Duyshop<nguyenducduy96tn@gmail.com>");
        mail.setToEmail(staff.getEmail());
        mail.setSubject("Xác nhận tài khoản của bạn");
        mail.setBody("Mã xác nhận:" + _code);
        helper.sendMailTo(mail);
        return "/Template/Website/checkcode";
    }

    @PostMapping("/checkstaff")
    public String postCheckstaff(Model model) {
        if (session.getAttribute("newStaff") == null) {
            return "redirect:/signupadmin";
        }
        Staff staff = (Staff) session.getAttribute("newStaff");
        if (req.getParameter("code").equals(_code + "")) {
            staffDAO.save(staff);
            System.out.println("alo");
        } else {
            model.addAttribute("error", "Bạn nhập sai mã code");
            return "";
        }
        return "redirect:/sign";
    }
}
