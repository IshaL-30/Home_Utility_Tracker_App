package com.example.homeutility.service.user;

import java.util.Optional;

import com.example.homeutility.core.model.User;

public interface UserService {

	User registerUser(User user);
	Optional<User> findByEmail(String email);
}
