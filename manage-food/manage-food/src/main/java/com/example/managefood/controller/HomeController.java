package com.example.managefood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHome(){
        return new ModelAndView("home/home");
    }

    //login
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model){
        return new ModelAndView("home/login");
    }

    //page admin
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage(Model model, Principal principal){

//        User loginedUser =(User) ((Authentication)principal).getPrincipal();
//
//        String userInfo = WebUtils.toString(loginedUser);
        return new ModelAndView("home/admin");
    }

    //403
    @GetMapping("/403")
    public ModelAndView accessDenied() {
        return new ModelAndView("home/403");
    }

}
