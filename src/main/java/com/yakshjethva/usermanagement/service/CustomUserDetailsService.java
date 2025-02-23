package com.yakshjethva.usermanagement.service;

//import com.example.springjwt.model.User;
//import com.example.springjwt.repositories.UserRepository;
import com.yakshjethva.usermanagement.model.User;
import com.yakshjethva.usermanagement.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        UserDetails userDetails = null;
        if(user.isPresent()) {
            List<String> roles = new ArrayList<>();
            roles.add("USER");
            userDetails =
                    org.springframework.security.core.userdetails.User.builder()
                            .username(user.get().getUsername())
                            .password(user.get().getPassword())
                            .roles(roles.toArray(new String[0]))
                            .build();
        }
        return userDetails;
    }
}