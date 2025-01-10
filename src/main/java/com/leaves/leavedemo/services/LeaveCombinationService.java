package com.leaves.leavedemo.services;

import com.leaves.leavedemo.entities.LeaveCombination;
import com.leaves.leavedemo.entities.LeaveType;
import com.leaves.leavedemo.repositories.LeaveCombinationRepository;
import com.leaves.leavedemo.repositories.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveCombinationService {

    @Autowired
    private LeaveCombinationRepository leaveCombinationRepository;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;
    public List<LeaveCombination> createLeaveCombinations(List<Long> allowedLeaveTypeIds) {
        List<LeaveCombination> combinations = new ArrayList<>();

        // Logic to create combinations based on allowedLeaveTypeIds
        for (Long primaryId : allowedLeaveTypeIds) {
            LeaveType primaryLeaveType = leaveTypeRepository.findById(primaryId)
                    .orElseThrow(() -> new RuntimeException("Primary leave type not found"));

            for (Long allowedId : allowedLeaveTypeIds) {
                if (!primaryId.equals(allowedId)) {
                    LeaveCombination combination = new LeaveCombination();
                    combination.setPrimaryLeaveType(primaryLeaveType); // Set LeaveType object
                    LeaveType allowedLeaveType = leaveTypeRepository.findById(allowedId)
                            .orElseThrow(() -> new RuntimeException("Allowed leave type not found"));
                    combination.setAllowedLeaveTypes(List.of(allowedLeaveType)); // Set allowed LeaveType list
                    combinations.add(leaveCombinationRepository.save(combination));
                }
            }
        }

        return combinations;
    }


    // Create a leave combination with allowed types
    public LeaveCombination createLeaveCombination(Long primaryLeaveTypeId, List<Long> allowedLeaveTypeIds, boolean isAllowed) {
        Optional<LeaveType> primaryLeaveTypeOpt = leaveTypeRepository.findById(primaryLeaveTypeId);
        if (!primaryLeaveTypeOpt.isPresent()) {
            throw new RuntimeException("Primary leave type not found");
        }

        LeaveCombination leaveCombination = new LeaveCombination();
        leaveCombination.setPrimaryLeaveType(primaryLeaveTypeOpt.get());
        leaveCombination.setAllowedLeaveTypes(leaveTypeRepository.findAllById(allowedLeaveTypeIds));
        leaveCombination.setIsAllowed(isAllowed);

        return leaveCombinationRepository.save(leaveCombination);
    }

    // Get allowed leave combinations
    public List<LeaveCombination> getAllowedCombinations() {
        return leaveCombinationRepository.findByIsAllowed(true);
    }

    // Get all combinations, both allowed and not allowed
    public List<LeaveCombination> getAllCombinations() {
        return leaveCombinationRepository.findAll();
    }
}
