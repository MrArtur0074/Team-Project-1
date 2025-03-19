package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AdminService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AdminServiceImpl(UserRepository userRepository, AdminRepository adminRepository,
                            SalonServiceRepository salonServiceRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.salonServiceRepository = salonServiceRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), List.of(() -> "ROLE_" + user.getRole())));
        return new LoginResponseDTO(token, user.getId(), user.getRole());
    }

    @Override
    public AdminProfileDTO getProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new AdminProfileDTO(user.getId(), user.getEmail(), user.getName(), user.getPhone(), user.getPhotoUrl());
    }

    @Override
    public AdminProfileDTO updateProfile(Long id, AdminProfileDTO request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(request.name());
        user.setPhone(request.phone());
        user.setPhotoUrl(request.photoUrl());
        userRepository.save(user);
        return new AdminProfileDTO(user.getId(), user.getEmail(), user.getName(), user.getPhone(), user.getPhotoUrl());
    }

    @Override
    public List<UserResponseDTO> getUsers(String role, String name, String email) {
        List<User> users = userRepository.findAll();
        if (role != null) users = users.stream().filter(u -> u.getRole().equals(role)).collect(Collectors.toList());
        if (name != null) users = users.stream().filter(u -> u.getName().contains(name)).collect(Collectors.toList());
        if (email != null) users = users.stream().filter(u -> u.getEmail().contains(email)).collect(Collectors.toList());
        return users.stream()
                .map(u -> new UserResponseDTO(u.getId(), u.getEmail(), u.getName(), u.getRole(), u.getPhone(), u.getPhotoUrl()))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getName(), user.getRole(), user.getPhone(), user.getPhotoUrl());
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserResponseDTO request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(request.email());
        user.setName(request.name());
        user.setRole(request.role());
        user.setPhone(request.phone());
        user.setPhotoUrl(request.photoUrl());
        userRepository.save(user);
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getName(), user.getRole(), user.getPhone(), user.getPhotoUrl());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDTO> getPendingMasters() {
        return userRepository.findByRoleAndIsPendingTrue("MASTER")
                .stream()
                .map(u -> new UserResponseDTO(u.getId(), u.getEmail(), u.getName(), u.getRole(), u.getPhone(), u.getPhotoUrl()))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO approveMaster(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsPending(false);
        userRepository.save(user);
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getName(), user.getRole(), user.getPhone(), user.getPhotoUrl());
    }

    @Override
    public UserResponseDTO rejectMaster(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getName(), user.getRole(), user.getPhone(), user.getPhotoUrl());
    }

    @Override
    public Integer getShiftCapacity() {
        return 10; // Заглушка, можно хранить в базе или конфигурации
    }

    @Override
    public Integer updateShiftCapacity(Integer capacity) {
        return capacity; // Заглушка
    }

    @Override
    public String getVacancyParameters() {
        return "example parameters"; // Заглушка
    }

    @Override
    public String updateVacancyParameters(String parameters) {
        return parameters; // Заглушка
    }

    @Override
    public List<SalonServiceResponseDTO> getSalonServices() {
        return salonServiceRepository.findAll()
                .stream()
                .map(ss -> new SalonServiceResponseDTO(ss.getId(), ss.getName(), ss.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public SalonServiceResponseDTO createSalonService(SalonServiceRequestDTO request) {
        SalonService ss = new SalonService();
        ss.setName(request.name());
        ss.setDescription(request.description());
        ss = salonServiceRepository.save(ss);
        return new SalonServiceResponseDTO(ss.getId(), ss.getName(), ss.getDescription());
    }

    @Override
    public SalonServiceResponseDTO updateSalonService(Long id, SalonServiceRequestDTO request) {
        SalonService ss = salonServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salon service not found"));
        ss.setName(request.name());
        ss.setDescription(request.description());
        ss = salonServiceRepository.save(ss);
        return new SalonServiceResponseDTO(ss.getId(), ss.getName(), ss.getDescription());
    }

    @Override
    public void deleteSalonService(Long id) {
        salonServiceRepository.deleteById(id);
    }

    @Override
    public String getCommonSchedule() {
        return "Mon-Fri: 9:00-18:00";
    }

    @Override
    public String updateCommonSchedule(String schedule) {
        return schedule;
    }
}