package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.MasterService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterServiceImpl implements MasterService {

    private final UserRepository userRepository;
    private final MasterRepository masterRepository;
    private final ScheduleSlotRepository scheduleSlotRepository;
    private final MasterServiceRepository masterServiceRepository;
    private final AppointmentRepository appointmentRepository;
    private final ClientTagRepository clientTagRepository;
    private final ClientRepository clientRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public MasterServiceImpl(UserRepository userRepository, MasterRepository masterRepository,
                             ScheduleSlotRepository scheduleSlotRepository, MasterServiceRepository masterServiceRepository,
                             AppointmentRepository appointmentRepository, ClientTagRepository clientTagRepository,
                             ClientRepository clientRepository, SalonServiceRepository salonServiceRepository,
                             PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.masterRepository = masterRepository;
        this.scheduleSlotRepository = scheduleSlotRepository;
        this.masterServiceRepository = masterServiceRepository;
        this.appointmentRepository = appointmentRepository;
        this.clientTagRepository = clientTagRepository;
        this.clientRepository = clientRepository;
        this.salonServiceRepository = salonServiceRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDTO register(RegisterRequestDTO request) {
        User user = new User();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setName(request.name());
        user.setPhone(request.phone());
        user.setRole("MASTER");
        user.setIsPending(true);
        user = userRepository.save(user);

        Master master = new Master();
        master.setUser(user);
        masterRepository.save(master);

        return new LoginResponseDTO(null, user.getId(), "MASTER");
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        if (user.getIsPending() != null && user.getIsPending()) {
            throw new RuntimeException("Account is pending approval");
        }
        String token = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), List.of(() -> "ROLE_" + user.getRole())));
        return new LoginResponseDTO(token, user.getId(), user.getRole());
    }

    @Override
    public MasterProfileDTO getProfile(Long id) {
        Master master = masterRepository.findById(id).orElseThrow(() -> new RuntimeException("Master not found"));
        User user = master.getUser();
        return new MasterProfileDTO(user.getId(), user.getEmail(), user.getName(), user.getPhone(), user.getPhotoUrl(),
                master.getRating(), master.getExperienceYears());
    }

    @Override
    public MasterProfileDTO updateProfile(Long id, MasterProfileDTO request) {
        Master master = masterRepository.findById(id).orElseThrow(() -> new RuntimeException("Master not found"));
        User user = master.getUser();
        user.setName(request.name());
        user.setPhone(request.phone());
        user.setPhotoUrl(request.photoUrl());
        master.setExperienceYears(request.experienceYears());
        userRepository.save(user);
        masterRepository.save(master);
        return new MasterProfileDTO(user.getId(), user.getEmail(), user.getName(), user.getPhone(), user.getPhotoUrl(),
                master.getRating(), master.getExperienceYears());
    }

    @Override
    public List<ScheduleSlotResponseDTO> getSchedule() {
        Master master = masterRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Master not found"));
        return scheduleSlotRepository.findByMasterId(master.getId())
                .stream()
                .map(s -> new ScheduleSlotResponseDTO(s.getId(), s.getDayOfWeek(), s.getStartTime(), s.getEndTime()))
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleSlotResponseDTO addScheduleSlot(ScheduleSlotRequestDTO request) {
        Master master = masterRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Master not found"));
        ScheduleSlot slot = new ScheduleSlot();
        slot.setMaster(master);
        slot.setDayOfWeek(request.dayOfWeek());
        slot.setStartTime(request.startTime());
        slot.setEndTime(request.endTime());
        slot = scheduleSlotRepository.save(slot);
        return new ScheduleSlotResponseDTO(slot.getId(), slot.getDayOfWeek(), slot.getStartTime(), slot.getEndTime());
    }

    @Override
    public ScheduleSlotResponseDTO updateScheduleSlot(Long id, ScheduleSlotRequestDTO request) {
        ScheduleSlot slot = scheduleSlotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule slot not found"));
        slot.setDayOfWeek(request.dayOfWeek());
        slot.setStartTime(request.startTime());
        slot.setEndTime(request.endTime());
        slot = scheduleSlotRepository.save(slot);
        return new ScheduleSlotResponseDTO(slot.getId(), slot.getDayOfWeek(), slot.getStartTime(), slot.getEndTime());
    }

    @Override
    public void deleteScheduleSlot(Long id) {
        scheduleSlotRepository.deleteById(id);
    }

    @Override
    public List<MasterServiceResponseDTO> getMasterServices() {
        Master master = masterRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Master not found"));
        return masterServiceRepository.findByMasterId(master.getId())
                .stream()
                .map(ms -> new MasterServiceResponseDTO(ms.getId(), ms.getMaster().getId(), ms.getSalonService().getId(),
                        ms.getSalonService().getName(), ms.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public MasterServiceResponseDTO addMasterService(MasterServiceRequestDTO request) {
        Master master = masterRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Master not found"));
        SalonService salonService = salonServiceRepository.findById(request.salonServiceId())
                .orElseThrow(() -> new RuntimeException("Salon service not found"));
        MasterService ms = new MasterService();
        ms.setMaster(master);
        ms.setSalonService(salonService);
        ms.setPrice(request.price());
        ms = masterServiceRepository.save(ms);
        return new MasterServiceResponseDTO(ms.getId(), ms.getMaster().getId(), ms.getSalonService().getId(),
                ms.getSalonService().getName(), ms.getPrice());
    }

    @Override
    public MasterServiceResponseDTO updateMasterService(Long id, MasterServiceRequestDTO request) {
        MasterService ms = masterServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Master service not found"));
        ms.setPrice(request.price());
        ms = masterServiceRepository.save(ms);
        return new MasterServiceResponseDTO(ms.getId(), ms.getMaster().getId(), ms.getSalonService().getId(),
                ms.getSalonService().getName(), ms.getPrice());
    }

    @Override
    public void deleteMasterService(Long id) {
        masterServiceRepository.deleteById(id);
    }

    @Override
    public List<AppointmentResponseDTO> getCurrentAppointments(Long masterId) {
        return appointmentRepository.findByMasterIdAndStatus(masterId, "scheduled")
                .stream()
                .map(this::mapToAppointmentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentHistory(Long masterId) {
        return appointmentRepository.findByMasterIdAndStatus(masterId, "completed")
                .stream()
                .map(this::mapToAppointmentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientTagResponseDTO> getClientTags() {
        Master master = masterRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Master not found"));
        return clientTagRepository.findByMasterId(master.getId())
                .stream()
                .map(ct -> new ClientTagResponseDTO(ct.getId(), ct.getClient().getId(), ct.getTagText()))
                .collect(Collectors.toList());
    }

    @Override
    public ClientTagResponseDTO addClientTag(ClientTagRequestDTO request) {
        Master master = masterRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Master not found"));
        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        ClientTag tag = new ClientTag();
        tag.setMaster(master);
        tag.setClient(client);
        tag.setTagText(request.tagText());
        tag = clientTagRepository.save(tag);
        return new ClientTagResponseDTO(tag.getId(), tag.getClient().getId(), tag.getTagText());
    }

    @Override
    public ClientTagResponseDTO updateClientTag(Long id, ClientTagRequestDTO request) {
        ClientTag tag = clientTagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client tag not found"));
        tag.setTagText(request.tagText());
        tag = clientTagRepository.save(tag);
        return new ClientTagResponseDTO(tag.getId(), tag.getClient().getId(), tag.getTagText());
    }

    @Override
    public void deleteClientTag(Long id) {
        clientTagRepository.deleteById(id);
    }

    private AppointmentResponseDTO mapToAppointmentResponse(Appointment appointment) {
        return new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getDateTime(),
                appointment.getStatus(),
                appointment.getClientMessage(),
                appointment.getPhotoUrl(),
                appointment.getService().getId(),
                appointment.getMaster() != null ? appointment.getMaster().getId() : null
        );
    }

    private Long getCurrentUserId() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found")).getId();
    }
}