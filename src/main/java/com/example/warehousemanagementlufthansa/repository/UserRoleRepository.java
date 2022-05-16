package com.example.warehousemanagementlufthansa.repository;

import com.example.warehousemanagementlufthansa.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
