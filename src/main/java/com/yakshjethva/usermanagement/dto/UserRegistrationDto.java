package com.yakshjethva.usermanagement.dto;

import lombok.Data;
import com.yakshjethva.usermanagement.model.Role;

@Data
public class UserRegistrationDto {
	private String username;
	private String password;
	private String email;
	private String name;
	private String address;
	private Role role = Role.USER; // Default role is USER
}