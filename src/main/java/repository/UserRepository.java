package repository;

import model.Role;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

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

