package com.leaves.leavedemo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity

public class LeaveCombination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The primary leave type (e.g., Casual Leave)
    @ManyToOne
    @JoinColumn(name = "primary_leave_type_id", referencedColumnName = "id")
    private LeaveType primaryLeaveType;

    // List of allowed leave types that can be combined with the primary one
    @ManyToMany
    @JoinTable(
            name = "leave_combination_types",
            joinColumns = @JoinColumn(name = "combination_id"),
            inverseJoinColumns = @JoinColumn(name = "leave_type_id")
    )
    private List<LeaveType> allowedLeaveTypes = new ArrayList<>();


    // Admin sets this flag to true for allowed combinations
    private boolean isAllowed;
    // Setter method
    @Setter
    @ManyToOne
    @JoinColumn(name = "organization_leave_policy_id")
    private OrganizationLeavePolicy organizationLeavePolicy;
    


    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public LeaveType getPrimaryLeaveType() {
		return primaryLeaveType;
	}



	public void setPrimaryLeaveType(LeaveType primaryLeaveType) {
		this.primaryLeaveType = primaryLeaveType;
	}



	public List<LeaveType> getAllowedLeaveTypes() {
		return allowedLeaveTypes;
	}



	public void setAllowedLeaveTypes(List<LeaveType> allowedLeaveTypes) {
		this.allowedLeaveTypes = allowedLeaveTypes;
	}



	public boolean isAllowed() {
		return isAllowed;
	}



	public void setAllowed(boolean isAllowed) {
		this.isAllowed = isAllowed;
	}



	public OrganizationLeavePolicy getOrganizationLeavePolicy() {
		return organizationLeavePolicy;
	}



	public void setOrganizationLeavePolicy(OrganizationLeavePolicy organizationLeavePolicy) {
		this.organizationLeavePolicy = organizationLeavePolicy;
	}



	public void setIsAllowed(boolean isAllowed) {
        this.isAllowed = isAllowed;
    }


}
