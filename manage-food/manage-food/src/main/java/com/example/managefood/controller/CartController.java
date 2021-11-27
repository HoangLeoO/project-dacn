package com.example.managefood.controller;

import com.example.managefood.model.Account;
import com.example.managefood.model.CategoryProduct;
import com.example.managefood.model.DetailsCart;
import com.example.managefood.model.Product;
import com.example.managefood.model.dto.QuantityDTO;
import com.example.managefood.repository.AccountRepository;
import com.example.managefood.repository.DetailsCartRepository;
import com.example.managefood.service.CategoryProductService;
import com.example.managefood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountRepository accountRepository ;

    @Autowired
    private CategoryProductService categoryProductService;


    @Autowired
    private DetailsCartRepository detailsCartRepository;

    @ModelAttribute("categoryProducts")
    public Iterable<CategoryProduct> categoryProducts() {
        return categoryProductService.getAllByCategoryProduct();
    }


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

    @GetMapping("/add-product/{id}")
    public ModelAndView formAddProduct(@PathVariable Long id){
        Product product = productService.getByIdProduct(id);
        ModelAndView modelAndView = new ModelAndView("/cart/addProduct");
        modelAndView.addObject("product", product);
        modelAndView.addObject("quantity",new DetailsCart());
        return modelAndView;
    }

    @PostMapping("/add-product")
    public ModelAndView addProduct(@ModelAttribute("quantity") DetailsCart quantity,@ModelAttribute("product") Product product){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepository.findUserByUsername(userDetails.getUsername());
        System.out.println(account.getId());
        System.out.println(product.getId() + " " + quantity.getQuantity() + " " + account.getId());
        detailsCartRepository.addProduct(quantity.getQuantity(),account.getId(),product.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/list-product");
        return modelAndView;
    }


    @GetMapping("/update-quantity/{id}")
    public ModelAndView formUpdate(@PathVariable Long id){
        DetailsCart detailsCart = detailsCartRepository.getById(id);
        Product product = productService.getByIdProduct(detailsCart.getProduct().getId());
        ModelAndView modelAndView = new ModelAndView("/cart/updateCart");
        modelAndView.addObject("product", product);
        modelAndView.addObject("quantity",detailsCart);
        return modelAndView;
    }

    @PostMapping("/update-quantity")
    public ModelAndView updateQuantity(@ModelAttribute("quantity") DetailsCart quantity,@ModelAttribute("product") Product product){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepository.findUserByUsername(userDetails.getUsername());
        System.out.println(account.getId());
        System.out.println(product.getId() + " " + quantity.getQuantity() + " " + account.getId());
        detailsCartRepository.updateCart(quantity.getQuantity(),product.getId(),quantity.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/detail-cart");
        return modelAndView;
    }
}
