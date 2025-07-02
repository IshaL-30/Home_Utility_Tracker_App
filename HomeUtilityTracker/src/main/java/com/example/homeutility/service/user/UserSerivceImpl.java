package com.example.homeutility.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.homeutility.core.model.User;
import com.example.homeutility.repository.user.UserRepository;

@Service
public class UserSerivceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		Optional<User> existing = userRepository.findByEmail(user.getEmail());
	    if (existing.isPresent()) {
	        throw new RuntimeException("Email is already registered.");
	    }

	    String hashedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(hashedPassword);
	    return userRepository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	
}
