package com.example.demo.service;

import com.example.demo.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminService {
    @Transactional
    LoginResponseDTO register(RegisterRequestDTO request);

    LoginResponseDTO login(LoginRequestDTO request);
    AdminProfileDTO getProfile(Long id);
    AdminProfileDTO updateProfile(Long id, AdminProfileDTO request);
    List<UserResponseDTO> getUsers(String role, String name, String email);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UserResponseDTO request);
    void deleteUser(Long id);
    List<UserResponseDTO> getPendingMasters();
    UserResponseDTO approveMaster(Long id);
    UserResponseDTO rejectMaster(Long id);
    Integer getShiftCapacity();
    Integer updateShiftCapacity(Integer capacity);
    String getVacancyParameters();
    String updateVacancyParameters(String parameters);
    List<SalonServiceResponseDTO> getSalonServices();
    SalonServiceResponseDTO createSalonService(SalonServiceRequestDTO request);
    SalonServiceResponseDTO updateSalonService(Long id, SalonServiceRequestDTO request);
    void deleteSalonService(Long id);
    String getCommonSchedule();
    String updateCommonSchedule(String schedule);
}