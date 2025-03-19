package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Вход
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(adminService.login(request));
    }

    // Профиль
    @GetMapping("/admins/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminProfileDTO> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getProfile(id));
    }

    @PutMapping("/admins/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminProfileDTO> updateProfile(@PathVariable Long id, @RequestBody AdminProfileDTO request) {
        return ResponseEntity.ok(adminService.updateProfile(id, request));
    }

    // Управление пользователями
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getUsers(
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        return ResponseEntity.ok(adminService.getUsers(role, name, email));
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserResponseDTO request) {
        return ResponseEntity.ok(adminService.updateUser(id, request));
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Регистрация мастеров
    @GetMapping("/masters/pending")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getPendingMasters() {
        return ResponseEntity.ok(adminService.getPendingMasters());
    }

    @PutMapping("/masters/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> approveMaster(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.approveMaster(id));
    }

    @PutMapping("/masters/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> rejectMaster(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.rejectMaster(id));
    }

    // Управление конфигурациями
    @GetMapping("/config/shift-capacity")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Integer> getShiftCapacity() {
        return ResponseEntity.ok(adminService.getShiftCapacity());
    }

    @PutMapping("/config/shift-capacity")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Integer> updateShiftCapacity(@RequestBody Integer capacity) {
        return ResponseEntity.ok(adminService.updateShiftCapacity(capacity));
    }

    @GetMapping("/config/vacancy-parameters")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getVacancyParameters() {
        return ResponseEntity.ok(adminService.getVacancyParameters());
    }

    @PutMapping("/config/vacancy-parameters")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateVacancyParameters(@RequestBody String parameters) {
        return ResponseEntity.ok(adminService.updateVacancyParameters(parameters));
    }

    // Глобальные услуги салона
    @GetMapping("/salon-services")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SalonServiceResponseDTO>> getSalonServices() {
        return ResponseEntity.ok(adminService.getSalonServices());
    }

    @PostMapping("/salon-services")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SalonServiceResponseDTO> createSalonService(@RequestBody SalonServiceRequestDTO request) {
        return ResponseEntity.ok(adminService.createSalonService(request));
    }

    @PutMapping("/salon-services/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SalonServiceResponseDTO> updateSalonService(@PathVariable Long id, @RequestBody SalonServiceRequestDTO request) {
        return ResponseEntity.ok(adminService.updateSalonService(id, request));
    }

    @DeleteMapping("/salon-services/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSalonService(@PathVariable Long id) {
        adminService.deleteSalonService(id);
        return ResponseEntity.noContent().build();
    }

    // Общий график работы
    @GetMapping("/common-schedule")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getCommonSchedule() {
        return ResponseEntity.ok(adminService.getCommonSchedule());
    }

    @PutMapping("/common-schedule")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateCommonSchedule(@RequestBody String schedule) {
        return ResponseEntity.ok(adminService.updateCommonSchedule(schedule));
    }
}