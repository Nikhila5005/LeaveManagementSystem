package com.leaves.leavedemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leaves.leavedemo.dtos.OrgLeaveApproverDTO;
import com.leaves.leavedemo.entities.OrgLeaveApprover;
import com.leaves.leavedemo.services.OrgLeaveApproverService;

@RestController
@RequestMapping("/api/org-leave-approvers")
public class OrgLeaveApproverController {

    @Autowired
    private OrgLeaveApproverService orgLeaveApproverService;

    @PostMapping()
    public ResponseEntity<OrgLeaveApprover> createOrgLeaveApprover(@RequestBody @Validated OrgLeaveApproverDTO dto) {
        OrgLeaveApprover createdOrgLeaveApprover = orgLeaveApproverService.createOrgLeaveApprover(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrgLeaveApprover);
    }
}
