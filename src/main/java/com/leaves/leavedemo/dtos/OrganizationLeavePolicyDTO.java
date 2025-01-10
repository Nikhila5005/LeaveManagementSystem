package com.leaves.leavedemo.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class OrganizationLeavePolicyDTO {
    private String primaryLeaveTypeName;
  
    private Long organizationId;
    private String employerType;
    private boolean isPaid;
    private int available;
    private List<Long> allowedLeaveTypeIds;
	public String getPrimaryLeaveTypeName() {
		return primaryLeaveTypeName;
	}
	public void setPrimaryLeaveTypeName(String primaryLeaveTypeName) {
		this.primaryLeaveTypeName = primaryLeaveTypeName;
	}
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	public String getEmployerType() {
		return employerType;
	}
	public void setEmployerType(String employerType) {
		this.employerType = employerType;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public List<Long> getAllowedLeaveTypeIds() {
		return allowedLeaveTypeIds;
	}
	public void setAllowedLeaveTypeIds(List<Long> allowedLeaveTypeIds) {
		this.allowedLeaveTypeIds = allowedLeaveTypeIds;
	}
    

}
