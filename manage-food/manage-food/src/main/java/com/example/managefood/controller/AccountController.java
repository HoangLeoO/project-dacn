package com.example.managefood.controller;

import com.example.managefood.model.Account;
import com.example.managefood.model.Product;
import com.example.managefood.model.dto.AccountDTO;
import com.example.managefood.model.dto.ProductDTO;
import com.example.managefood.repository.AccountRepository;
import com.example.managefood.util.EncrypPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AccountController {


    @Autowired
    private AccountRepository accountRepository ;


    @GetMapping("/list-account")
    public ModelAndView getAllProduct() {
        List<Account> accounts = accountRepository.getAllAccount();
        ModelAndView modelAndView = new ModelAndView("/member/list","account",accounts);
        return modelAndView;
    }


    @GetMapping("account/{id}")
    public ResponseEntity<Account> getByIdAccount(@PathVariable("id") long id) {
        Account account = accountRepository.getAccountById(id);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/create-account")
    public ModelAndView FromCreateAccount(){
        ModelAndView modelAndView = new ModelAndView("/home/register","accountDTO",new AccountDTO());
        return modelAndView;
    }

    @PostMapping("/create-account")
    private ModelAndView createAccount(AccountDTO accountDTO){
        accountDTO.setPassword(EncrypPasswordUtils.EncrypPasswordUtils(accountDTO.getPassword()));
        accountRepository.createAccount(accountDTO.getAddress(),accountDTO.getEmail(),accountDTO.getFullname(),accountDTO.getPassword(),
                accountDTO.getPhone(),accountDTO.getUsername()
        );
        Account account = accountRepository.findUserByUsername(accountDTO.getUsername());
        System.out.println("Creating account " + account.getId());
        accountRepository.createAccountRole(account.getId(),2);
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        return modelAndView;
    }

    @PostMapping("/update-account")
    public ModelAndView updateAccount(@ModelAttribute("userDetails") Account userDetails) {
        accountRepository.updateAccount(userDetails.getAddress(),userDetails.getEmail(),userDetails.getFullname(),userDetails.getPassword(),
                userDetails.getPhone(),userDetails.getUsername(),userDetails.getId());
        System.out.println("update thanh cong");
        ModelAndView modelAndView = new ModelAndView("redirect:/accountInfo");
        return modelAndView;
    }


    @GetMapping("/delete-account/{id}")
    public ModelAndView showFromDelete(@PathVariable Long id){
        Account account = accountRepository.getAccountById(id);
        ModelAndView modelAndView = new ModelAndView("/member/delete");
        modelAndView.addObject("account" ,account);
        return  modelAndView;
    }

    @PostMapping("/delete-account")
    public ModelAndView delete(@ModelAttribute("account") Account account){
        System.out.println(
                "Xóa thành công " + account.getFullname());
        accountRepository.deleteByIdAccountRole(account.getId());
        accountRepository.deleteByIdAccount(account.getId());
        Account account1 = accountRepository.findUserByUsername(account.getUsername());

        ModelAndView modelAndView = new ModelAndView("redirect:/list-account");
        return modelAndView;
    }


    @RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());
        Account account = accountRepository.findUserByUsername(userDetails.getUsername());
        model.addAttribute("userDetails", account);
        return "/home/accountInfor";
    }


}
