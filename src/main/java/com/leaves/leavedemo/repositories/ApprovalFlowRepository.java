package com.leaves.leavedemo.repositories;

import com.leaves.leavedemo.entities.ApprovalFlow;
import com.leaves.leavedemo.entities.EmployerType;
import com.leaves.leavedemo.entities.Organization;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalFlowRepository extends JpaRepository<ApprovalFlow, Long> {
	
	
    Optional<ApprovalFlow> findByOrganizationAndEmployerType(Organization organization, EmployerType employerType);

}