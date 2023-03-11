package com.jonesman.shop.controller;

import com.jonesman.shop.entity.ProductEntity;
import com.jonesman.shop.model.Product;
import com.jonesman.shop.repository.ProductRepository;
import com.jonesman.shop.repository.UserRepository;
import com.jonesman.shop.services.ProductService;
import com.jonesman.shop.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public ProductController(ProductService productService,
                             ProductRepository productRepository, UserRepository userRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.userRepository = userRepository;


    }


    @GetMapping("/")
    public String showHomePage(Model model) {
        long itemCount = productRepository.count();
        long userCount = userRepository.count();
        Double totalProductPrice = productRepository.getTotalProductPrice();

        model.addAttribute("itemCount", itemCount);
        model.addAttribute("userCount", userCount);
        model.addAttribute("totalProductPrice", totalProductPrice);

        return findPaginated(1, "productCode", "asc", model);

    }


    @GetMapping("/retail/form")
    public String showNewProductFormPage(Model model) {
        long itemCount = productRepository.count();
        long userCount = userRepository.count();
        Double totalProductPrice = productRepository.getTotalProductPrice();

        model.addAttribute("itemCount", itemCount);
        model.addAttribute("userCount", userCount);
        model.addAttribute("totalProductPrice", totalProductPrice);
        model.addAttribute("product", new Product());
        return "retail/product-form";
    }

    @PostMapping("/retail/form")
    public String saveProduct(@ModelAttribute("product") ProductEntity productEntity, Model model) {
        long itemCount = productRepository.count();
        long userCount = userRepository.count();
        Double totalProductPrice = productRepository.getTotalProductPrice();

        model.addAttribute("itemCount", itemCount);
        model.addAttribute("userCount", userCount);
        model.addAttribute("totalProductPrice", totalProductPrice);
        //save product to database
        productService.saveProduct(productEntity);
        return "redirect:/retail";
    }

    @GetMapping("/retail/edit/{id}")
    public String showEditProductFormPage(@PathVariable(value = "id") long id, Model model) {
        //get product from service
        ProductEntity productEntity = productService.getProductById(id);

        //set product as a model attribute to pre-populate the form
        model.addAttribute("product", productEntity);
        return "retail/product-form";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 8;

        Page<ProductEntity> page = productService.findPagination(pageNo, pageSize, sortField, sortDir);
        List<ProductEntity> productEntityList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("productEntityList", productEntityList);
        return "index";
    }


    @GetMapping("/retail/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id) {
        this.productService.deleteProductById(id);
        return "redirect:/retail";
    }

    @GetMapping("/wholesale")
    public String showWholeSalePage() {
        return "wholesale/index";
    }


    @GetMapping("/retail")
    public String showRetailTablePage(Model model) {
        long itemCount = productRepository.count();
        long userCount = userRepository.count();
        Double totalProductPrice = productRepository.getTotalProductPrice();

        model.addAttribute("itemCount", itemCount);
        model.addAttribute("userCount", userCount);
        model.addAttribute("totalProductPrice", totalProductPrice);
        findPaginated(1, "productCode", "asc", model);

        return "retail/index";

    }

    @GetMapping("/retail/page/{pageNo}")
    public String findRetailPaginated(@PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("sortField") String sortField,
                                      @RequestParam("sortDir") String sortDir,
                                      Model model) {
        int pageSize = 5;

        Page<ProductEntity> page = productService.findPagination(pageNo, pageSize, sortField, sortDir);
        List<ProductEntity> productEntityList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("productEntityList", productEntityList);
        return "retail/index";
    }


//        @GetMapping("/auth/login")
//        public String showLoginPage(){
//            return "auth/login";
//        }

//        @GetMapping("/auth/signup")
//        public String showSignUpPage(){
//            return "auth/signup";
//        }


}
