package com.airclaimbd.airclaimbackend.repository;

import com.airclaimbd.airclaimbackend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);

    Admin findByUsernameAndPassword(
            String username,
            String password
    );

}