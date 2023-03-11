package com.jonesman.shop.controller;

import com.jonesman.shop.entity.ProductEntity;
import com.jonesman.shop.entity.UserEntity;
import com.jonesman.shop.model.User;
import com.jonesman.shop.repository.ProductRepository;
import com.jonesman.shop.repository.UserRepository;
import com.jonesman.shop.services.UserService;
import io.micrometer.core.instrument.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Valid
@Controller
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private final UserService userService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    public UserController(UserService userService, ProductRepository productRepository, UserRepository userRepository) {
        super();
        this.userService = userService;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    //    @ModelAttribute("users")
//    public User userRegistrationDto(){
//        return new User();
//    }
//    @GetMapping
//    public String showRegistrationForm(){
//        return "/auth/signup";
//    }
//
    @GetMapping("/users")
    public String showUsersPage(Model model) {
        long itemCount = productRepository.count();
        long userCount = userRepository.count();
        Double totalProductPrice = productRepository.getTotalProductPrice();

        model.addAttribute("itemCount", itemCount);
        model.addAttribute("userCount", userCount);
        model.addAttribute("totalProductPrice", totalProductPrice);
        return findPaginated(1, "userName", "asc", model);

    }

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("users", new UserEntity());
        return "auth/register";
    }

    @GetMapping("/signup/edit/{id}")
    public String showEditProductFormPage(@PathVariable(value = "id") long id, Model model) {

        //get product from service
        UserEntity userEntity = userService.getUserById(id);

        //set product as a model attribute to pre-populate the form
        model.addAttribute("user", userEntity);
        return "auth/register";
    }

//    @PostMapping
//    public String registerUserAccount(@ModelAttribute("users") User userRegistrationDto){
//        userService.save(userRegistrationDto);
//        System.out.println("form values " + userRegistrationDto + "\n");
//         return  "redirect:/auth/signup?success";
//    }


    @PostMapping("/signup")
    public String saveUser(@Valid @ModelAttribute("users") UserEntity userEntity, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        } else {
            try {
                userService.saveUser(userEntity);
                System.out.println("form values " + userEntity + "\n");
                return "redirect:/admin?signup";
            } catch (Exception e) {
                bindingResult.addError(new ObjectError("user", "An error occurred while saving the user."));
                return "auth/register";
            }
        }
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 8;

        Page<UserEntity> page = userService.findPagination(pageNo, pageSize, sortField, sortDir);
        List<UserEntity> userEntityList = page.getContent();

        model.addAttribute("currentUsersPage", pageNo);
        model.addAttribute("totalUsersPages", page.getTotalPages());
        model.addAttribute("totalUsers", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("userEntityList", userEntityList);
        return "auth/index";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        //userService.deleteUserById(id);
        System.out.println("delete id" + id);
        return "redirect:/";
    }

    @GetMapping("/users/{id}")
    public String getApproveUserForm(@PathVariable Long id, Model model) {
        userService.updateUserApproval(id);
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userEntity.setApproved(!userEntity.isApproved());
        userRepository.save(userEntity);


        return "redirect:/auth/users";
    }

//    @PostMapping("/users/{id}")
//    public String approveUser(@PathVariable Long id, @RequestParam("isApproved") boolean isApproved, Model model) {
//        UserEntity userEntity = userService.getUserById(id);
//        userEntity.setApproved(isApproved);
//        userService.saveUser(userEntity);
//
//        long itemCount = productRepository.count();
//        long userCount = userRepository.count();
//        Double totalProductPrice = productRepository.getTotalProductPrice();
//
//        model.addAttribute("itemCount", itemCount);
//        model.addAttribute("userCount", userCount);
//        model.addAttribute("totalProductPrice", totalProductPrice);
//
//        return findPaginated(1, "updatedAt", "asc", model);
//    }


    // other methods


}
