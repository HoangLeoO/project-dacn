package com.example.managefood.service.impl;

import com.example.managefood.model.Account;
import com.example.managefood.repository.AccountRepository;
import com.example.managefood.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // query xuống database tìm user
        Account user=userRepository.findUserByUsername(username);

        // nếu user null
        if (user==null){
            System.out.println("User not found : " + username);
            throw new UsernameNotFoundException("User" + username+" was is not found in the database");
        }

        //có use => query xem role
        // [ROLE_USER, ROLE_ADMIN,..]
        //List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());
//        String roleNames =   userRepository.findRoleNameByUsername(username);
        List<String> roleNames = Collections.singletonList((this.userRepository.findRoleNameByUsername(username)));


        // Dựa vào list quyền trả về mình tạo đối tượng GrantedAuthority  của spring cho quyền đó
        //Một GrantedAuthority là một quyền được ban cho principal.

//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleNames);


        List<GrantedAuthority> grantList = new ArrayList<>();
        for (String role : roleNames) {
            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);
        }


        // tạo đối tượng UserDetails  của Spring và cung cấp các thông số name, password và quyền
        // UserDetails sẽ chứa đựng các thông tin cần thiết của user từ đó giúp SPring Security quản lí
        // được phân quyền như configSecurity


        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                username,user.getPassword(),grantList);
        return userDetails;
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(), user.getPassword(), Collections.singleton(authority));
    }
}