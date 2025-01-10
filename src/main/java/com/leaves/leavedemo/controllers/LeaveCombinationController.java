package com.leaves.leavedemo.controllers;

import com.leaves.leavedemo.entities.LeaveCombination;
import com.leaves.leavedemo.services.LeaveCombinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leavecombinations")
public class LeaveCombinationController {

    @Autowired
    private LeaveCombinationService leaveCombinationService;

    // Create a new leave combination
    @PostMapping("/create")
    public ResponseEntity<LeaveCombination> createLeaveCombination(
            @RequestParam Long primaryLeaveTypeId,
            @RequestParam List<Long> allowedLeaveTypeIds,
            @RequestParam boolean isAllowed
    ) {
        LeaveCombination createdCombination = leaveCombinationService.createLeaveCombination(primaryLeaveTypeId, allowedLeaveTypeIds, isAllowed);
        return ResponseEntity.ok(createdCombination);
    }

    // Get all allowed leave combinations
    @GetMapping("/allowed")
    public ResponseEntity<List<LeaveCombination>> getAllowedCombinations() {
        List<LeaveCombination> allowedCombinations = leaveCombinationService.getAllowedCombinations();
        return ResponseEntity.ok(allowedCombinations);
    }

    // Get all leave combinations (allowed and not allowed)
    @GetMapping("/all")
    public ResponseEntity<List<LeaveCombination>> getAllCombinations() {
        List<LeaveCombination> allCombinations = leaveCombinationService.getAllCombinations();
        return ResponseEntity.ok(allCombinations);
    }
}
