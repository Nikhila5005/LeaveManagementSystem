package com.leaves.leavedemo.repositories;

import com.leaves.leavedemo.entities.ApprovalFlow;
import com.leaves.leavedemo.entities.EmployerType;
import com.leaves.leavedemo.entities.OrgLeaveApprover;
import com.leaves.leavedemo.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrgLeaveApproverRepository extends JpaRepository<OrgLeaveApprover, Long> {

    // Find all approvers for a specific approval flow, ordered by the approver order
    List<OrgLeaveApprover> findByApprovalFlowOrderByApproverOrderAsc(ApprovalFlow approvalFlow);

    // Find all approvers for a specific organization
    List<OrgLeaveApprover> findByOrganization(Organization organization);

//    // Find a specific approver by approval flow and order (to find who is currently assigned)
//    OrgLeaveApprover findByApprovalFlowAndApproverOrder(ApprovalFlow approvalFlow, int approverOrder);
    
 // Find all approvers for a specific organization and employer type
    List<OrgLeaveApprover> findByOrganizationAndApprovalFlowEmployerTypeOrderByApproverOrderAsc(
            Organization organization, EmployerType employerType);
    
    // Find a specific approver by approval flow and order (to find who is currently assigned)
    OrgLeaveApprover findByApprovalFlowAndApproverOrder(ApprovalFlow approvalFlow, int approverOrder);
    
    
    // Find approvers for a specific organization and employer type at a specific order
    List<OrgLeaveApprover> findByOrganizationAndApprovalFlowEmployerTypeAndApproverOrder(
            Organization organization, EmployerType employerType, int approverOrder);
    
    
    List<OrgLeaveApprover> findByOrganizationAndApprovalFlow(Organization organization, ApprovalFlow approvalFlow);

    



    
   
    

}
