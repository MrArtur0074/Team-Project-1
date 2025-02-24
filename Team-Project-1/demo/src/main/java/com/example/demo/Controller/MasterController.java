package com.example.demo.Controller;

import com.example.demo.Model.Master;
import com.example.demo.Service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/masters")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @GetMapping
    public List<Master> getAllMasters() {
        return masterService.getAllMasters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Master> getMasterById(@PathVariable Long id) {
        Master master = masterService.getMasterById(id);
        if (master != null) {
            return ResponseEntity.ok(master);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Master createMaster(@RequestBody Master master) {
        return masterService.createMaster(master);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Master> updateMaster(@PathVariable Long id, @RequestBody Master masterDetails) {
        Master updatedMaster = masterService.updateMaster(id, masterDetails);
        if (updatedMaster != null) {
            return ResponseEntity.ok(updatedMaster);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaster(@PathVariable Long id) {
        masterService.deleteMaster(id);
        return ResponseEntity.noContent().build();
    }
}