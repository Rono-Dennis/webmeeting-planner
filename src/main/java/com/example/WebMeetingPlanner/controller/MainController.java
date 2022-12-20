package com.example.WebMeetingPlanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adminPage")
public class MainController {

    @GetMapping("")
    public ModelAndView adminPage(){
        return new ModelAndView("redirect:/adminPage/user_form");
    }
}
