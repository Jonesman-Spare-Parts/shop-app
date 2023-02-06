package com.jonesman.shop.controller;

import com.jonesman.shop.model.Product;
import com.jonesman.shop.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/")
        public String showHomePage(){
            return "index";
        }
       @GetMapping("/retail")
        public String showRetailPage(Model model){
             model.addAttribute("products",productService.getAllProducts());

            return "retail/index";
        }

        @GetMapping("/retail/form")
        public String showNewProductFormPage(Model model){
         model.addAttribute("product", new Product());
         return "retail/form";
        }
         @PostMapping("/retail/form")
        public String productSubmit(@ModelAttribute Product product , Model model) {
            model.addAttribute("product", product);
            productService.createProduct(product);
            return "redirect:/retail";
          }


       @GetMapping("/retail/{id}")
       public String deleteProduct(@PathVariable(value = "id") long id){
        this.productService.deleteProductById(id);

        return "redirect:/retail";
       }
        @GetMapping("/wholesale")
        public String showWholeSalePage(){
            return "wholesale/index";
        } @GetMapping("/auth/login")
        public String showLoginPage(){
            return "auth/login";
        }

        @GetMapping("/auth/signup")
        public String showSignUpPage(){
            return "auth/signup";
        }
 



}
