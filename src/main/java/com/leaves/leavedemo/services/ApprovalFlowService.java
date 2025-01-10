package com.leaves.leavedemo.services;

import com.leaves.leavedemo.entities.ApprovalFlow;
import com.leaves.leavedemo.entities.Employee;
import com.leaves.leavedemo.entities.EmployerType;
import com.leaves.leavedemo.entities.Organization;
import com.leaves.leavedemo.repositories.ApprovalFlowRepository;
import com.leaves.leavedemo.repositories.EmployeeRepository;
import com.leaves.leavedemo.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApprovalFlowService {

    @Autowired
    private ApprovalFlowRepository approvalFlowRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    // Method to create a new approval flow
    public ApprovalFlow createApprovalFlow(Long orgId, List<Long> approverIds, EmployerType employerType) {
        Organization organization = organizationRepository.findById(orgId)
                .orElseThrow(() -> new RuntimeException("Organization not found"));

        List<Employee> approvers = employeeRepository.findAllById(approverIds);
        ApprovalFlow approvalFlow = new ApprovalFlow();
        approvalFlow.setOrganization(organization);
        approvalFlow.setApprovers(approvers);
        approvalFlow.setEmployerType(employerType);

        return approvalFlowRepository.save(approvalFlow);
    }

    // Method to get all approval flows
    public List<ApprovalFlow> getAllApprovalFlows() {
        return approvalFlowRepository.findAll();
    }

    // Method to get approval flow by ID
    public Optional<ApprovalFlow> getApprovalFlowById(Long id) {
        return approvalFlowRepository.findById(id);
    }

    // Method to delete approval flow by ID
    public void deleteApprovalFlow(Long id) {
        approvalFlowRepository.deleteById(id);
    }
}
