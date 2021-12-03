package com.example.managefood.repository;

import com.example.managefood.model.Account;
import com.example.managefood.model.DetailsCart;
import com.example.managefood.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    // danh sách sản phẩm có phân trang
    @Query(value = "SELECT * FROM manage_food.product",nativeQuery = true)
    Page<Product> findAllByProduct(Pageable pageable);


    // tìm kiếm sản phẩm theo tên sản phẩm có phân trang
    @Query(value = "SELECT * from product where name like %?1%",nativeQuery = true)
    Page<Product> findByNameProduct(String name, Pageable pageable);

    // tìm kiếm sản phẩm
    @Query(value = "SELECT * from product where name=:name", nativeQuery = true)
    Product findProductByName(@Param("name") String name);

    // danh sách sản phẩm
    @Query(value = "SELECT * FROM manage_food.product;",nativeQuery = true)
    List<Product> getAllByProduct();

    // hiển thị sản phẩm theo id
    @Query(value = "select * from product  " +
            "where product.id = ?1 ", nativeQuery = true)
    Product getByIdProduct(long id);

    // Thêm mới sản phẩm
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `manage_food`.`product` ( `name`,`image_url`,`price`,`description`, `category_product_id`) VALUES (?1,?2,?3,?4,?5)",nativeQuery = true)
    void createProduct(String name,String imageUrl,long price,String description,long category_product_id);

    // Chỉnh sửa sản phẩm
    @Transactional
    @Modifying
    @Query(value = "UPDATE `manage_food`.`product` SET `description` = ?1, `image_url` = ?2, `name` = ?3, `price` = ?4 , `category_product_id` = ?5  WHERE (`id` = ?6 )",nativeQuery = true)
    void updateProduct(String description,String imageUrl,String name,long price,long category_product_id,long id);

    //Xóa sản phẩm theo id
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `manage_food`.`product` WHERE product.id = ?1", nativeQuery = true)
    void deleteByIdProduct(Long id);

    //Xóa sản phẩm trong giỏ hàng
    @Transactional
    @Modifying
    @Query(value = " DELETE FROM `manage_food`.`details_cart` WHERE  details_cart.id = ?1 ", nativeQuery = true)
    void deleteByIdCartProduct(long product_id);

    //Hiển thị sản phẩm trong giỏ hàng theo id
    @Query(value = " SELECT * FROM manage_food.details_cart where details_cart.product_id = ?1 ",nativeQuery = true)
    List<DetailsCart>  getCartById(long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `manage_food`.`details_cart` WHERE  details_cart.product_id = ?1", nativeQuery = true)
    void deleteByIdCartIdProduct(Long account_id);
}
