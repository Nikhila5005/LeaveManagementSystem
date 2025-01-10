package com.leaves.leavedemo.repositories;

import com.leaves.leavedemo.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    // Add custom query methods if needed
    Organization findByName(String name);
    

}