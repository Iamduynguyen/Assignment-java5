package com.example.assignment.controller;


import com.example.assignment.DAO.CategoryDAO;
import com.example.assignment.DAO.ProductDAO;
import com.example.assignment.Helper.HelperCtrl;
import com.example.assignment.module.Category;
import com.example.assignment.module.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@Controller
@RequestMapping("/admin")
public class Admin {
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
    List<Product> productList = new ArrayList<>();
    Category category = new Category();

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "/Template/Admin/index";
    }

    @GetMapping("/product/read")
    public String readProduct(Model model) {
        if (req.getParameter("delete") != null) {
            int id = Integer.parseInt(req.getParameter("delete"));
            productDAO.deleteById(id);
        }
        productList = productDAO.findAll();
        model.addAttribute("index", 0);
        System.out.println("alo");
        model.addAttribute("productList", productList);
        return "/Template/Admin/product";
    }


    @GetMapping("/product/save")
    public String getNewProduct(Model model) {
        if (req.getParameter("id") != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            product = productDAO.findById(id).get();
        }
        model.addAttribute("product", product);
        return "/Template/Admin/newProduct";
    }

    @PostMapping("/product/save")
    public String postNewProduct(@Valid @ModelAttribute("product") Product product, BindingResult errors,
                                 @RequestParam("fileimage") MultipartFile file) {
        if (errors.hasErrors()) {
            System.out.println(errors.getFieldErrors().get(0).getDefaultMessage() + "chay den day");
            return "/Template/Admin/newProduct";
        } else {
            try {
                helper.saveFile(file, "D:\\Study\\Ki 6\\Java 5\\Shopdongho\\Assignment\\src\\main\\resources\\static\\file");
                product.setImage(file.getOriginalFilename());
                productDAO.save(product);
                System.out.println("del lỗi gì");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/admin/product/read?success=true";
        }
    }

    @GetMapping("/category")
    public String getCategory(Model model) {
        if (req.getParameter("id") != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            category = categoryDAO.findById(id).get();
        }
        model.addAttribute("category", category);
        model.addAttribute("index", 1);
        System.out.println(category.getName());
        return "/Template/Admin/category";
    }

    @PostMapping("/category")
    public String postCategory(@ModelAttribute("category") Category category) {
        System.out.println(category.getName());
        categoryDAO.save(category);
        return "redirect:/admin/category?succes=true";
    }

    @ModelAttribute("categoryList")
    public List<Category> getCategories() {
        List<Category> categoryList = categoryDAO.findAll();
        return categoryList;
    }

}
