package com.study.planner.dto;

public class AuthRequest {
    private String email;
    private String password;

    // Пустой конструктор (обязателен для Spring Boot)
    public AuthRequest() {
    }

    // Конструктор с параметрами
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // РУЧНЫЕ ГЕТТЕРЫ И СЕТТЕРЫ
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}