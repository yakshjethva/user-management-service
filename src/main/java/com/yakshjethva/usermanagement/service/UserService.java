package com.yakshjethva.usermanagement.service;

import com.yakshjethva.usermanagement.exception.UserNotFoundException;
import lombok.Data;
import com.yakshjethva.usermanagement.model.Role;
import com.yakshjethva.usermanagement.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.yakshjethva.usermanagement.repository.UserRepository;

import java.time.LocalDateTime;

@Data
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User registerUser(String username, String password, String email, String name, String address, Role role) {
		if (userRepository.existsByUsername(username)) {
			throw new IllegalArgumentException("Username already exists");
		}
		if (userRepository.existsByEmail(email)) {
			throw new IllegalArgumentException("Email already exists");
		}

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setName(name);
		user.setAddress(address);
		user.setRole(role != null ? role : Role.USER);
		user.setDeleted(false);
		user.setCreatedDate(LocalDateTime.now());
		user.setUpdatedDate(LocalDateTime.now());

		return userRepository.save(user);
	}


	public User updateUserProfile(Long userId, User updatedUser) {
		return userRepository.findById(userId).map(user -> {
			if (updatedUser.getName() != null) {
				user.setName(updatedUser.getName());
			}
			if (updatedUser.getAddress() != null) {
				user.setAddress(updatedUser.getAddress());
			}
			if (updatedUser.getEmail() != null) {
				user.setEmail(updatedUser.getEmail());
			}
			user.setUpdatedDate(LocalDateTime.now());
			return userRepository.save(user);
		}).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
	}

	public User getUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
	}
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
	}

	public void softDeleteUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
		user.setDeleted(true);
		user.setUpdatedDate(LocalDateTime.now());
		userRepository.save(user);
	}
}
