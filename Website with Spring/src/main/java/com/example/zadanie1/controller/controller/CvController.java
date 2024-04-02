package com.example.zadanie1.controller.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CvController {
    @RequestMapping("/Cv")
    public ModelAndView Cv(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Cv.html");
        return modelAndView;
    }
    @RequestMapping("/Cv-eng")
    public ModelAndView CvEng(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Cv-eng.html");
        return modelAndView;
    }

}

