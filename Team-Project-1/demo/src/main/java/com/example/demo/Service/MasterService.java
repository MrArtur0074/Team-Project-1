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

    public List<Master> getAllMasters() {
        return masterRepository.findAll();
    }

    public Master getMasterById(Long id) {
        return masterRepository.findById(id).orElse(null);
    }

    public Master createMaster(Master master) {
        return masterRepository.save(master);
    }

    public Master updateMaster(Long id, Master masterDetails) {
        Master master = masterRepository.findById(id).orElse(null);
        if (master != null) {
            master.setName(masterDetails.getName());
            master.setSpecialization(masterDetails.getSpecialization());
            master.setPhotoUrl(masterDetails.getPhotoUrl());
            return masterRepository.save(master);
        } else {
            throw new RuntimeException("Master not found with id: " + id);
        }
    }

    public void deleteMaster(Long id) {
        masterRepository.deleteById(id);
    }
}