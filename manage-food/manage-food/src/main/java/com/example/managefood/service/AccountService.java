package com.example.managefood.service;

import com.example.managefood.model.Account;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountService {
//
//    List<Account> getAllAccount();

    Account findUserByUsername( String username);

    String findRoleNameByUsername(String username);

    Account getAccountById(long id);

    void createAccountRole(long accountId, long roleId);
    void createAccount(String address,String email , String fullname,String password,String phone , String username);
    void updateAccount(String address,String email , String fullname,String password,String phone , String username,long id);
    void deleteByIdAccount(Long id);
    void deleteByIdAccountRole(Long id);
}
