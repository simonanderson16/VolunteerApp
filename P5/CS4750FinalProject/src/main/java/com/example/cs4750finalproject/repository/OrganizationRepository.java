package com.example.cs4750finalproject.repository;

import com.example.cs4750finalproject.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
