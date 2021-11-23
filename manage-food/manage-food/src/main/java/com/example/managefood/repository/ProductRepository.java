package com.example.managefood.repository;

import com.example.managefood.model.Account;
import com.example.managefood.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT * from product where name=:name", nativeQuery = true)
    Product findProductByName(@Param("name") String name);

    @Query(value = "SELECT * FROM manage_food.product;",nativeQuery = true)
    List<Product> getAllByProduct();

    @Query(value = "select * from product  " +
            "where product.id = ?1 ", nativeQuery = true)
    Product getByIdProduct(long id);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `manage_food`.`product` ( `name`,`image_url`,`price`,`description`,`quantity`, `category_product_id`) VALUES (?1,?2,?3,?4,?5,?6)",nativeQuery = true)
    void createProduct(String name,String imageUrl,long price,String description,long quantity,long category_product_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `manage_food`.`product` SET `description` = ?1, `image_url` = ?2, `name` = ?3, `price` = ?4, `quantity` = ?5 , `category_product_id` = ?6  WHERE (`id` = ?7 )",nativeQuery = true)
    void updateProduct(String description,String imageUrl,String name,long price,long quantity,long category_product_id,long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `manage_food`.`product` WHERE (`id` = ?1);", nativeQuery = true)
    void deleteByIdProduct(Long id);
}
