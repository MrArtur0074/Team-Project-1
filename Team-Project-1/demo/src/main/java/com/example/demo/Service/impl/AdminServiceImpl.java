package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.AdminService;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final MasterRepository masterRepository;
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final ShiftCapacityRepository shiftCapacityRepository;
    private final VacancyParametersRepository vacancyParametersRepository;
    private final ScheduleSlotRepository scheduleRepository;
    private final AuthService authService;

    public AdminServiceImpl(UserRepository userRepository, MasterRepository masterRepository,
                            ClientRepository clientRepository, AdminRepository adminRepository,
                            SalonServiceRepository salonServiceRepository, ShiftCapacityRepository shiftCapacityRepository,
                            VacancyParametersRepository vacancyParametersRepository, ScheduleSlotRepository scheduleRepository,
                            AuthService authService) {
        this.userRepository = userRepository;
        this.masterRepository = masterRepository;
        this.clientRepository = clientRepository;
        this.adminRepository = adminRepository;
        this.salonServiceRepository = salonServiceRepository;
        this.shiftCapacityRepository = shiftCapacityRepository;
        this.vacancyParametersRepository = vacancyParametersRepository;
        this.scheduleRepository = scheduleRepository;
        this.authService = authService;
    }

    @Transactional
    @Override
    public LoginResponseDTO register(RegisterRequestDTO request) {
        LoginResponseDTO response = authService.register(request, Role.ADMIN, true);
        User user = userRepository.findById(response.userId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Admin admin = new Admin();
        admin.setUser(user);
        adminRepository.save(admin);

        return response;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        return authService.login(request);
    }

    @Override
    public AdminProfileDTO getProfile(Long id) {
        Admin admin = adminRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("Администратор не найден"));
        User user = admin.getUser();
        return new AdminProfileDTO(user.getId(), user.getEmail(), user.getName(), user.getPhone(), user.getPhotoUrl());
    }

    @Override
    @Transactional
    public AdminProfileDTO updateProfile(Long id, AdminProfileDTO request) {
        Admin admin = adminRepository.findByUserId(id)
                .orElseThrow(() -> new RuntimeException("Администратор не найден"));
        User user = admin.getUser();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPhone(request.phone());
        user.setPhotoUrl(request.photoUrl());
        userRepository.save(user);
        return new AdminProfileDTO(user.getId(), user.getEmail(), user.getName(), user.getPhone(), user.getPhotoUrl());
    }

    @Override
    public List<UserResponseDTO> getUsers(String role, String name, String email) {
        Role roleEnum = (role != null) ? Role.valueOf(role.toUpperCase()) : null;
        List<User> users = userRepository.findByFilters(roleEnum, name, email);
        return users.stream()
                .map(this::mapToUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return mapToUserResponseDTO(user);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserResponseDTO request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPhone(request.phone());
        user.setPhotoUrl(request.photoUrl());
        if (request.role() != null) {
            user.setRole(Role.valueOf(request.role().toUpperCase()));
        }
        userRepository.save(user);
        return mapToUserResponseDTO(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDTO> getPendingMasters() {
        List<User> pendingMasters = userRepository.findByRoleAndIsPending(Role.MASTER, true);
        return pendingMasters.stream()
                .map(this::mapToUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponseDTO approveMaster(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Мастер не найден"));
        if (user.getRole() != Role.MASTER || !Boolean.TRUE.equals(user.getIsPending())) {
            throw new RuntimeException("Пользователь не является ожидающим мастером");
        }
        user.setIsPending(false);
        userRepository.save(user);

        Optional<Master> masterOpt = masterRepository.findByUserId(id);
        if (masterOpt.isEmpty()) {
            Master master = new Master();
            master.setUser(user);
            master.setRating(0.0);
            master.setExperienceYears(0);
            masterRepository.save(master);
        }

        return mapToUserResponseDTO(user);
    }

    @Override
    @Transactional
    public UserResponseDTO rejectMaster(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Мастер не найден"));
        if (user.getRole() != Role.MASTER || !Boolean.TRUE.equals(user.getIsPending())) {
            throw new RuntimeException("Пользователь не является ожидающим мастером");
        }
        userRepository.delete(user);
        return mapToUserResponseDTO(user);
    }

    @Override
    public Integer getShiftCapacity() {
        Optional<ShiftCapacity> shiftCapacity = shiftCapacityRepository.findFirst();
        return shiftCapacity.map(ShiftCapacity::getMaxMasters)
                .orElseThrow(() -> new RuntimeException("Информация о сменах не найдена"));
    }

    @Override
    @Transactional
    public Integer updateShiftCapacity(Integer capacity) {
        Optional<ShiftCapacity> optionalShift = shiftCapacityRepository.findFirst();
        ShiftCapacity shift = optionalShift.orElse(new ShiftCapacity());
        if (optionalShift.isEmpty()) {
            shift.setShiftName("default");
        }
        shift.setMaxMasters(capacity);
        shiftCapacityRepository.save(shift);
        return capacity;
    }

    @Override
    public String getVacancyParameters() {
        Optional<VacancyParameters> vacancyParams = vacancyParametersRepository.findFirst();
        return vacancyParams.map(VacancyParameters::getParameterName)
                .orElseThrow(() -> new RuntimeException("Параметры вакансий не найдены"));
    }

    @Override
    @Transactional
    public String updateVacancyParameters(String parameters) {
        Optional<VacancyParameters> optionalParams = vacancyParametersRepository.findFirst();
        VacancyParameters vacancyParams = optionalParams.orElse(new VacancyParameters());
        if (optionalParams.isEmpty()) {
            vacancyParams.setIsRequired(true);
        }
        vacancyParams.setParameterName(parameters);
        vacancyParametersRepository.save(vacancyParams);
        return parameters;
    }

    @Override
    public List<SalonServiceResponseDTO> getSalonServices() {
        return salonServiceRepository.findAll()
                .stream()
                .map(this::mapToSalonServiceResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SalonServiceResponseDTO createSalonService(SalonServiceRequestDTO request) {
        SalonService service = new SalonService();
        service.setName(request.name());
        service.setDefaultCost(request.defaultCost());
        service.setDescription(request.description());
        SalonService savedService = salonServiceRepository.save(service);
        return mapToSalonServiceResponseDTO(savedService);
    }

    @Override
    @Transactional
    public SalonServiceResponseDTO updateSalonService(Long id, SalonServiceRequestDTO request) {
        SalonService service = salonServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Услуга не найдена"));
        service.setName(request.name());
        service.setDefaultCost(request.defaultCost());
        service.setDescription(request.description());
        SalonService updatedService = salonServiceRepository.save(service);
        return mapToSalonServiceResponseDTO(updatedService);
    }

    @Override
    @Transactional
    public void deleteSalonService(Long id) {
        // Исправление: проверяем, что метод deleteById существует в репозитории
        // В Spring Data JPA этот метод должен быть доступен по умолчанию
        salonServiceRepository.deleteById(id);
    }

    @Override
    public String getCommonSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();
        if (schedules.isEmpty()) {
            return "No common schedule defined";
        }
        return schedules.stream()
                .map(s -> String.format("%s: %s - %s", s.getDayOfWeek(), s.getStartTime(), s.getEndTime()))
                .collect(Collectors.joining(", "));
    }

    @Override
    @Transactional
    public String updateCommonSchedule(String schedule) {
        scheduleRepository.deleteAll();
        Schedule newSchedule = new Schedule();
        newSchedule.setMaster(null);
        newSchedule.setDayOfWeek("All");
        newSchedule.setStartTime("09:00");
        newSchedule.setEndTime("18:00");
        scheduleRepository.save(newSchedule);
        return schedule;
    }

    private UserResponseDTO mapToUserResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole().name(),
                user.getPhone(),
                user.getPhotoUrl()
        );
    }

    private SalonServiceResponseDTO mapToSalonServiceResponseDTO(SalonService service) {
        return new SalonServiceResponseDTO(
                service.getId(),
                service.getName(),
                service.getDefaultCost(),
                service.getDescription()
        );
    }
}