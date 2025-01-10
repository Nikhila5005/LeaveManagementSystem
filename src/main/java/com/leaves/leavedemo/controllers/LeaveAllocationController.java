package com.leaves.leavedemo.controllers;

import com.leaves.leavedemo.entities.LeaveAllocation;
import com.leaves.leavedemo.services.LeaveAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaveallocations")
public class LeaveAllocationController {

    @Autowired
    private LeaveAllocationService leaveAllocationService;

    // Create a new leave allocation for a leave type
    @PostMapping("/create")
    public ResponseEntity<LeaveAllocation> createLeaveAllocation(
            @RequestParam Long leaveTypeId,
            @RequestParam Long orgId,
            @RequestParam int available) {

        LeaveAllocation leaveAllocation = leaveAllocationService.createLeaveAllocation(leaveTypeId, orgId, available);
        return ResponseEntity.ok(leaveAllocation);
    }
//    @PostMapping("/create")
//    public ResponseEntity<?> createLeaveAllocation(@RequestBody LeaveAllocationRequest request) {
//        try {
//            LeaveAllocation leaveAllocation = leaveAllocationService.createLeaveAllocation(
//                    request.getLeaveTypeId(),
//                    request.getOrgId(),
//                    request.getAvailable()
//            );
//            return ResponseEntity.ok(leaveAllocation);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    // Get all leave allocations for an organization
    @GetMapping("/organization/{orgId}")
    public ResponseEntity<List<LeaveAllocation>> getLeaveAllocationsForOrganization(@PathVariable Long orgId) {
        List<LeaveAllocation> allocations = leaveAllocationService.getLeaveAllocationsForOrganization(orgId);
        return ResponseEntity.ok(allocations);
    }
}
