package com.example.demo.service;

import com.example.demo.dto.*;
import java.util.List;

public interface MasterService {
    List<ScheduleSlotResponseDTO> getSchedule(Long masterId);
    ScheduleSlotResponseDTO createScheduleSlot(Long masterId, ScheduleSlotRequestDTO request);
    ScheduleSlotResponseDTO updateScheduleSlot(Long slotId, ScheduleSlotRequestDTO request);

    List<MasterServiceResponseDTO> getMasterServices(Long masterId);
    MasterServiceResponseDTO createMasterService(Long masterId, MasterServiceRequestDTO request);
    MasterServiceResponseDTO updateMasterService(Long masterServiceId, MasterServiceRequestDTO request);

    List<ClientTagResponseDTO> getClientTags(Long masterId, Long clientId);
    ClientTagResponseDTO createClientTag(Long masterId, Long clientId, ClientTagRequestDTO request);
    ClientTagResponseDTO updateClientTag(Long tagId, ClientTagRequestDTO request);
    void deleteClientTag(Long tagId);

    List<AppointmentResponseDTO> getAppointments(Long masterId);
    MasterProfileDTO getProfile(Long id);
}