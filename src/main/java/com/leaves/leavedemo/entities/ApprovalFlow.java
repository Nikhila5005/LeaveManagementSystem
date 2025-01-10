package com.leaves.leavedemo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class ApprovalFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-One relationship with Organization
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    // Enumerated field for EmployeeType
    @Enumerated(EnumType.STRING)
    private EmployerType employerType;

    // Many-to-Many relationship for list of approvers
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "approval_flow_approvers",
            joinColumns = @JoinColumn(name = "approval_flow_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> approvers;

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

	public EmployerType getEmployerType() {
		return employerType;
	}

	public void setEmployerType(EmployerType employerType) {
		this.employerType = employerType;
	}

	public List<Employee> getApprovers() {
		return approvers;
	}

	public void setApprovers(List<Employee> approvers) {
		this.approvers = approvers;
	}
    
}