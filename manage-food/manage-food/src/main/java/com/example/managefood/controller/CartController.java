package com.example.managefood.controller;

import com.example.managefood.model.Account;
import com.example.managefood.model.DetailsCart;
import com.example.managefood.repository.AccountRepository;
import com.example.managefood.repository.DetailsCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private AccountRepository accountRepository ;

    @Autowired
    private DetailsCartRepository detailsCartRepository;

    @GetMapping("/detail-cart")
    public ModelAndView getDetailCart(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepository.findUserByUsername(userDetails.getUsername());
        List<DetailsCart> detailsCart = detailsCartRepository.getListCartTest(account.getId());
        long tongtien = 0 ;
        for(DetailsCart mh:detailsCart){
            tongtien+=mh.getProduct().getPrice()*mh.getQuantity();
        }
        System.out.println(tongtien);
        ModelAndView modelAndView = new ModelAndView("/cart/listCart","details",detailsCart);
        modelAndView.addObject("tongtien",tongtien);
        return modelAndView;
    }

    @GetMapping("/payment-cart")
    public ModelAndView getPayment(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepository.findUserByUsername(userDetails.getUsername());
        List<DetailsCart> detailsCart = detailsCartRepository.getListCartTest(account.getId());
        long tongtien = 0 ;
        for(DetailsCart mh:detailsCart){
            tongtien+=mh.getProduct().getPrice()*mh.getQuantity();
        }
        System.out.println(tongtien);
        ModelAndView modelAndView = new ModelAndView("/cart/payment","details",detailsCart);
        modelAndView.addObject("tongtien",tongtien);
        modelAndView.addObject("account",account);
        return modelAndView;
    }
}
