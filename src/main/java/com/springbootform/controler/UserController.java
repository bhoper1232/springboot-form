package com.springbootform.controler;

import com.springbootform.entity.UserEntity;
import com.springbootform.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerUser", new UserEntity());
        return "register_page";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginUser", new UserEntity());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerUser") UserEntity userEntity) {
        UserEntity newUser = userService.registerUser(userEntity.getLogin(), userEntity.getPassword(), userEntity.getEmail());
        if (newUser == null) {
            return "redirect:/";
        }
        return "login_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginUser") UserEntity userEntity, Model model) {
        UserEntity authorizedUser = userService.authenticate(userEntity.getLogin(), userEntity.getPassword());
        if (authorizedUser == null) {
            return "redirect:/";
        }
        model.addAttribute("userLogin", userEntity.getLogin());
        return "user-info";
    }


}
