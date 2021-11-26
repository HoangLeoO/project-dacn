package com.example.managefood.repository;

import com.example.managefood.model.DetailsCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsCartRepository extends JpaRepository<DetailsCart,Long> {
    @Query(value = "SELECT * FROM details_cart inner join `account` " +
            "on details_cart.account_id = account.id " +
            "inner join product on details_cart.product_id = product.id where account_id = ?1 ;",nativeQuery = true)
    List<DetailsCart> getListCart(Long id);

    @Query(value = "SELECT * FROM manage_food.details_cart where account_id = ?1 ;",nativeQuery = true)
    List<DetailsCart> getListCartTest(Long id);

}
