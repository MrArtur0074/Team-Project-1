package com.example.demo.service;

import com.example.demo.dto.*;

import java.util.List;

public interface MasterService {
    LoginResponseDTO register(RegisterRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO request);
    MasterProfileDTO getProfile(Long id);
    MasterProfileDTO updateProfile(Long id, MasterProfileDTO request);
    List<ScheduleSlotResponseDTO> getSchedule();
    ScheduleSlotResponseDTO addScheduleSlot(ScheduleSlotRequestDTO request);
    ScheduleSlotResponseDTO updateScheduleSlot(Long id, ScheduleSlotRequestDTO request);
    void deleteScheduleSlot(Long id);
    List<MasterServiceResponseDTO> getMasterServices();
    MasterServiceResponseDTO addMasterService(MasterServiceRequestDTO request);
    MasterServiceResponseDTO updateMasterService(Long id, MasterServiceRequestDTO request);
    void deleteMasterService(Long id);
    List<AppointmentResponseDTO> getCurrentAppointments(Long masterId);
    List<AppointmentResponseDTO> getAppointmentHistory(Long masterId);
    List<ClientTagResponseDTO> getClientTags();
    ClientTagResponseDTO addClientTag(ClientTagRequestDTO request);
    ClientTagResponseDTO updateClientTag(Long id, ClientTagRequestDTO request);
    void deleteClientTag(Long id);
}