package com.dnb.smartsaver.repository;

import com.dnb.smartsaver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}