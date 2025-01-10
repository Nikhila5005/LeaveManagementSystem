package com.leaves.leavedemo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leaves.leavedemo.dtos.LeaveRequestDTO;
import com.leaves.leavedemo.entities.ApprovalFlow;
import com.leaves.leavedemo.entities.Employee;
import com.leaves.leavedemo.entities.LeaveRequest;
import com.leaves.leavedemo.entities.LeaveStatus;
import com.leaves.leavedemo.entities.OrgLeaveApprover;
import com.leaves.leavedemo.entities.Organization;
import com.leaves.leavedemo.repositories.ApprovalFlowRepository;
import com.leaves.leavedemo.repositories.EmployeeRepository;
import com.leaves.leavedemo.repositories.LeaveRequestRepository;
import com.leaves.leavedemo.repositories.OrgLeaveApproverRepository;
import com.leaves.leavedemo.repositories.OrganizationRepository;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

   

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Autowired
    private  ApprovalFlowRepository approvalFlowRepository;

    
    @Autowired
    private OrgLeaveApproverRepository orgLeaveApproverRepository;

  
 // Method to apply for a leave request (no approval flow ID required)
   
//    public LeaveRequest applyLeave(LeaveRequestDTO leaveRequestDTO) {
//        Employee employee = employeeRepository.findById(leaveRequestDTO.getEmployeeId())
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        // Fetch the organization based on the employee's organization mapping
//        Organization organization = employee.getOrganization();
//
//        // Create the leave request
//        LeaveRequest leaveRequest = new LeaveRequest();
//        leaveRequest.setEmployee(employee);
//        leaveRequest.setStartDate(leaveRequestDTO.getStartDate());
//        leaveRequest.setEndDate(leaveRequestDTO.getEndDate());
//        leaveRequest.setReason(leaveRequestDTO.getReason());
//
//        // Set the status to PENDING using the enum
//        leaveRequest.setStatus(LeaveStatus.PENDING);
//
//        // Find the approval flow based on organization and employer type
//        ApprovalFlow approvalFlow = approvalFlowRepository.findByOrganizationAndEmployerType(organization, leaveRequestDTO.getEmployerType())
//                .orElseThrow(() -> new RuntimeException("No approval flow found for this organization and employer type."));
//        
//        leaveRequest.setApprovalFlow(approvalFlow); // Set the approval flow
//
//        // Find the approvers based on organization and approval flow
//        List<OrgLeaveApprover> approvers = orgLeaveApproverRepository
//                .findByOrganizationAndApprovalFlow(organization, approvalFlow); // Use the correct method
//
//        if (approvers.isEmpty()) {
//            throw new RuntimeException("No approvers found for this organization and approval flow.");
//        }
//
//        // Set the first approver automatically without asking the user
//        leaveRequest.setCurrentApprover(approvers.get(0)); // Assuming setCurrentApprover takes OrgLeaveApprover
//
//        return leaveRequestRepository.save(leaveRequest);
//    }

    public LeaveRequest applyLeave(LeaveRequestDTO leaveRequestDTO) {
        Employee employee = employeeRepository.findById(leaveRequestDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Fetch the organization based on the employee's organization mapping
        Organization organization = employee.getOrganization();

        // Create the leave request
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(employee);
        leaveRequest.setStartDate(leaveRequestDTO.getStartDate());
        leaveRequest.setEndDate(leaveRequestDTO.getEndDate());
        leaveRequest.setReason(leaveRequestDTO.getReason());

        // Set the status to PENDING using the enum
        leaveRequest.setStatus(LeaveStatus.PENDING);

        // Find the approval flow based on organization and employer type
        ApprovalFlow approvalFlow = approvalFlowRepository.findByOrganizationAndEmployerType(organization, leaveRequestDTO.getEmployerType())
                .orElseThrow(() -> new RuntimeException("No approval flow found for this organization and employer type."));
        
        leaveRequest.setApprovalFlow(approvalFlow); // Set the approval flow

        // Find the approvers based on organization and approval flow
        List<OrgLeaveApprover> approvers = orgLeaveApproverRepository
                .findByOrganizationAndApprovalFlow(organization, approvalFlow); // Use the correct method

        if (approvers.isEmpty()) {
            throw new RuntimeException("No approvers found for this organization and approval flow.");
        }

        // Set the first approver automatically without asking the user
        leaveRequest.setCurrentApprover(approvers.get(0)); // Assuming setCurrentApprover takes OrgLeaveApprover

        // Save the leave request to the database
        return leaveRequestRepository.save(leaveRequest);
    }

    
    
 

      
      

    

        // Other methods like approveLeave, rejectLeave, updateCurrentApprover...
    public LeaveRequest updateCurrentApprover(Long leaveRequestId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        // Fetch the current approver and their organization/approval flow
        OrgLeaveApprover currentApprover = leaveRequest.getCurrentApprover();
        ApprovalFlow currentApprovalFlow = currentApprover.getApprovalFlow();

        // Fetch the next approver based on the current approver's order in the approval flow
        List<OrgLeaveApprover> nextApprovers = orgLeaveApproverRepository
                .findByOrganizationAndApprovalFlowEmployerTypeAndApproverOrder(
                    currentApprover.getOrganization(),
                    currentApprovalFlow.getEmployerType(), // Ensure to use the correct EmployerType
                    currentApprover.getApproverOrder() + 1); // Get the next approver

        if (!nextApprovers.isEmpty()) {
            leaveRequest.setCurrentApprover(nextApprovers.get(0)); // Set the next approver
            leaveRequest.setStatus(LeaveStatus.PENDING); // Reset status to pending
        } else {
            throw new RuntimeException("No further approvers available.");
        }

        return leaveRequestRepository.save(leaveRequest);
    }


   
   

    // Method to approve a leave request
    public LeaveRequest approveLeave(Long leaveRequestId, Long approverId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        // Check if the approver is valid and belongs to the organization
        OrgLeaveApprover approver = orgLeaveApproverRepository.findById(approverId)
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        // Approve the leave request
        leaveRequest.setStatus(LeaveStatus.APPROVED);
        leaveRequest.setCurrentApprover(approver); // Set the current approver who approved

        return leaveRequestRepository.save(leaveRequest);
    }

    // Method to reject a leave request
    public LeaveRequest rejectLeave(Long leaveRequestId, Long approverId, String rejectionReason) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        // Check if the approver is valid and belongs to the organization
        OrgLeaveApprover approver = orgLeaveApproverRepository.findById(approverId)
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        // Reject the leave request
        leaveRequest.setStatus(LeaveStatus.REJECTED);
        leaveRequest.setCurrentApprover(approver); // Set the current approver who rejected
        leaveRequest.setReason(rejectionReason); // Assuming there's a rejection reason field

        return leaveRequestRepository.save(leaveRequest);
    }

    // Method to update current approver if the duration exceeds
//    public LeaveRequest updateCurrentApprover(Long leaveRequestId) {
//        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
//                .orElseThrow(() -> new RuntimeException("Leave request not found"));
//
//        // Fetch the current approver and their organization/approval flow
//        OrgLeaveApprover currentApprover = leaveRequest.getCurrentApprover();
//        ApprovalFlow currentApprovalFlow = currentApprover.getApprovalFlow();
//        
//        // Fetch the next approver based on the current approver's order in the approval flow
//        List<OrgLeaveApprover> nextApprovers = orgLeaveApproverRepository
//                .findByOrganizationAndEmployerTypeAndApproverOrder(
//                    currentApprover.getOrganization(),
//                    currentApprovalFlow,
//                    currentApprover.getApproverOrder() + 1); // Get the next approver
//
//        if (!nextApprovers.isEmpty()) {
//            leaveRequest.setCurrentApprover(nextApprovers.get(0)); // Set the next approver
//            leaveRequest.setStatus(LeaveStatus.PENDING); // Reset status to pending
//        } else {
//            throw new RuntimeException("No further approvers available.");
//        }
//
//        return leaveRequestRepository.save(leaveRequest);
//    }
    
    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
    }

    // Method to get all leave requests
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll(); // Fetch all leave requests
    }

}

