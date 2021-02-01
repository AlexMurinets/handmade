package com.example.handmadeBackend.bootstrap;

import com.example.handmadeBackend.model.Category;
import com.example.handmadeBackend.model.User;
import com.example.handmadeBackend.repository.CategoryRepository;
import com.example.handmadeBackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Bootstrap implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("Jonny");
        userRepository.save(user);

        Category category  = new Category();
        category.setName("Porno");
        categoryRepository.save(category);
    }
}
