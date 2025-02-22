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

    public Master createMaster(String name, String specialization) {
        Master master = new Master();
        master.setName(name);
        master.setSpecialization(specialization);
        return masterRepository.save(master);
    }
}