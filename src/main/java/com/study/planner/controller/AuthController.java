package com.study.planner.controller;

import com.study.planner.dto.AuthRequest;
import com.study.planner.model.User;
import com.study.planner.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        // ИСПРАВЛЕНО: Сервис возвращает объект User, передаем его напрямую в JSON
        User user = authService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(user);
    }
}