package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.ClientService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final AppointmentRepository appointmentRepository;
    private final ReviewRepository reviewRepository;
    private final MasterRepository masterRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ClientServiceImpl(UserRepository userRepository, ClientRepository clientRepository,
                             AppointmentRepository appointmentRepository, ReviewRepository reviewRepository,
                             MasterRepository masterRepository, SalonServiceRepository salonServiceRepository,
                             PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.appointmentRepository = appointmentRepository;
        this.reviewRepository = reviewRepository;
        this.masterRepository = masterRepository;
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
        user.setRole("CLIENT");
        user = userRepository.save(user);

        Client client = new Client();
        client.setUser(user);
        clientRepository.save(client);

        String token = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), List.of(() -> "ROLE_CLIENT")));
        return new LoginResponseDTO(token, user.getId(), "CLIENT");
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String token = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), List.of(() -> "ROLE_" + user.getRole())));
        return new LoginResponseDTO(token, user.getId(), user.getRole());
    }

    @Override
    public ClientProfileDTO getProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new ClientProfileDTO(user.getId(), user.getEmail(), user.getName(), user.getPhone(), user.getPhotoUrl());
    }

    @Override
    public ClientProfileDTO updateProfile(Long id, ClientProfileDTO request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(request.name());
        user.setPhone(request.phone());
        user.setPhotoUrl(request.photoUrl());
        userRepository.save(user);
        return new ClientProfileDTO(user.getId(), user.getEmail(), user.getName(), user.getPhone(), user.getPhotoUrl());
    }

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO request) {
        Client client = clientRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Master master = request.masterId() != null ? masterRepository.findById(request.masterId())
                .orElseThrow(() -> new RuntimeException("Master not found")) : null;
        SalonService service = salonServiceRepository.findById(request.serviceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        Appointment appointment = new Appointment();
        appointment.setClient(client);
        appointment.setMaster(master);
        appointment.setService(service);
        appointment.setDateTime(request.dateTime());
        appointment.setStatus("scheduled");
        appointment.setClientMessage(request.clientMessage());
        appointment.setPhotoUrl(request.wantsPhoto() ? "" : null);
        appointment = appointmentRepository.save(appointment);

        return mapToAppointmentResponseDTO(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> getCurrentAppointments() {
        Client client = clientRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return appointmentRepository.findByClientIdAndStatus(client.getId(), "scheduled")
                .stream().map(this::mapToAppointmentResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponseDTO> getAppointmentHistory() {
        Client client = clientRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return appointmentRepository.findByClientIdAndStatus(client.getId(), "completed")
                .stream().map(this::mapToAppointmentResponseDTO).collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO request) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setDateTime(request.dateTime());
        appointment.setClientMessage(request.clientMessage());
        appointment.setPhotoUrl(request.wantsPhoto() ? "" : null);
        appointment = appointmentRepository.save(appointment);
        return mapToAppointmentResponseDTO(appointment);
    }

    @Override
    public ReviewResponseDTO leaveReview(Long appointmentId, ReviewRequestDTO request) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        Client client = clientRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Review review = new Review();
        review.setAppointment(appointment);
        review.setClient(client);
        review.setRating(request.rating());
        review.setComment(request.comment());
        review = reviewRepository.save(review);

        return new ReviewResponseDTO(review.getId(), review.getRating(), review.getComment());
    }

    private Long getCurrentUserId() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }

    private AppointmentResponseDTO mapToAppointmentResponseDTO(Appointment appointment) {
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
}
