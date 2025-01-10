package com.leaves.leavedemo.dtos;

import java.time.LocalDate;

public class OrgLeaveApproverDTO {
    private Long employeeId;
    private Long orgId;
    private Long approvalFlowId;
    private Integer approverOrder;
    private LocalDate approvalDuration;
    private Boolean canSkip;

    // Getters and Setters

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getApprovalFlowId() {
        return approvalFlowId;
    }

    public void setApprovalFlowId(Long approvalFlowId) {
        this.approvalFlowId = approvalFlowId;
    }

    public Integer getApproverOrder() {
		return approverOrder;
	}

	public void setApproverOrder(Integer approverOrder) {
		this.approverOrder = approverOrder;
	}

	 public LocalDate getApprovalDuration() {
		return approvalDuration;
	}

	public void setApprovalDuration(LocalDate approvalDuration) {
		this.approvalDuration = approvalDuration;
	}

	public Boolean getCanSkip() {
        return canSkip;
    }

    public void setCanSkip(Boolean canSkip) {
        this.canSkip = canSkip;
    }
}
