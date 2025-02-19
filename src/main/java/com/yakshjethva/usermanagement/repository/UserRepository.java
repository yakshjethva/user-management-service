package com.yakshjethva.usermanagement.repository;

import com.yakshjethva.usermanagement.model.Role;
import com.yakshjethva.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Optional<User> findByUsernameAndDeletedFalse(String username);
	Optional<User> findByEmailAndDeletedFalse(String email);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	List<User> findByRole(Role role);
	List<User> findByDeletedFalse();
	List<User> findByDeletedTrue();
}

