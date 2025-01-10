package com.leaves.leavedemo.repositories;

import com.leaves.leavedemo.entities.LeaveAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveAllocationRepository extends JpaRepository<LeaveAllocation, Long> {
    List<LeaveAllocation> findByLeaveType_Id(Long leaveTypeId);

    List<LeaveAllocation> findByOrganization_Id(Long orgId);
}