package com.example.demo.service;

import com.example.demo.dto.*;
import java.util.List;

public interface ClientService {
    AppointmentResponseDTO createAppointment(Long clientId, AppointmentRequestDTO request);
    AppointmentResponseDTO updateAppointment(Long appointmentId, AppointmentRequestDTO request);

    ReviewResponseDTO createReview(Long appointmentId, ReviewRequestDTO request);
    ReviewResponseDTO getReview(Long reviewId);

    List<AppointmentResponseDTO> getAppointments(Long clientId);
    ClientProfileDTO getProfile(Long id);
    List<AchievementResponseDTO> getAchievements(Long clientId);
}