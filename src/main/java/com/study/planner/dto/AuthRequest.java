// 1. AuthRequest.java
package com.study.planner.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}