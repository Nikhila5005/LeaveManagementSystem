package com.leaves.leavedemo.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class OrgLeaveApprover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "org_id", nullable = false)
    @JsonBackReference
    private Organization organization; // FK to Organization

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // FK to Employee who requested the leave

    private boolean canSkip=false; // Whether the approver can skip the request

    private LocalDate approvalDuration; // Duration in hours or days for each approver

    private int approverOrder; // The order in which the approvers act based on approval flow

    @ManyToOne
    @JoinColumn(name = "approval_flow_id", nullable = false)
    @JsonBackReference
    private ApprovalFlow approvalFlow; // Reference to the ApprovalFlow entity

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean isCanSkip() {
		return canSkip;
	}

	public void setCanSkip(boolean canSkip) {
		this.canSkip = canSkip;
	}

	public LocalDate getApprovalDuration() {
		return approvalDuration;
	}

	public void setApprovalDuration(LocalDate approvalDuration) {
		this.approvalDuration = approvalDuration;
	}

	public int getApproverOrder() {
		return approverOrder;
	}

	public void setApproverOrder(int approverOrder) {
		this.approverOrder = approverOrder;
	}

	public ApprovalFlow getApprovalFlow() {
		return approvalFlow;
	}

	public void setApprovalFlow(ApprovalFlow approvalFlow) {
		this.approvalFlow = approvalFlow;
	}
    
}

