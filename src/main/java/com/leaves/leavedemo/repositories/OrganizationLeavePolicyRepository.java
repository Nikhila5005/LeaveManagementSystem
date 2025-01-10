package com.leaves.leavedemo.repositories;

import com.leaves.leavedemo.entities.Organization;
import com.leaves.leavedemo.entities.OrganizationLeavePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrganizationLeavePolicyRepository extends JpaRepository<OrganizationLeavePolicy, Long> {
    List<OrganizationLeavePolicy> findByOrganization(Organization organization);

    List<OrganizationLeavePolicy> findByOrganizationId(Long organizationId);
}
