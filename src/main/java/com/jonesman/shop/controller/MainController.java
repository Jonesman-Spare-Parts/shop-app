package com.jonesman.shop.controller;

import com.jonesman.shop.repository.ProductRepository;
import com.jonesman.shop.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public MainController(ProductRepository productRepository,
                          UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/auth/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/auth/profile")
    public String showUserProfile() {
        return "auth/profile";
    }

    @GetMapping("/auth/settings")
    public String showUserSettings() {
        return "auth/settings";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @GetMapping("/assets/img/register_bg_2.png?continue")
    public String redirectToHomePage() {
        return "redirect:/";
    }


}
