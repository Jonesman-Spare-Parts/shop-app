package com.jonesman.shop.controller;

import com.jonesman.shop.entity.ProductEntity;
import com.jonesman.shop.model.Product;
import com.jonesman.shop.repository.ProductRepository;
import com.jonesman.shop.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService,
                             ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }


    @GetMapping("/")
        public String showHomePage(){
            return "index";
        }
       @GetMapping("/retail")
        public String showRetailPage(Model model){
            // model.addAttribute("products",productService.getAllProducts());
             return findPaginated(1,"productCode", "asc", model);
           // return "retail/index";
        }




    @GetMapping("/retail/form")
        public String showNewProductFormPage(Model model){
         model.addAttribute("product", new Product());
         return "retail/form";
        }

        @PostMapping("/retail/form")
        public String saveProduct(@ModelAttribute("product") ProductEntity productEntity){
        //save product to database
        productService.saveProduct(productEntity);
        return "redirect:/retail";
        }

        @GetMapping("/retail/edit/{id}")
        public String showEditProductFormPage(@PathVariable(value = "id") long id, Model model){

        //get product from service
         ProductEntity productEntity = productService.getProductById(id);

         //set product as a model attribute to pre-populate the form
         model.addAttribute("product", productEntity);
            return  "retail/form";
        }

         @GetMapping("/retail/page/{pageNo}")
        public String findPaginated(@PathVariable(value = "pageNo") int pageNo ,
                                    @RequestParam("sortField") String sortField,
                                    @RequestParam("sortDir") String sortDir,
                                    Model model){
            int pageSize = 8;

            Page<ProductEntity> page = productService.findPagination(pageNo, pageSize, sortField, sortDir);
            List<ProductEntity> productEntityList = page.getContent();

            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());

            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("reverseSortDir", sortDir.equals("asc")?  "desc" : "asc");

            model.addAttribute("productEntityList", productEntityList);
            return "retail/index";
        }

//         @PostMapping("/retail/form")
//        public String productSubmit(@ModelAttribute Product product , Model model) {
//            model.addAttribute("product", product);
//            productService.createProduct(product);
//            return "redirect:/retail";
//          }
//



       @GetMapping("/retail/{id}")
       public String deleteProduct(@PathVariable(value = "id") long id){
        this.productService.deleteProductById(id);

        return "redirect:/retail";
       }
        @GetMapping("/wholesale")
        public String showWholeSalePage(){
            return "wholesale/index";
        }

        @GetMapping("/auth/login")
        public String showLoginPage(){
            return "auth/login";
        }

//        @GetMapping("/auth/signup")
//        public String showSignUpPage(){
//            return "auth/signup";
//        }
 



}
