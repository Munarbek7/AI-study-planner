// 1. AuthService.java
package com.study.planner.service;

import com.study.planner.model.User;
import com.study.planner.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Пользователь с таким Email уже существует!");
        }
        // В реальном проекте тут должен быть: passwordEncoder.encode(user.getPassword())
        user.setRole("University Student");
        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Неверный Email или пароль"));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Неверный Email или пароль");
        }
        return user; // Возвращаем сессию пользователя
    }
}