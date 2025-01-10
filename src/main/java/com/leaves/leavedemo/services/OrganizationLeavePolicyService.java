package com.leaves.leavedemo.services;

import com.leaves.leavedemo.dtos.OrganizationLeavePolicyDTO;
import com.leaves.leavedemo.entities.*;
import com.leaves.leavedemo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationLeavePolicyService {

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @Autowired
    private LeaveAllocationRepository leaveAllocationRepository;

    @Autowired
    private OrganizationLeavePolicyRepository policyRepository;

    @Autowired
    private LeaveCombinationRepository leaveCombinationRepository;

    @Autowired
    private OrganizationRepository organizationRepository; // Inject organization repository
    @Autowired
    private OrganizationService organizationService; // Inject organization service

    @Autowired
    private LeaveTypeService leaveTypeService; // Inject leave type service

    public OrganizationLeavePolicy createPolicy(OrganizationLeavePolicyDTO dto) {
        // Find the primary leave type based on name
        LeaveType primaryLeaveType = leaveTypeService.getLeaveTypeByName(dto.getPrimaryLeaveTypeName());
        Organization organization = organizationService.getOrganizationById(dto.getOrganizationId());

        // Create the policy
        OrganizationLeavePolicy policy = new OrganizationLeavePolicy();
        policy.setPrimaryLeaveType(primaryLeaveType);
        policy.setOrganization(organization);
        policy.setEmployerType(dto.getEmployerType());

        // Save the policy
        OrganizationLeavePolicy savedPolicy = policyRepository.save(policy);

        // Handle Leave Allocation
        LeaveAllocation leaveAllocation = createLeaveAllocation(dto, primaryLeaveType, savedPolicy);
        leaveAllocationRepository.save(leaveAllocation);

        // Handle Leave Combinations
        if (dto.getAllowedLeaveTypeIds() != null && !dto.getAllowedLeaveTypeIds().isEmpty()) {
            createLeaveCombinations(dto.getAllowedLeaveTypeIds(), primaryLeaveType, savedPolicy);
        }
        // Set the isPaid value for the primary leave type
        primaryLeaveType.setPaid(dto.isPaid()); // <-- Added this line
        leaveTypeRepository.save(primaryLeaveType);

        // Add the allocations and combinations to the policy
        savedPolicy.setLeaveAllocations(List.of(leaveAllocation));
        savedPolicy.setLeaveCombinations(leaveCombinationRepository.findByPrimaryLeaveTypeId(primaryLeaveType.getId()));

        return savedPolicy;
    }

    private LeaveAllocation createLeaveAllocation(OrganizationLeavePolicyDTO dto, LeaveType primaryLeaveType, OrganizationLeavePolicy savedPolicy) {
        LeaveAllocation leaveAllocation = new LeaveAllocation();
        leaveAllocation.setLeaveType(primaryLeaveType);
        leaveAllocation.setAvailable(dto.getAvailable());
        leaveAllocation.setOrganization(savedPolicy.getOrganization());
        leaveAllocation.setOrganizationLeavePolicy(savedPolicy);
        return leaveAllocation;
    }

    private void createLeaveCombinations(List<Long> allowedLeaveTypeIds, LeaveType primaryLeaveType, OrganizationLeavePolicy savedPolicy) {
        // Create a single leave combination entry for the primary leave type
        LeaveCombination leaveCombination = new LeaveCombination();
        leaveCombination.setPrimaryLeaveType(primaryLeaveType);
        leaveCombination.setIsAllowed(true);
        leaveCombination.setOrganizationLeavePolicy(savedPolicy);

        // Iterate through allowed leave type IDs and add them to the leave combination
        for (Long leaveTypeId : allowedLeaveTypeIds) {
            LeaveType allowedLeaveType = leaveTypeService.getLeaveTypeById(leaveTypeId);
            leaveCombination.getAllowedLeaveTypes().add(allowedLeaveType);
        }

        // Save the single leave combination
        leaveCombinationRepository.save(leaveCombination);
    }


    public List<OrganizationLeavePolicy> getPoliciesByOrganization(Long organizationId) {
        // Check if the organization exists first
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        // Retrieve and return all policies by organization
        return policyRepository.findByOrganization(organization);
    }

}

