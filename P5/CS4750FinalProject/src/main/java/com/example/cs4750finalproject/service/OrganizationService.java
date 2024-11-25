package com.example.cs4750finalproject.service;

import com.example.cs4750finalproject.model.Organization;
import com.example.cs4750finalproject.model.Person;
import com.example.cs4750finalproject.repository.OrganizationRepository;
import com.example.cs4750finalproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Optional<Organization> getOrganizationById(Integer id) {
        return organizationRepository.findById(id);
    }

    public Organization createOrganization(Organization organization) {
        // Ensure the admin (Person) exists in the database
        Person admin = personRepository.findById(organization.getAdmin().getPersonId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        organization.setAdmin(admin);

        return organizationRepository.save(organization);
    }

    public Organization updateOrganization(Integer id, Organization updatedOrganization) {
        Optional<Organization> existingOrganizationOptional = organizationRepository.findById(id);
        if (existingOrganizationOptional.isEmpty()) {
            throw new RuntimeException("Organization not found");
        }

        Organization existingOrganization = existingOrganizationOptional.get();
        existingOrganization.setName(updatedOrganization.getName());
        existingOrganization.setDescription(updatedOrganization.getDescription());
        existingOrganization.setEmail(updatedOrganization.getEmail());
        existingOrganization.setWebsite(updatedOrganization.getWebsite());

        Person admin = personRepository.findById(updatedOrganization.getAdmin().getPersonId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        existingOrganization.setAdmin(admin);

        return organizationRepository.save(existingOrganization);
    }

    public void deleteOrganization(Integer id) {
        organizationRepository.deleteById(id);
    }
}
