package com.example.assignment.controller;


import com.example.assignment.DAO.CategoryDAO;
import com.example.assignment.DAO.Oderdao;
import com.example.assignment.DAO.OderdetallDao;
import com.example.assignment.DAO.ProductDAO;
import com.example.assignment.Helper.HelperCtrl;
import com.example.assignment.module.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.Optional;

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
    @Autowired
    Category category;
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
    int page =0;

    List<Oderdetall> oderdetalls = new ArrayList<>();

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "/Template/Admin/index";
    }

    @GetMapping("/product/read")
    public String readProduct(Model model,@RequestParam("category") int categoryId) {
        if (req.getParameter("delete") != null) {
            int id = Integer.parseInt(req.getParameter("delete"));
            productDAO.deleteById(id);
        }
        Pageable pageable = PageRequest.of(page, 6);
        if (categoryId>0){
            category = categoryDAO.findById(categoryId).get();
            productList = productDAO.findByCategoryandLimit(categoryId,pageable);
        }else {
            productList = productDAO.findLimit(pageable);
        }
        if (req.getParameter("key")!=null){
            productList = productDAO.findByName(req.getParameter("key"),pageable);
        }
        model.addAttribute("category",category);
        model.addAttribute("page",page);
        model.addAttribute("productList", productList);
        return "/Template/Admin/product";
    }


    @GetMapping ("/delete{id}")
    public String deleteproduct(@RequestParam("id") int id){
        productDAO.deleteById(id);
        return "redirect:/admin/product/read?category="+0;
    }

    @GetMapping("/product/save")
    public String getNewProduct(Model model) {
        if (req.getParameter("id") != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            product = productDAO.findById(id).get();
        }else {
            product = new Product();
        }
        model.addAttribute("product", product);
        return "/Template/Admin/newProduct";
    }

    @PostMapping("/product/save")
    public String postNewProduct(@Valid @ModelAttribute("product") Product newproduct, BindingResult errors,
                                 @RequestParam("fileimage") MultipartFile file,Model model) {
        if (errors.hasErrors()) {
            System.out.println(errors.getFieldErrors().get(0).getDefaultMessage() + "chay den day");
            return "/Template/Admin/newProduct";
        } else {
            try {
                helper.saveFile(file, "D:\\Study\\Ki 6\\Java 5\\Shopdongho\\Assignment\\src\\main\\resources\\static\\file");
                if (!file.getOriginalFilename().isEmpty()){
                    newproduct.setImage(file.getOriginalFilename());
                    System.out.println("sao"+file.getOriginalFilename());
                }else {
                    newproduct.setImage(product.getImage());
                    System.out.println(product.getImage());
                }
                productDAO.save(newproduct);
                System.out.println("del lỗi gì");
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("category",product.getCategory());
            return "redirect:/admin/product/read?category=0";
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

    @GetMapping("/oderwaiting")
    public String getOdwaiting(Model model){
        List<Oder> oders = oderdao.getWaiting(1);
        model.addAttribute("oders",oders);
        System.out.println("alo");
        return "/Template/Admin/odwaiting";
    }

    @GetMapping("/detailsorder{id}")
    public String getDetailsOrder(Model model,@RequestParam("id") int id){
        oder = oderdao.findById(id).get();
        oderdetalls  = oderdetallDao.getOderdetallsByOder(oder);
        model.addAttribute("oder",oder);
        model.addAttribute("oddetails",oderdetalls);
        return "/Template/Admin/DeltailsOD";
    }

    @GetMapping("/detailsorder/confirm{cf}")
    public String getDetailsOrder(@RequestParam("cf") int cf){
        if (cf==1){
            oder.setStatus(2);
            for (Oderdetall oderdetall:oderdetalls){
                Product newproduct = oderdetall.getProduct();
                newproduct.setQuantity(newproduct.getQuantity()-oderdetall.getQuantity());
                productDAO.save(product);
            }
            oderdao.save(oder);
        }else if (cf==0){
            oderdetallDao.deleteAll(oderdetalls);
            oderdao.delete(oder);
        }else if (cf==2){
            oder.setStatus(3);
            oderdao.save(oder);
        }else if (cf==-1){
            for (Oderdetall oderdetall:oderdetalls){
                Product newproduct = oderdetall.getProduct();
                newproduct.setQuantity(newproduct.getQuantity()+oderdetall.getQuantity());
                productDAO.save(product);
            }
            oderdetallDao.deleteAll(oderdetalls);
            oderdao.delete(oder);
        }
        return "redirect:/admin/oderwaiting";
    }

}
