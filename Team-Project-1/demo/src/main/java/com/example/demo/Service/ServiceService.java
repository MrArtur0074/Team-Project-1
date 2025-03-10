package com.example.demo.Service;

import com.example.demo.Model.ServiceModel;
import com.example.demo.Repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceModel> getAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<ServiceModel> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    public ServiceModel createService(ServiceModel service) {
        return serviceRepository.save(service);
    }

    public ServiceModel updateService(Long id, ServiceModel updatedService) {
        return serviceRepository.findById(id)
                .map(service -> {
                    service.setName(updatedService.getName());
                    service.setDescription(updatedService.getDescription());
                    service.setPrice(updatedService.getPrice());
                    return serviceRepository.save(service);
                })
                .orElse(null);
    }

    public boolean deleteService(Long id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}