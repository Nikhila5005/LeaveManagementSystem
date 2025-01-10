package com.leaves.leavedemo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leaves.leavedemo.entities.LeaveRequest;
import com.leaves.leavedemo.entities.LeaveStatus;
import com.leaves.leavedemo.entities.OrgLeaveApprover;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStatusAndCurrentApprover(LeaveStatus status, OrgLeaveApprover approver);
}

