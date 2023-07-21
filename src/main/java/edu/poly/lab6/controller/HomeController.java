package edu.poly.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    UserDetailsService service;

    @GetMapping("/home/index")
    public String index(Model model) {

        Authentication authen = SecurityContextHolder.getContext().getAuthentication();
        if (authen == null) {

            System.out.println("Chưa đăng nhập rồi cha");
        } else {
            user =  service.loadUserByUsername(authen.getName());
        }
        model.addAttribute("user", user);
        model.addAttribute("message", "This is home page");
        return "home/index";
    }

    @RequestMapping("/home/about")
    public String about(Model model) {
        model.addAttribute("message", "This is introduction page");
        return "home/index";
    }

}
