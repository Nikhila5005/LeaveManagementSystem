package com.leaves.leavedemo.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrganizationLeavePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "primary_leave_type_id", referencedColumnName = "id")
    private LeaveType primaryLeaveType;

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organization organization;

    private String employerType;

    @JsonManagedReference
    @OneToMany(mappedBy = "organizationLeavePolicy", cascade = CascadeType.ALL)
    private List<LeaveAllocation> leaveAllocations;

    @OneToMany(mappedBy = "organizationLeavePolicy", cascade = CascadeType.ALL)
    private List<LeaveCombination> leaveCombinations;

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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getEmployerType() {
		return employerType;
	}

	public void setEmployerType(String employerType) {
		this.employerType = employerType;
	}

	public List<LeaveAllocation> getLeaveAllocations() {
		return leaveAllocations;
	}

	public void setLeaveAllocations(List<LeaveAllocation> leaveAllocations) {
		this.leaveAllocations = leaveAllocations;
	}

	public List<LeaveCombination> getLeaveCombinations() {
		return leaveCombinations;
	}

	public void setLeaveCombinations(List<LeaveCombination> leaveCombinations) {
		this.leaveCombinations = leaveCombinations;
	}
    
}
