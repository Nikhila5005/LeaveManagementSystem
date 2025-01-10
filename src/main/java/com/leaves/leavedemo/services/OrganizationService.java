package com.leaves.leavedemo.services;

import com.leaves.leavedemo.entities.Organization;
import com.leaves.leavedemo.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    // Create a new organization
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    // Update an existing organization
    public Organization updateOrganization(Long id, Organization organization) {
        if (!organizationRepository.existsById(id)) {
            throw new RuntimeException("Organization not found");
        }
        organization.setId(id);
        return organizationRepository.save(organization);
    }

    // Delete an organization
    public void deleteOrganization(Long id) {
        if (!organizationRepository.existsById(id)) {
            throw new RuntimeException("Organization not found");
        }
        organizationRepository.deleteById(id);
    }

    // Get an organization by ID
    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
    }

    // Get all organizations
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
}