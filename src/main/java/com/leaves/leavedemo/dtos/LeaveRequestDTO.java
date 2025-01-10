package com.leaves.leavedemo.dtos;

import java.time.LocalDate;

import com.leaves.leavedemo.entities.ApprovalFlow;
import com.leaves.leavedemo.entities.EmployerType;

public class LeaveRequestDTO {
    private Long employeeId;
    private Long approvalFlowId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
     private EmployerType employerType; // Ensure this is present

	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getApprovalFlowId() {
		return approvalFlowId;
	}
	public void setApprovalFlowId(Long approvalFlowId) {
		this.approvalFlowId = approvalFlowId;
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
	public EmployerType getEmployerType() {
		return employerType;
	}
	public void setEmployerType(EmployerType employerType) {
		this.employerType = employerType;
	}
	
	
	

   
}

