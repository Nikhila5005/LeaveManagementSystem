package com.leaves.leavedemo.controllers;

import com.leaves.leavedemo.dtos.LeaveTypeDTO;
import com.leaves.leavedemo.entities.LeaveType;
import com.leaves.leavedemo.services.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leavetypes")
public class LeaveTypeController {

    @Autowired
    private LeaveTypeService leaveTypeService;
    // API to create and save a new LeaveType
    @PostMapping("/create")
    public ResponseEntity<LeaveType> createLeaveType(@RequestBody LeaveType leaveType) {
        LeaveType savedLeaveType = leaveTypeService.saveLeaveType(leaveType);
        return ResponseEntity.ok(savedLeaveType);
    }

    // Get leave type by ID with allocated days/hours as DTO
    @GetMapping("/{id}")
    public LeaveTypeDTO getLeaveTypeWithAllocations(@PathVariable Long id) {
        return leaveTypeService.getLeaveTypeWithAllocations(id);
    }
}
