package com.example.cs4750finalproject.service;

import com.example.cs4750finalproject.model.BelongsTo;
import com.example.cs4750finalproject.model.BelongsToId;
import com.example.cs4750finalproject.model.Organization;
import com.example.cs4750finalproject.model.Person;
import com.example.cs4750finalproject.repository.BelongsToRepository;
import com.example.cs4750finalproject.repository.OrganizationRepository;
import com.example.cs4750finalproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BelongsToService {

    @Autowired
    private BelongsToRepository belongsToRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public List<BelongsTo> getAllMemberships() {
        return belongsToRepository.findAll();
    }

    public Optional<BelongsTo> getMembershipById(Integer personId, Integer orgId) {
        return belongsToRepository.findById(new BelongsToId(personId, orgId));
    }

    public List<BelongsTo> getMembershipsByPerson(Integer personId) {
        return belongsToRepository.findByPersonPersonId(personId);
    }

    public List<BelongsTo> getMembershipsByOrganization(Integer orgId) {
        return belongsToRepository.findByOrganizationOrgId(orgId);
    }

    public BelongsTo addMembership(Integer personId, Integer orgId, String role) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        Organization organization = organizationRepository.findById(orgId)
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        BelongsTo belongsTo = new BelongsTo(person, organization, role);
        return belongsToRepository.save(belongsTo);
    }

    public void removeMembership(Integer personId, Integer orgId) {
        belongsToRepository.deleteById(new BelongsToId(personId, orgId));
    }

    public BelongsTo updateMembership(Integer personId, Integer orgId, String role) {
        BelongsToId id = new BelongsToId(personId, orgId);
        BelongsTo membership = belongsToRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));

        membership.setRole(role);
        return belongsToRepository.save(membership);
    }

}