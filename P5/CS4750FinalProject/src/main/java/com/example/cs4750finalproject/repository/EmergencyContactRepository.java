package com.example.cs4750finalproject.repository;

import com.example.cs4750finalproject.model.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Integer> {
    List<EmergencyContact> findByNameContaining(String name);
}

