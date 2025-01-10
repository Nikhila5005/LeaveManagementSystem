package com.leaves.leavedemo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity

public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isPaid;

    @Enumerated(EnumType.STRING)
    private LeaveDuration duration;

    public boolean setPaid(boolean paid) {
        return isPaid;
    }

    public enum LeaveDuration {
        HOURS, DAYS
    }
    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LeaveDuration getDuration() {
		return duration;
	}
	public void setDuration(LeaveDuration duration) {
		this.duration = duration;
	}
    


}
