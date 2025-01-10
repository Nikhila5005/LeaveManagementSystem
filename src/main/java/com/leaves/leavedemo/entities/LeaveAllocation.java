package com.leaves.leavedemo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity

public class LeaveAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "leave_type_id", referencedColumnName = "id")
    private LeaveType leaveType;

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organization organization;

    private int available;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "policy_id", referencedColumnName = "id")
    private OrganizationLeavePolicy organizationLeavePolicy;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LeaveType getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public OrganizationLeavePolicy getOrganizationLeavePolicy() {
		return organizationLeavePolicy;
	}
	public void setOrganizationLeavePolicy(OrganizationLeavePolicy organizationLeavePolicy) {
		this.organizationLeavePolicy = organizationLeavePolicy;
	}
    
    


}
