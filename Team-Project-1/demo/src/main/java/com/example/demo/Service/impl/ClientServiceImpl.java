package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final AppointmentRepository appointmentRepository;
    private final MasterRepository masterRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final ReviewRepository reviewRepository;

    public ClientServiceImpl(UserRepository userRepository, ClientRepository clientRepository,
                             AppointmentRepository appointmentRepository, MasterRepository masterRepository,
                             SalonServiceRepository salonServiceRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.appointmentRepository = appointmentRepository;
        this.masterRepository = masterRepository;
        this.salonServiceRepository = salonServiceRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    @Transactional
    public AppointmentResponseDTO createAppointment(Long clientId, AppointmentRequestDTO request) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));
        Master master = masterRepository.findById(request.masterId())
                .orElseThrow(() -> new RuntimeException("Мастер не найден"));
        SalonService service = salonServiceRepository.findById(request.serviceId())
                .orElseThrow(() -> new RuntimeException("Услуга не найдена"));

        Appointment appointment = new Appointment();
        appointment.setClient(client);
        appointment.setMaster(master);
        appointment.setService(service);
        appointment.setAppointmentDate(request.dateTime().toLocalDate());
        appointment.setAppointmentTime(request.dateTime().toLocalTime());
        appointment.setStatus("scheduled");
        appointment.setWantsPhoto(request.wantsPhoto());
        appointment.setClientMessage(request.clientMessage());
        appointment.setFeedback(request.wantsPhoto() ? "photo requested" : null);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return mapToAppointmentResponseDTO(savedAppointment);
    }

    @Override
    @Transactional
    public AppointmentResponseDTO updateAppointment(Long appointmentId, AppointmentRequestDTO request) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Запись не найдена"));
        Master master = masterRepository.findById(request.masterId())
                .orElseThrow(() -> new RuntimeException("Мастер не найден"));
        SalonService service = salonServiceRepository.findById(request.serviceId())
                .orElseThrow(() -> new RuntimeException("Услуга не найдена"));

        appointment.setMaster(master);
        appointment.setService(service);
        appointment.setAppointmentDate(request.dateTime().toLocalDate());
        appointment.setAppointmentTime(request.dateTime().toLocalTime());
        appointment.setClientMessage(request.clientMessage());
        appointment.setWantsPhoto(request.wantsPhoto());
        appointment.setFeedback(request.wantsPhoto() ? "photo requested" : null);

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return mapToAppointmentResponseDTO(updatedAppointment);
    }

    @Override
    @Transactional
    public ReviewResponseDTO createReview(Long appointmentId, ReviewRequestDTO request) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Запись не найдена"));
        Review review = new Review();
        review.setAppointment(appointment);
        review.setClient(appointment.getClient());
        review.setRating(request.rating());
        review.setReviewText(request.reviewText());

        Review savedReview = reviewRepository.save(review);
        return mapToReviewResponseDTO(savedReview);
    }

    @Override
    public ReviewResponseDTO getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Отзыв не найден"));
        return mapToReviewResponseDTO(review);
    }

    @Override
    public List<AppointmentResponseDTO> getAppointments(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));
        return appointmentRepository.findByClient(client)
                .stream()
                .map(this::mapToAppointmentResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientProfileDTO getProfile(Long id) {
        Client client = clientRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));
        User user = userRepository.findByEmail(client.getUser().getEmail())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return new ClientProfileDTO(user.getId(), user.getEmail(), user.getName(), user.getPhone(), user.getPhotoUrl());
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

    private ReviewResponseDTO mapToReviewResponseDTO(Review review) {
        return new ReviewResponseDTO(
                review.getId(),
                review.getAppointment().getId(),
                review.getClient().getId(),
                review.getRating(),
                review.getReviewText()
        );
    }
}