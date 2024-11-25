package com.example.cs4750finalproject.repository;

import com.example.cs4750finalproject.model.BelongsTo;
import com.example.cs4750finalproject.model.BelongsToId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BelongsToRepository extends JpaRepository<BelongsTo, BelongsToId> {
    List<BelongsTo> findByPersonPersonId(Integer personId);
    List<BelongsTo> findByOrganizationOrgId(Integer orgId);
}
