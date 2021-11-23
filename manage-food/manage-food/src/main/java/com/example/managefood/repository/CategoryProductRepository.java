package com.example.managefood.repository;

import com.example.managefood.model.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryProductRepository extends JpaRepository<CategoryProduct,Long> {

    @Query(value = "SELECT * FROM category_product",nativeQuery = true)
    List<CategoryProduct> getAllByCategoryProduct();

}
