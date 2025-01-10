package com.leaves.leavedemo.controllers;

import com.leaves.leavedemo.dtos.ApprovalFlowDTO;
import com.leaves.leavedemo.entities.ApprovalFlow;
import com.leaves.leavedemo.entities.EmployerType;
import com.leaves.leavedemo.services.ApprovalFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/approval-flows")
public class ApprovalFlowController {

    @Autowired
    private ApprovalFlowService approvalFlowService;


    // Create a new approval flow

    // Create a new approval flow
    @PostMapping
    public ResponseEntity<ApprovalFlow> createApprovalFlow(@RequestBody ApprovalFlowDTO approvalFlowDTO) {
        ApprovalFlow createdApprovalFlow = approvalFlowService.createApprovalFlow(
                approvalFlowDTO.getOrgId(),
                approvalFlowDTO.getApproverIds(),
                approvalFlowDTO.getEmployerType()
        );
        return ResponseEntity.ok(createdApprovalFlow);
    }

    // Get all approval flows
    @GetMapping
    public ResponseEntity<List<ApprovalFlow>> getAllApprovalFlows() {
        List<ApprovalFlow> approvalFlows = approvalFlowService.getAllApprovalFlows();
        return ResponseEntity.ok(approvalFlows);
    }

    // Get a specific approval flow by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApprovalFlow> getApprovalFlowById(@PathVariable Long id) {
        ApprovalFlow approvalFlow = approvalFlowService.getApprovalFlowById(id)
                .orElseThrow(() -> new RuntimeException("ApprovalFlow not found"));
        return ResponseEntity.ok(approvalFlow);
    }

    // Delete an approval flow by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApprovalFlow(@PathVariable Long id) {
        approvalFlowService.deleteApprovalFlow(id);
        return ResponseEntity.noContent().build();
    }
}
