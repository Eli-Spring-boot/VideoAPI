package com.example.demofake;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demofake.UserAccount;

public interface UserManagementRepository extends JpaRepository<UserAccount, Long>{
}