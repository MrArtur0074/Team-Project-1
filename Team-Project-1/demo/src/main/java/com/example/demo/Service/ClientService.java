package com.example.demo.service;

import com.example.demo.dto.*;

import java.util.List;

public interface ClientService {
    LoginResponseDTO register(RegisterRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO request);
    ClientProfileDTO getProfile(Long id);
    ClientProfileDTO updateProfile(Long id, ClientProfileDTO request);
    AppointmentResponseDTO createAppointment(AppointmentRequestDTO request);
    List<AppointmentResponseDTO> getCurrentAppointments();
    List<AppointmentResponseDTO> getAppointmentHistory();
    AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO request);
    ReviewResponseDTO leaveReview(Long appointmentId, ReviewRequestDTO request);
    List<ReviewResponseDTO> getReviewsByClientId(Long clientId);
    List<String> getAchievements(Long clientId);
}