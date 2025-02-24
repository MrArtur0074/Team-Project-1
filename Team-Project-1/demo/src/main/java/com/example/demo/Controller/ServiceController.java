package com.example.demo.Controller;

import com.example.demo.Model.ServiceModel;
import com.example.demo.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    // Получить список всех услуг
    @GetMapping
    public List<ServiceModel> getAllServices() {
        return serviceService.getAllServices();
    }

    // Получить информацию об услуге по ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceModel> getServiceById(@PathVariable Long id) {
        Optional<ServiceModel> service = serviceService.getServiceById(id);
        return service.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Добавить новую услугу
    @PostMapping
    public ServiceModel createService(@RequestBody ServiceModel service) {
        return serviceService.createService(service);
    }

    // Обновить услугу по ID
    @PutMapping("/{id}")
    public ResponseEntity<ServiceModel> updateService(@PathVariable Long id, @RequestBody ServiceModel updatedService) {
        ServiceModel service = serviceService.updateService(id, updatedService);
        return service != null ? ResponseEntity.ok(service) : ResponseEntity.notFound().build();
    }

    // Удалить услугу по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        boolean deleted = serviceService.deleteService(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}