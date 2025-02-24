package com.example.demo.Service;

import com.example.demo.Model.ServiceModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {  // <-- Переименован с ServiceModel на ServiceService
    private List<ServiceModel> services = new ArrayList<>();
    private Long nextId = 1L;

    // Получить все услуги
    public List<ServiceModel> getAllServices() {
        return services;
    }

    // Получить услугу по ID
    public Optional<ServiceModel> getServiceById(Long id) {
        return services.stream()
                .filter(service -> service.getId().equals(id))
                .findFirst();
    }

    // Добавить новую услугу
    public ServiceModel createService(ServiceModel service) {
        service.setId(nextId++);
        services.add(service);
        return service;
    }

    // Обновить услугу
    public ServiceModel updateService(Long id, ServiceModel updatedService) {
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getId().equals(id)) {
                updatedService.setId(id);
                services.set(i, updatedService);
                return updatedService;
            }
        }
        return null;
    }

    // Удалить услугу
    public boolean deleteService(Long id) {
        return services.removeIf(service -> service.getId().equals(id));
    }
}