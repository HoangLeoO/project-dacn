package com.example.managefood.service.impl;

import com.example.managefood.model.Account;
import com.example.managefood.repository.AccountRepository;
import com.example.managefood.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }

    @Override
    public Account findUserByUsername(String username) {
        return accountRepository.findUserByUsername(username);
    }

    @Override
    public String findRoleNameByUsername(String username) {
        return accountRepository.findRoleNameByUsername(username);
    }

    @Override
    public Account getAccountById(long id) {
        return accountRepository.getAccountById(id);
    }

    @Override
    public void createAccountRole(long accountId, long roleId) {
        accountRepository.createAccountRole(accountId,roleId);
    }

    @Override
    public void createAccount(String address, String email, String fullname, String password, String phone, String username) {
        accountRepository.createAccount(address,email,fullname,password,phone,username);
    }

    @Override
    public void updateAccount(String address, String email, String fullname, String password, String phone, String username, long id) {
        accountRepository.updateAccount(address,email,fullname,password,phone,username,id);
    }

    @Override
    public void deleteByIdAccount(Long id) {
        accountRepository.deleteByIdAccount(id);
    }

    @Override
    public void deleteByIdAccountRole(Long id) {
        accountRepository.deleteByIdAccountRole(id);
    }
}
