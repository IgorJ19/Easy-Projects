package com.example.zadanie1.controller.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProjektyController {
    @RequestMapping("/projekty")
    public ModelAndView Cv(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("projekty.html");
        return modelAndView;
    }

    @RequestMapping("/projekty-eng")
    public ModelAndView CvEng(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("projekty-eng.html");
        return modelAndView;
    }

}