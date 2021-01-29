package com.example.handmadeBackend.bootstrap;

import com.example.handmadeBackend.model.User;
import com.example.handmadeBackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Bootstrap implements CommandLineRunner {
    BCryptPasswordEncoder passwordEncoder;
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("Test");
        user.setPassword(passwordEncoder.encode("Test"));
        userRepository.save(user);
        System.out.println(passwordEncoder.encode("Murdol"));
    }
}
