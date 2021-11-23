package com.example.managefood.repository;

import com.example.managefood.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    @Query(value = "select * from `account` inner join account_role on account.id = account_role.account_id \n" +
            "where account_role.role_id = 2 ",nativeQuery = true)
    List<Account>  getAllAccount();

    @Query(value = "SELECT * from account where username=:username", nativeQuery = true)
    Account findUserByUsername(@Param("username") String username);


    @Query(
            value = "SELECT role.role_name from account_role " +
                    " inner join account on account.id = account_role.account_id " +
                    " inner join role on role.id = account_role.role_id where account.username=:username "
            , nativeQuery = true
    )
    String findRoleNameByUsername(@Param("username") String username);


    @Query(value = "select * from account where account.id = ?1 ", nativeQuery = true)
    Account getAccountById(long id);

    @Transactional
    @Modifying
    @Query(value = "insert into account_role(account_id, role_id) values (?1, ?2)", nativeQuery = true)
    void createAccountRole(long accountId, long roleId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `manage_food`.`account` (`address`, `email`, `fullname`, `password`, `phone`, `username`) VALUES (?1,?2, ?3,?4,?5,?6)",nativeQuery = true)
    void createAccount(String address,String email , String fullname,String password,int phone , String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `manage_food`.`account` SET `address` = ?1, `email` = ?2, `fullname` = ?3, `password` = ?4, `phone` = ?5, `username` =?6 WHERE (`id` = ?7)",nativeQuery = true)
    void updateAccount(String address,String email , String fullname,String password,int phone , String username,long id);


    //Xóa account theo id
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `manage_food`.`account` WHERE (`id` = ?1);", nativeQuery = true)
    void deleteByIdAccount(Long id);


    // Xóa quyền cho account theo id account
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `manage_food`.`account_role` WHERE account_role.account_id = ?1", nativeQuery = true)
    void deleteByIdAccountRole(Long id);
}
