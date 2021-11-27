package com.example.managefood.repository;

import com.example.managefood.model.DetailsCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DetailsCartRepository extends JpaRepository<DetailsCart,Long> {

    @Query(value = "SELECT * FROM manage_food.details_cart where details_cart.id = ?1",nativeQuery = true)
    DetailsCart getById (long id);

    @Query(value = "SELECT * FROM details_cart inner join `account` " +
            "on details_cart.account_id = account.id " +
            "inner join product on details_cart.product_id = product.id where account_id = ?1 ;",nativeQuery = true)
    List<DetailsCart> getListCart(Long id);

    // danh sách giỏ hàng theo người dùng
    @Query(value = "SELECT * FROM manage_food.details_cart where account_id = ?1 ",nativeQuery = true)
    List<DetailsCart> getListCartTest(Long id);

    //
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `manage_food`.`details_cart` (`quantity`, `account_id`, `product_id`) VALUES (?1, ?2, ?3)",nativeQuery = true)
    void addProduct(long quantity,long account_id,long product_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `manage_food`.`details_cart` SET `quantity` = ?1, `product_id` = ?2 WHERE (`id` = '7')",nativeQuery = true)
    void updateCart(long quantity,long product_id,long id);
}
