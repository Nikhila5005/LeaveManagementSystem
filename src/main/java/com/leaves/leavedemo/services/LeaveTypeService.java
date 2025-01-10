package com.leaves.leavedemo.services;


import com.leaves.leavedemo.dtos.LeaveTypeDTO;
import com.leaves.leavedemo.entities.LeaveAllocation;
import com.leaves.leavedemo.entities.LeaveType;
import com.leaves.leavedemo.repositories.LeaveAllocationRepository;
import com.leaves.leavedemo.repositories.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveTypeService {

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @Autowired
    private LeaveAllocationRepository leaveAllocationRepository;
    public LeaveType getLeaveTypeByName(String name) {
        return leaveTypeRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Leave Type not found"));
    }
    public LeaveType getLeaveTypeById(Long id) {
        return leaveTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave Type not found"));
    }







    // Method to save LeaveType data
    public LeaveType saveLeaveType(LeaveType leaveType) {
        return leaveTypeRepository.save(leaveType);
    }

    // Fetch leave type with allocation and map to DTO
    public LeaveTypeDTO getLeaveTypeWithAllocations(Long leaveTypeId) {
        Optional<LeaveType> leaveTypeOpt = leaveTypeRepository.findById(leaveTypeId);
        if (leaveTypeOpt.isPresent()) {
            LeaveType leaveType = leaveTypeOpt.get();
            List<LeaveAllocation> allocations = leaveAllocationRepository.findByLeaveType_Id(leaveTypeId);

            // Assume one allocation per organization, taking the first one
            LeaveAllocation allocation = allocations.get(0);

            // Map the entity to the DTO
            LeaveTypeDTO leaveTypeDTO = new LeaveTypeDTO(
                    leaveType.getId(),
                    leaveType.getName(),
                    allocation.getAvailable()
            );

            return leaveTypeDTO;
        } else {
            throw new RuntimeException("Leave Type not found");
        }
    }
}
