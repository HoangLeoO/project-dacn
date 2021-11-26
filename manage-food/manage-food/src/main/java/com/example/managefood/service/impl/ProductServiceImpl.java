package com.example.managefood.service.impl;


import com.example.managefood.model.Product;

import com.example.managefood.model.dto.ProductDTO;
import com.example.managefood.repository.ProductRepository;
import com.example.managefood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl  implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAllProduct() {
        return productRepository.getAllByProduct();
    }

    @Override
    public Product findProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public Product getByIdProduct(long id) {
        return productRepository.getByIdProduct(id);
    }

    @Override
    public void createProduct(ProductDTO productDTO) {
        productRepository.createProduct(productDTO.getName(),productDTO.getImageUrl(),productDTO.getPrice(),
                productDTO.getDescription(),productDTO.getCategoryProduct());
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        productRepository.updateProduct(productDTO.getDescription(), productDTO.getImageUrl(), productDTO.getName(), productDTO.getPrice()
              , productDTO.getCategoryProduct(), productDTO.getId());

    }

    @Override
    public void deleteByIdProduct(Long id) {
        productRepository.deleteByIdProduct(id);
    }


}
