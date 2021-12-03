package com.example.managefood.controller;

import com.example.managefood.model.CategoryProduct;
import com.example.managefood.model.DetailsCart;
import com.example.managefood.model.Product;
import com.example.managefood.model.dto.ProductDTO;
import com.example.managefood.repository.ProductRepository;
import com.example.managefood.service.CategoryProductService;
import com.example.managefood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryProductService categoryProductService;

    @Autowired
    private ProductRepository productRepository;

    @ModelAttribute("categoryProducts")
    public Iterable<CategoryProduct> categoryProducts() {
        return categoryProductService.getAllByCategoryProduct();
    }

    // Thông tin chi tiết sản phẩm
    @GetMapping("/details-product/{id}")
    public ModelAndView showDetailsProduct(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/product/details");
        modelAndView.addObject("product", productService.getByIdProduct(id));
        return modelAndView;
    }


    @GetMapping("/list-product")
    public ModelAndView getHome(@RequestParam Optional<String> key_search, @PageableDefault(value = 2) Pageable pageable, Model model){
        if(!key_search.isPresent()){
            return new ModelAndView("/product/list", "products", productRepository.findAllByProduct(pageable));
        }else {
            model.addAttribute("key_search", key_search.get());
            return new ModelAndView("/product/list", "products", productRepository.findByNameProduct(key_search.get(), pageable));
        }
    }


    @GetMapping("/create-product")
    public ModelAndView FromCreateProduct() {
        ModelAndView modelAndView = new ModelAndView("/product/create", "productFrom", new ProductDTO());
        return modelAndView;
    }


    @PostMapping("/create-product")
    public ModelAndView create(ProductDTO productDTO,@RequestParam("fileImg") MultipartFile photo) {
        Path path = Paths.get("src/main/resources/static/CSS/image/");
        System.out.println(photo.getOriginalFilename());
        try {
            InputStream inputStream = photo.getInputStream();
            Files.copy(inputStream, path.resolve(photo.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        productDTO.setImageUrl(photo.getOriginalFilename());
        System.out.println("Creating product " + productDTO.getName());
        ModelAndView modelAndView = new ModelAndView("redirect:/list-product");

        productService.createProduct(productDTO);

        return modelAndView;
    }

//    @RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
//    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
//                             @RequestParam("id") long id) throws IOException {
//        Product product = null;
//        if (id != 0) {
//            product = productService.getByIdProduct(id);
//        }
//        if (product != null && product.getImageUrl() != null) {
//            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
//            response.getOutputStream().write(product.getImageUrl());
//        }
//        response.getOutputStream().close();
//    }


    @GetMapping("/edit-product/{id}")
    public ModelAndView showFromEdit(@PathVariable Long id) {
        Product product = productService.getByIdProduct(id);
        ModelAndView modelAndView = new ModelAndView("/product/update");
        modelAndView.addObject("product", product);
        modelAndView.addObject("productType", categoryProductService.getAllByCategoryProduct());
        return modelAndView;
    }

    @PostMapping("/update-product")
    public ModelAndView updateProduct(ProductDTO product) {

        productService.updateProduct(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/list-product");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showFromDelete(@PathVariable Long id) {
        Product product = productService.getByIdProduct(id);
        ModelAndView modelAndView = new ModelAndView("/product/delete");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/delete-product")
    public ModelAndView delete(@ModelAttribute("product") Product product) {
        System.out.println(
                "Xóa thành công " + product.getId());
//        if(!productRepository.getCartById(product.getId()).isEmpty()){
//            productRepository.deleteByIdCartByProduct(product.getId());
//        }
        productRepository.deleteByIdCartIdProduct(product.getId());
        productService.deleteByIdProduct(product.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/list-product");
        return modelAndView;
    }
}
