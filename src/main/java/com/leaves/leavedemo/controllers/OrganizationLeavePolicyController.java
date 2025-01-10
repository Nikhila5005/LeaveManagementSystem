package com.leaves.leavedemo.controllers;

import com.leaves.leavedemo.dtos.OrganizationLeavePolicyDTO;
import com.leaves.leavedemo.entities.OrganizationLeavePolicy;
import com.leaves.leavedemo.services.OrganizationLeavePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class OrganizationLeavePolicyController {

    @Autowired
    private OrganizationLeavePolicyService organizationLeavePolicyService;



    @PostMapping
    public ResponseEntity<OrganizationLeavePolicy> createPolicy(@RequestBody OrganizationLeavePolicyDTO dto) {
        OrganizationLeavePolicy policy = organizationLeavePolicyService.createPolicy(dto);
        return new ResponseEntity<>(policy, HttpStatus.CREATED);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<OrganizationLeavePolicy>> getPoliciesByOrganization(@PathVariable Long organizationId) {
        List<OrganizationLeavePolicy> policies = organizationLeavePolicyService.getPoliciesByOrganization(organizationId);
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }
}
