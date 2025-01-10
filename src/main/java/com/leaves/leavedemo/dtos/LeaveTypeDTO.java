package com.leaves.leavedemo.dtos;

import lombok.Data;


public class LeaveTypeDTO {

    private Long leaveTypeId;
    private String name;
    private int available;
    public LeaveTypeDTO(Long leaveTypeId, String name, int available) {
        this.leaveTypeId = leaveTypeId;
        this.name = name;
        this.available = available;
        
    }
	public Long getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(Long leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
    
}
