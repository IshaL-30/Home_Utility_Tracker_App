package com.example.homeutility.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.homeutility.core.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByEmail(String email);

}
