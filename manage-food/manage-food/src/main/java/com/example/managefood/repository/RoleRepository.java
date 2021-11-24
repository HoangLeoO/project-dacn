package com.example.managefood.repository;

import com.example.managefood.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query(value = "select * from manage_food.role where role.role_name = ?1",nativeQuery = true)
    Role getByNameRole(String roleName);

    @Query(value = "select role.id from manage_food.role where role.role_name = ?1",nativeQuery = true)
    Role getIdByNameRole(String roleName);

}
