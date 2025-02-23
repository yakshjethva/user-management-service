package com.yakshjethva.usermanagement.controller;

import com.yakshjethva.usermanagement.dto.*;
import com.yakshjethva.usermanagement.dto.Response.ErrorResponse;
import com.yakshjethva.usermanagement.dto.Response.UserLoginResponse;
import com.yakshjethva.usermanagement.model.User;
import com.yakshjethva.usermanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.yakshjethva.usermanagement.Auth.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;


@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;


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
	public ResponseEntity loginUser(@RequestBody UserLoginDto loginDto) {
		try {
			Authentication authentication =
					authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
			String username = authentication.getName();
			User user = userService.getUserByUsername(username);
			String token = jwtUtil.createToken(user);
			return ResponseEntity.ok(token);

		}catch (BadCredentialsException e){
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}catch (Exception e){
			ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
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
