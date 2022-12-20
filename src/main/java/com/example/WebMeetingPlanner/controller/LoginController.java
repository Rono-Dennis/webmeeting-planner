package com.example.WebMeetingPlanner.controller;

import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
 public class LoginController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping(value = "/login")
    public String login (){

        return "signin_form";
    }


    // handler method to handle login request
    /*@GetMapping("/login")
    public String login(){
        return "login";
    }*/

    // Login form with error
    @GetMapping("/login?error")
    public String loginError(Model model) {
         model.addAttribute("loginError", "Your account has been locked due to 3 failed attempts."
                 + " It will be unlocked after 24 hours.");
        return "signin_form";
    }
}
