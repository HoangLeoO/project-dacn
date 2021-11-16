package com.example.dacn.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("/product/index");
        return modelAndView;
    }

    @GetMapping("/cart")
    public ModelAndView cart(){
        ModelAndView modelAndView = new ModelAndView("/check/cart");
        return modelAndView;
    }

    @GetMapping("/test")
    public ModelAndView getIndex(){
        ModelAndView modelAndView = new ModelAndView("/layout/header");
        return modelAndView;
    }

    @GetMapping("/test1")
    public ModelAndView getFood(){
        ModelAndView modelAndView = new ModelAndView("/layout/fooder");
        return modelAndView;
    }
    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("/product/list");
        return modelAndView;
    }
    @GetMapping("/navbar")
    public ModelAndView navbar(){
        ModelAndView modelAndView = new ModelAndView("/layout/navbar");
        return modelAndView;
    }
    @GetMapping("/details")
    public ModelAndView details(){
        ModelAndView modelAndView = new ModelAndView("/product/details");
        return modelAndView;
    }
}
