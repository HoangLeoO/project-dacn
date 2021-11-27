package com.example.managefood.service;

import com.example.managefood.model.Product;
import com.example.managefood.model.dto.ProductDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product findProductByName(String name);

    Product getByIdProduct(long id);

    void createProduct(ProductDTO productDTO);

    void updateProduct(ProductDTO productDTO);

    void deleteByIdProduct(Long id);

    void deleteByIdCartProduct(Long product_id);


}
