package com.example.demo.repository;

import com.example.demo.entity.Client;
import com.example.demo.entity.ClientTag;
import com.example.demo.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientTagRepository extends JpaRepository<ClientTag, Long> {
    List<ClientTag> findByMasterAndClient(Master master, Client client);
}
