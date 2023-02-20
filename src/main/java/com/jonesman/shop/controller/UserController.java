package com.jonesman.shop.controller;

import com.jonesman.shop.entity.UserEntity;
import com.jonesman.shop.model.User;
import com.jonesman.shop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/signup")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    //    @ModelAttribute("users")
//    public User userRegistrationDto(){
//        return new User();
//    }
//    @GetMapping
//    public String showRegistrationForm(){
//        return "/auth/signup";
//    }
    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("users", new User());
        return "/auth/signup";
    }

//    @PostMapping
//    public String registerUserAccount(@ModelAttribute("users") User userRegistrationDto){
//        userService.save(userRegistrationDto);
//        System.out.println("form values " + userRegistrationDto + "\n");
//         return  "redirect:/auth/signup?success";
//    }

    @PostMapping
    public String saveUser(@ModelAttribute("users") UserEntity userEntity) {
        //save product to database
        userService.saveUser(userEntity);
        System.out.println("form values " + userEntity + "\n");
        return "redirect:/auth/signup?success";
    }
}
