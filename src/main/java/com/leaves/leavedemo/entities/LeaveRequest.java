package com.leaves.leavedemo.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
     private Employee employee; // Employee who requests the leave

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status = LeaveStatus.PENDING; // PENDING by default

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "approval_flow_id", nullable = false)
    private ApprovalFlow approvalFlow; // Reference to the ApprovalFlow entity

    
    @ManyToOne
    @JoinColumn(name = "current_approver_id")
   private OrgLeaveApprover currentApprover; // Keeps track of which approver currently has the request
    private LocalDate requestDate; // Date when leave was requested

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}

	public ApprovalFlow getApprovalFlow() {
		return approvalFlow;
	}

	public void setApprovalFlow(ApprovalFlow approvalFlow) {
		this.approvalFlow = approvalFlow;
	}

	public OrgLeaveApprover getCurrentApprover() {
		return currentApprover;
	}

	public void setCurrentApprover(OrgLeaveApprover currentApprover) {
		this.currentApprover = currentApprover;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

    // Getters and Setters
	
    
}
