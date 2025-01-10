package com.leaves.leavedemo.repositories;

import com.leaves.leavedemo.entities.LeaveCombination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveCombinationRepository extends JpaRepository<LeaveCombination, Long> {

    // Fetch combinations where isAllowed is true
    List<LeaveCombination> findByIsAllowed(boolean isAllowed);
    List<LeaveCombination> findByPrimaryLeaveTypeId(Long primaryLeaveTypeId);
}
