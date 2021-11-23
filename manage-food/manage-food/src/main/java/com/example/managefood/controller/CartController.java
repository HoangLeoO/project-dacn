package com.example.managefood.controller;

import com.example.managefood.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CartController {

    @Autowired
    private AccountRepository accountRepository ;

}
