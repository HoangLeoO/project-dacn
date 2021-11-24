package com.example.managefood.config;

import com.example.managefood.model.Account;
import com.example.managefood.model.Role;
import com.example.managefood.repository.AccountRepository;
import com.example.managefood.repository.CategoryProductRepository;
import com.example.managefood.repository.ProductRepository;
import com.example.managefood.repository.RoleRepository;
import com.example.managefood.util.EncrypPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AccountRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CategoryProductRepository categoryProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (roleRepository.getByNameRole("ROLE_ADMIN")==null){
            Role role = new Role();
            role.setRoleName("ROLE_ADMIN");
            roleRepository.save(role);
        }
        if (roleRepository.getByNameRole("ROLE_MEMBER")==null){
            Role role = new Role();
            role.setRoleName("ROLE_MEMBER");
            roleRepository.save(role);
        }

        //them admin
        if (userRepository.findUserByUsername("admin") == null){
            Account admin = new Account();
            admin.setUsername("admin");
            admin.setPassword(EncrypPasswordUtils.EncrypPasswordUtils("123456"));
            userRepository.save(admin);
            Account accountDTO = userRepository.findUserByUsername("admin");
            Role role = new Role();
            role = roleRepository.getByNameRole("ROLE_ADMIN");
            userRepository.createAccountRole(accountDTO.getId(),role.getId());
        }

        //them member
        if (userRepository.findUserByUsername("member") == null){
            Account admin = new Account();
            admin.setUsername("member");
            admin.setPassword(EncrypPasswordUtils.EncrypPasswordUtils("123456"));
            admin.setFullname("member1");
            admin.setEmail("member@email.com");
            admin.setPhone("0932568991");
            admin.setAddress("To 48 Hoa Minh");
            userRepository.save(admin);
            Account accountDTO = userRepository.findUserByUsername("member");
            Role role = new Role();
            role = roleRepository.getByNameRole("ROLE_MEMBER");
            userRepository.createAccountRole(accountDTO.getId(),role.getId());
        }

    }
}
