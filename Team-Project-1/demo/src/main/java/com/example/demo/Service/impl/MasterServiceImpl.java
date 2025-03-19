package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.MasterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterServiceImpl implements MasterService {

    private final UserRepository userRepository;
    private final MasterRepository masterRepository;
    private final ScheduleSlotRepository scheduleSlotRepository;
    private final MasterServiceRepository masterServiceRepository;
    private final ClientTagRepository clientTagRepository;
    private final AppointmentRepository appointmentRepository;

    public MasterServiceImpl(UserRepository userRepository, MasterRepository masterRepository,
                             ScheduleSlotRepository scheduleSlotRepository, MasterServiceRepository masterServiceRepository,
                             ClientTagRepository clientTagRepository, AppointmentRepository appointmentRepository) {
        this.userRepository = userRepository;
        this.masterRepository = masterRepository;
        this.scheduleSlotRepository = scheduleSlotRepository;
        this.masterServiceRepository = masterServiceRepository;
        this.clientTagRepository = clientTagRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<ScheduleSlotResponseDTO> getSchedule(Long masterId) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new RuntimeException("Мастер не найден"));
        return scheduleSlotRepository.findByMaster(master)
                .stream()
                .map(this::mapToScheduleSlotResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ScheduleSlotResponseDTO createScheduleSlot(Long masterId, ScheduleSlotRequestDTO request) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new RuntimeException("Мастер не найден"));
        ScheduleSlot slot = new ScheduleSlot();
        slot.setMaster(master);
        slot.setDayOfWeek(request.dayOfWeek());
        slot.setStartTime(request.startTime());
        slot.setEndTime(request.endTime());

        ScheduleSlot savedSlot = scheduleSlotRepository.save(slot);
        return mapToScheduleSlotResponseDTO(savedSlot);
    }

    @Override
    @Transactional
    public ScheduleSlotResponseDTO updateScheduleSlot(Long slotId, ScheduleSlotRequestDTO request) {
        ScheduleSlot slot = scheduleSlotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Слот не найден"));
        slot.setDayOfWeek(request.dayOfWeek());
        slot.setStartTime(request.startTime());
        slot.setEndTime(request.endTime());

        ScheduleSlot updatedSlot = scheduleSlotRepository.save(slot);
        return mapToScheduleSlotResponseDTO(updatedSlot);
    }

    @Override
    public List<MasterServiceResponseDTO> getMasterServices(Long masterId) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new RuntimeException("Мастер не найден"));
        return masterServiceRepository.findByMaster(master)
                .stream()
                .map(this::mapToMasterServiceResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MasterServiceResponseDTO createMasterService(Long masterId, MasterServiceRequestDTO request) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new RuntimeException("Мастер не найден"));
        SalonService salonService = salonServiceRepository.findById(request.salonServiceId())
                .orElseThrow(() -> new RuntimeException("Услуга не найдена"));

        MasterService masterServiceEntity = new MasterService();
        masterServiceEntity.setMaster(master);
        masterServiceEntity.setSalonService(salonService);
        masterServiceEntity.setCost(request.cost());

        MasterService savedMasterService = masterServiceRepository.save(masterServiceEntity);
        return mapToMasterServiceResponseDTO(savedMasterService);
    }

    @Override
    @Transactional
    public MasterServiceResponseDTO updateMasterService(Long masterServiceId, MasterServiceRequestDTO request) {
        MasterService masterServiceEntity = masterServiceRepository.findById(masterServiceId)
                .orElseThrow(() -> new RuntimeException("Услуга мастера не найдена"));
        SalonService salonService = salonServiceRepository.findById(request.salonServiceId())
                .orElseThrow(() -> new RuntimeException("Услуга не найдена"));

        masterServiceEntity.setSalonService(salonService);
        masterServiceEntity.setCost(request.cost());

        MasterService updatedMasterService = masterServiceRepository.save(masterServiceEntity);
        return mapToMasterServiceResponseDTO(updatedMasterService);
    }

    @Override
    public List<ClientTagResponseDTO> getClientTags(Long masterId, Long clientId) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new RuntimeException("Мастер не найден"));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));
        return clientTagRepository.findByMasterAndClient(master, client)
                .stream()
                .map(this::mapToClientTagResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClientTagResponseDTO createClientTag(Long masterId, Long clientId, ClientTagRequestDTO request) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new RuntimeException("Мастер не найден"));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));

        ClientTag tag = new ClientTag();
        tag.setMaster(master);
        tag.setClient(client);
        tag.setTag(request.tag());

        ClientTag savedTag = clientTagRepository.save(tag);
        return mapToClientTagResponseDTO(savedTag);
    }

    @Override
    @Transactional
    public ClientTagResponseDTO updateClientTag(Long tagId, ClientTagRequestDTO request) {
        ClientTag tag = clientTagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Тег не найден"));
        tag.setTag(request.tag());

        ClientTag updatedTag = clientTagRepository.save(tag);
        return mapToClientTagResponseDTO(updatedTag);
    }

    @Override
    public List<AppointmentResponseDTO> getAppointments(Long masterId) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new RuntimeException("Мастер не найден"));
        return appointmentRepository.findByMaster(master)
                .stream()
                .map(this::mapToAppointmentResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MasterProfileDTO getProfile(Long id) {
        Master master = masterRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("Мастер не найден"));
        User user = userRepository.findByEmail(master.getUser().getEmail())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return new MasterProfileDTO(user.getId(), user.getEmail(), user.getName(), user.getPhone(), user.getPhotoUrl(), master.getRating(), master.getExperienceYears());
    }

    private ScheduleSlotResponseDTO mapToScheduleSlotResponseDTO(ScheduleSlot slot) {
        return new ScheduleSlotResponseDTO(
                slot.getId(),
                slot.getMaster().getId(),
                slot.getDayOfWeek(),
                slot.getStartTime(),
                slot.getEndTime()
        );
    }

    private MasterServiceResponseDTO mapToMasterServiceResponseDTO(MasterService msEntity) {
        return new MasterServiceResponseDTO(
                msEntity.getId(),
                msEntity.getMaster().getId(),
                msEntity.getSalonService().getId(),
                msEntity.getCost()
        );
    }

    private ClientTagResponseDTO mapToClientTagResponseDTO(ClientTag ct) {
        return new ClientTagResponseDTO(
                ct.getId(),
                ct.getMaster().getId(),
                ct.getClient().getId(),
                ct.getTag()
        );
    }

    private AppointmentResponseDTO mapToAppointmentResponseDTO(Appointment appointment) {
        return new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getClient().getId(),
                appointment.getMaster().getId(),
                appointment.getService().getId(),
                appointment.getAppointmentDate().atTime(appointment.getAppointmentTime()),
                appointment.getStatus(),
                appointment.getClientMessage(),
                appointment.getFeedback()
        );
    }
}