package com.yakshjethva.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com/yakshjethva/usermanagement/model")  // Add this line to scan the "model" package
@EnableJpaRepositories(basePackages = "com.yakshjethva.usermanagement.repository") // Explicitly specify the package
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
