package com.example.demo.service;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.RegisterRequestDTO;
import com.example.demo.entity.Role;

public interface AuthService {
    LoginResponseDTO register(RegisterRequestDTO request, Role role, boolean isPending);
    LoginResponseDTO login(LoginRequestDTO request);
}