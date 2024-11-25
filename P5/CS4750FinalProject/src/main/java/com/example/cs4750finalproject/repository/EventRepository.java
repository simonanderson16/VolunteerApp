package com.example.cs4750finalproject.repository;

import com.example.cs4750finalproject.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByOrganizationOrgId(Integer orgId);
}
