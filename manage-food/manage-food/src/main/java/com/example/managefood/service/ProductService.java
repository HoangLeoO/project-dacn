package com.example.managefood.service;

import com.example.managefood.model.DetailsCart;
import com.example.managefood.model.Product;
import com.example.managefood.model.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductService {

    //danh sách sản phẩm
    List<Product> getAllProduct();

    // hiển thị người dùng theo tên và id
    Product findProductByName(String name);
    Product getByIdProduct(long id);

    // thêm xóa sửa
    void createProduct(ProductDTO productDTO);
    void updateProduct(ProductDTO productDTO);
    void deleteByIdProduct(Long id);

    //Xóa sản phẩm trong giỏ hàng
    void deleteByIdCartProduct(long product_id);
    //Hiển thị sản phẩm trong giỏ hàng theo id
    List<DetailsCart>  getCartById(long id);

    //Tìm kiếm và Danh sách sản phẩm
    Page<Product> findAllByProduct(Pageable pageable);
    Page<Product> findByNameProduct(String name, Pageable pageable);


}
