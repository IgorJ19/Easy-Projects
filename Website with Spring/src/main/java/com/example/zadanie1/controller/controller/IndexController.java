package com.example.zadanie1.controller.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {
    @RequestMapping("/home-eng")
    public ModelAndView Cv(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index-eng.html");
        return modelAndView;
    }

}