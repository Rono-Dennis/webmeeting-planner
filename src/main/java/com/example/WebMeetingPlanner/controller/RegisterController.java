package com.example.WebMeetingPlanner.controller;

import com.example.WebMeetingPlanner.Exceptions.UserNotFoundExceptionn;
import com.example.WebMeetingPlanner.Model.*;

import com.example.WebMeetingPlanner.Repository.RoleRepository;

import com.example.WebMeetingPlanner.Repository.UserRepository;
import com.example.WebMeetingPlanner.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    private UserRepository repo;


    @Autowired
    private UserService userService;








//    @PostMapping("/process_register")
//    public String processRegistration(User user) {
//        userService.saveUserWithDefaultRole(user);
//
//
//        return "register_success";
//    }

    @GetMapping("/register")
    public String main(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }
//    @GetMapping("/login-error")
//    public String loginError(Model model) {
//        model.addAttribute("registerError", true);
//        return "login";
//    }





//    @GetMapping("/register")
//    public String showSignUpForm(Model model) {
//        model.addAttribute("user", new User());
//        return "signup_form";
//    }
//
//
//
//    @GetMapping("/process_register")
//    public String Process(Model model) {
//        List<User> listUsers = userService.listAll();
////        List<User> listUsers=repo.findAll();
//        model.addAttribute("listUsers", listUsers);
//        return "registered";
//    }


    @PostMapping("/process_register")
    public ModelAndView SaveUser(ModelAndView modelAndView,@ModelAttribute("user") @Valid final User user, BindingResult bindingResult                              ) {
    User emailExists = repo.findByEmail(user.getEmail());
    //  System.out.println(emailExists);
        if (emailExists != null || bindingResult.hasErrors() ) {
            modelAndView.setViewName("/register");
            bindingResult.rejectValue("email", "emailAlreadyExists");
            modelAndView.setViewName("/register");
        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("/register");
//        }

        else {

//            User userr = userService.saveUserWithDefaultRole(user);


            userService.saveUserWithDefaultRole(user);

            modelAndView.setViewName("/registered");

        }
        return modelAndView;
    }












//    @PostMapping("/process_register")
//    public String addusers(HttpServletRequest request, Model model)
//    {
//
////        userService.createUser(user);
//
//
//        String email = request.getParameter("email");
//
//        try {
//            userService.checkemail(email);
////            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
//
//        } catch (UserNotFoundExceptionn e) {
//            e.printStackTrace();
////            model.addAttribute("error", e.getMessage());
//        }
//
//
//        return "forget_password";
//    }












}
