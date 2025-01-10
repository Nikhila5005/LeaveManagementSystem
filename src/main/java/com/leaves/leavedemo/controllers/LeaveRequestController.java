package com.leaves.leavedemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.leaves.leavedemo.dtos.LeaveRequestDTO;
import com.leaves.leavedemo.entities.LeaveRequest;
import com.leaves.leavedemo.services.LeaveRequestService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    // Apply for a leave request
    @PostMapping
    public ResponseEntity<LeaveRequest> applyLeave(@RequestBody LeaveRequestDTO leaveRequestDTO) {
        LeaveRequest leaveRequest = leaveRequestService.applyLeave(leaveRequestDTO);
        return new ResponseEntity<>(leaveRequest, HttpStatus.CREATED);
    }
   

    // Approve a leave request
    @PutMapping("/approve/{leaveRequestId}")
    public ResponseEntity<LeaveRequest> approveLeave(
            @PathVariable Long leaveRequestId,
            @RequestParam Long approverId) {
        LeaveRequest leaveRequest = leaveRequestService.approveLeave(leaveRequestId, approverId);
        return ResponseEntity.ok(leaveRequest);
    }

    // Reject a leave request
    @PutMapping("/reject/{leaveRequestId}")
    public ResponseEntity<LeaveRequest> rejectLeave(
            @PathVariable Long leaveRequestId,
            @RequestParam Long approverId,
            @RequestParam String rejectionReason) {
        LeaveRequest leaveRequest = leaveRequestService.rejectLeave(leaveRequestId, approverId, rejectionReason);
        return ResponseEntity.ok(leaveRequest);
    }

    // Update current approver if the duration exceeds
    @PutMapping("/update-approver/{leaveRequestId}")
    public ResponseEntity<LeaveRequest> updateCurrentApprover(@PathVariable Long leaveRequestId) {
        LeaveRequest leaveRequest = leaveRequestService.updateCurrentApprover(leaveRequestId);
        return ResponseEntity.ok(leaveRequest);
    }

    // Get leave request by ID
    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable Long id) {
        LeaveRequest leaveRequest = leaveRequestService.getLeaveRequestById(id);
        return ResponseEntity.ok(leaveRequest);
    }

    // Get all leave requests
    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = leaveRequestService.getAllLeaveRequests();
        return ResponseEntity.ok(leaveRequests);
    }
}



