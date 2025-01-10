package com.leaves.leavedemo.services;

import com.leaves.leavedemo.entities.LeaveAllocation;
import com.leaves.leavedemo.entities.LeaveType;
import com.leaves.leavedemo.entities.Organization;
import com.leaves.leavedemo.repositories.LeaveAllocationRepository;
import com.leaves.leavedemo.repositories.LeaveTypeRepository;
import com.leaves.leavedemo.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveAllocationService {

    @Autowired
    private LeaveAllocationRepository leaveAllocationRepository;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    // Create a new LeaveAllocation for a specific LeaveType
    public LeaveAllocation createLeaveAllocation(Long leaveTypeId, Long orgId, int available) {
        Optional<LeaveType> leaveTypeOpt = leaveTypeRepository.findById(leaveTypeId);
        Optional<Organization> organizationOpt = organizationRepository.findById(orgId);

        if (leaveTypeOpt.isPresent() && organizationOpt.isPresent()) {
            LeaveType leaveType = leaveTypeOpt.get();
            Organization organization = organizationOpt.get();

            LeaveAllocation leaveAllocation = new LeaveAllocation();
            leaveAllocation.setLeaveType(leaveType);
            leaveAllocation.setOrganization(organization); // Set the organization
            leaveAllocation.setAvailable(available);

            return leaveAllocationRepository.save(leaveAllocation);
        } else {
            throw new RuntimeException("Leave Type or Organization not found");
        }
    }

    // Method to retrieve all leave allocations for a specific organization
    public List<LeaveAllocation> getLeaveAllocationsForOrganization(Long orgId) {
        return leaveAllocationRepository.findByOrganization_Id(orgId);
    }
}
