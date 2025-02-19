package com.yakshjethva.usermanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String email;

	private String name;
	private String address;

	@Column(nullable = false)
	private boolean deleted = false;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDate;

	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updatedDate;
}