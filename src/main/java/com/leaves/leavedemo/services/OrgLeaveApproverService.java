package com.leaves.leavedemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leaves.leavedemo.dtos.OrgLeaveApproverDTO;
import com.leaves.leavedemo.entities.ApprovalFlow;
import com.leaves.leavedemo.entities.Employee;
import com.leaves.leavedemo.entities.OrgLeaveApprover;
import com.leaves.leavedemo.entities.Organization;
import com.leaves.leavedemo.repositories.ApprovalFlowRepository;
import com.leaves.leavedemo.repositories.EmployeeRepository;
import com.leaves.leavedemo.repositories.OrgLeaveApproverRepository;
import com.leaves.leavedemo.repositories.OrganizationRepository;

@Service
public class OrgLeaveApproverService {

    @Autowired
    private OrgLeaveApproverRepository orgLeaveApproverRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ApprovalFlowRepository approvalFlowRepository;

    public OrgLeaveApprover createOrgLeaveApprover(OrgLeaveApproverDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Organization organization = organizationRepository.findById(dto.getOrgId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        ApprovalFlow approvalFlow = approvalFlowRepository.findById(dto.getApprovalFlowId())
                .orElseThrow(() -> new RuntimeException("Approval Flow not found"));

        OrgLeaveApprover orgLeaveApprover = new OrgLeaveApprover();
        orgLeaveApprover.setEmployee(employee);
        orgLeaveApprover.setOrganization(organization);
        orgLeaveApprover.setApprovalFlow(approvalFlow);
//        orgLeaveApprover.setLevel(dto.getLevel());
        orgLeaveApprover.setApproverOrder(dto.getApproverOrder());
        orgLeaveApprover.setApprovalDuration(dto.getApprovalDuration());
        orgLeaveApprover.setCanSkip(dto.getCanSkip());

        return orgLeaveApproverRepository.save(orgLeaveApprover);
    }
}

