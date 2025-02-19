package com.yakshjethva.usermanagement.controller;

import com.yakshjethva.usermanagement.dto.*;
import com.yakshjethva.usermanagement.model.User;
import com.yakshjethva.usermanagement.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")

public class UserController {
	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto registrationDto) {
		User user = userService.registerUser(
				registrationDto.getUsername(),
				registrationDto.getPassword(),
				registrationDto.getEmail(),
				registrationDto.getName(),
				registrationDto.getAddress(),
				registrationDto.getRole()
		);
		return ResponseEntity.ok(user);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody UserLoginDto loginDto) {
		return ResponseEntity.ok("Login successful" );
	}

	@PutMapping("/{userId}/profile")
	public ResponseEntity<User> updateProfile(@PathVariable Long userId, @RequestBody UserProfileDto profileDto) {
		User updatedUser = new User();
		updatedUser.setName(profileDto.getName());
		updatedUser.setAddress(profileDto.getAddress());
		updatedUser.setEmail(profileDto.getEmail());
		User user = userService.updateUserProfile(userId, updatedUser);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserProfile(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		userService.softDeleteUser(userId);
		return ResponseEntity.noContent().build();
	}
}
