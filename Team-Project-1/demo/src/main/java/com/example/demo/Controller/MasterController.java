package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.MasterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MasterController {

    private final MasterService masterService;

    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    // Регистрация
    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(masterService.register(request));
    }

    // Вход
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(masterService.login(request));
    }

    // Профиль
    @GetMapping("/masters/{id}")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<MasterProfileDTO> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(masterService.getProfile(id));
    }

    @PutMapping("/masters/{id}")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<MasterProfileDTO> updateProfile(@PathVariable Long id, @RequestBody MasterProfileDTO request) {
        return ResponseEntity.ok(masterService.updateProfile(id, request));
    }

    // Расписание
    @GetMapping("/schedule")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<List<ScheduleSlotResponseDTO>> getSchedule() {
        return ResponseEntity.ok(masterService.getSchedule());
    }

    @PostMapping("/schedule")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<ScheduleSlotResponseDTO> addScheduleSlot(@RequestBody ScheduleSlotRequestDTO request) {
        return ResponseEntity.ok(masterService.addScheduleSlot(request));
    }

    @PutMapping("/schedule/{id}")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<ScheduleSlotResponseDTO> updateScheduleSlot(@PathVariable Long id, @RequestBody ScheduleSlotRequestDTO request) {
        return ResponseEntity.ok(masterService.updateScheduleSlot(id, request));
    }

    @DeleteMapping("/schedule/{id}")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<Void> deleteScheduleSlot(@PathVariable Long id) {
        masterService.deleteScheduleSlot(id);
        return ResponseEntity.noContent().build();
    }

    // Услуги мастера
    @GetMapping("/master-services")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<List<MasterServiceResponseDTO>> getMasterServices() {
        return ResponseEntity.ok(masterService.getMasterServices());
    }

    @PostMapping("/master-services")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<MasterServiceResponseDTO> addMasterService(@RequestBody MasterServiceRequestDTO request) {
        return ResponseEntity.ok(masterService.addMasterService(request));
    }

    @PutMapping("/master-services/{id}")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<MasterServiceResponseDTO> updateMasterService(@PathVariable Long id, @RequestBody MasterServiceRequestDTO request) {
        return ResponseEntity.ok(masterService.updateMasterService(id, request));
    }

    @DeleteMapping("/master-services/{id}")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<Void> deleteMasterService(@PathVariable Long id) {
        masterService.deleteMasterService(id);
        return ResponseEntity.noContent().build();
    }

    // Записи
    @GetMapping("/appointments/current")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<List<AppointmentResponseDTO>> getCurrentAppointments(@RequestParam("master_id") Long masterId) {
        return ResponseEntity.ok(masterService.getCurrentAppointments(masterId));
    }

    @GetMapping("/appointments/history")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentHistory(@RequestParam("master_id") Long masterId) {
        return ResponseEntity.ok(masterService.getAppointmentHistory(masterId));
    }

    // Теги клиентов
    @GetMapping("/client-tags")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<List<ClientTagResponseDTO>> getClientTags() {
        return ResponseEntity.ok(masterService.getClientTags());
    }

    @PostMapping("/client-tags")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<ClientTagResponseDTO> addClientTag(@RequestBody ClientTagRequestDTO request) {
        return ResponseEntity.ok(masterService.addClientTag(request));
    }

    @PutMapping("/client-tags/{id}")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<ClientTagResponseDTO> updateClientTag(@PathVariable Long id, @RequestBody ClientTagRequestDTO request) {
        return ResponseEntity.ok(masterService.updateClientTag(id, request));
    }

    @DeleteMapping("/client-tags/{id}")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<Void> deleteClientTag(@PathVariable Long id) {
        masterService.deleteClientTag(id);
        return ResponseEntity.noContent().build();
    }
}