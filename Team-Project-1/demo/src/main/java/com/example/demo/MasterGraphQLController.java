package com.example.demo;

import com.example.demo.Model.Master;
import com.example.demo.Service.MasterService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MasterGraphQLController {
    private final MasterService masterService;

    public MasterGraphQLController(MasterService masterService) {
        this.masterService = masterService;
    }

    @QueryMapping
    public List<Master> masters() {
        return masterService.getAllMasters();
    }

    @QueryMapping
    public Master master(@Argument Long id) {
        return masterService.getMasterById(id);
    }

    @MutationMapping
    public Master createMaster(@Argument String name, @Argument String specialization) {
        return masterService.createMaster(name, specialization);
    }
}