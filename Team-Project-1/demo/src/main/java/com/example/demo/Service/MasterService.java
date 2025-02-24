package com.example.demo.Service;

import com.example.demo.Model.Master;
import com.example.demo.Repository.MasterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterService {
    private final MasterRepository masterRepository;

    public MasterService(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    // Получить всех мастеров
    public List<Master> getAllMasters() {
        return masterRepository.findAll();
    }

    // Получить мастера по ID
    public Master getMasterById(Long id) {
        return masterRepository.findById(id).orElse(null);
    }

    // Создать нового мастера
    public Master createMaster(Master master) {
        return masterRepository.save(master);
    }

    // Обновить существующего мастера
    public Master updateMaster(Long id, Master masterDetails) {
        Master master = masterRepository.findById(id).orElse(null);
        if (master != null) {
            // Обновляем поля мастера
            master.setName(masterDetails.getName());
            master.setSpecialization(masterDetails.getSpecialization());
            master.setPhotoUrl(masterDetails.getPhotoUrl());
            return masterRepository.save(master);
        } else {
            throw new RuntimeException("Master not found with id: " + id);
        }
    }

    // Удалить мастера по ID
    public void deleteMaster(Long id) {
        masterRepository.deleteById(id);
    }
}