package com.leaves.leavedemo.dtos;

import com.leaves.leavedemo.entities.EmployerType;
import lombok.Data;

import java.util.List;


public class ApprovalFlowDTO {  
    private Long orgId;
    private List<Long> approverIds;
    private EmployerType employerType;
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public List<Long> getApproverIds() {
		return approverIds;
	}
	public void setApproverIds(List<Long> approverIds) {
		this.approverIds = approverIds;
	}
	public EmployerType getEmployerType() {
		return employerType;
	}
	public void setEmployerType(EmployerType employerType) {
		this.employerType = employerType;
	}
    
}