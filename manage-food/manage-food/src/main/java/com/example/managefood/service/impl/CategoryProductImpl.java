package com.example.managefood.service.impl;

import com.example.managefood.model.CategoryProduct;
import com.example.managefood.repository.CategoryProductRepository;
import com.example.managefood.service.CategoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryProductImpl implements CategoryProductService {
    @Autowired
    private CategoryProductRepository categoryProductRepository;

    @Override
    public List<CategoryProduct> getAllByCategoryProduct() {
        return categoryProductRepository.getAllByCategoryProduct();
    }
}
