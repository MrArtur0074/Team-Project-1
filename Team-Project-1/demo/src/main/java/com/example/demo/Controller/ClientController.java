package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Регистрация
    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(clientService.register(request));
    }

    // Вход
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(clientService.login(request));
    }

    // Профиль
    @GetMapping("/clients/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ClientProfileDTO> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getProfile(id));
    }

    @PutMapping("/clients/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ClientProfileDTO> updateProfile(@PathVariable Long id, @RequestBody ClientProfileDTO request) {
        return ResponseEntity.ok(clientService.updateProfile(id, request));
    }

    // Записи (Appointments)
    @PostMapping("/appointments")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@RequestBody AppointmentRequestDTO request) {
        return ResponseEntity.ok(clientService.createAppointment(request));
    }

    @GetMapping("/appointments/current")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<AppointmentResponseDTO>> getCurrentAppointments() {
        return ResponseEntity.ok(clientService.getCurrentAppointments());
    }

    @GetMapping("/appointments/history")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentHistory() {
        return ResponseEntity.ok(clientService.getAppointmentHistory());
    }

    @PutMapping("/appointments/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequestDTO request) {
        return ResponseEntity.ok(clientService.updateAppointment(id, request));
    }

    // Отзывы
    @PostMapping("/appointments/{id}/review")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ReviewResponseDTO> leaveReview(@PathVariable Long id, @RequestBody ReviewRequestDTO request) {
        return ResponseEntity.ok(clientService.leaveReview(id, request));
    }

    @GetMapping("/reviews")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<ReviewResponseDTO>> getReviews(@RequestParam("client_id") Long clientId) {
        return ResponseEntity.ok(clientService.getReviewsByClientId(clientId));
    }

    // Ачивки
    @GetMapping("/achievements")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<String>> getAchievements(@RequestParam("client_id") Long clientId) {
        return ResponseEntity.ok(clientService.getAchievements(clientId));
    }
}