package com.sliit.betterwellness.dto;

import java.util.Date;

public class AppointmentViewDTO {

	private long id;
	private String alias;
	private int counsellorId;
	private int customerId;
	private int availabilityId;
	private Date createdDate;
	private String notes;
	private String counsellorName;
	private String counsellorSpec;
	private String date;
	private String time;

	public AppointmentViewDTO(long id, String alias, int counsellorId, int customerId, int availabilityId, Date createdDate, String notes) {
		this.id = id;
		this.alias = alias;
		this.counsellorId = counsellorId;
		this.customerId = customerId;
		this.availabilityId = availabilityId;
		this.createdDate = createdDate;
		this.notes = notes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getCounsellorId() {
		return counsellorId;
	}

	public void setCounsellorId(int counsellorId) {
		this.counsellorId = counsellorId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAvailabilityId() {
		return availabilityId;
	}

	public void setAvailabilityId(int availabilityId) {
		this.availabilityId = availabilityId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCounsellorName() {
		return counsellorName;
	}

	public void setCounsellorName(String counsellorName) {
		this.counsellorName = counsellorName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCounsellorSpec() {
		return counsellorSpec;
	}

	public void setCounsellorSpec(String counsellorSpec) {
		this.counsellorSpec = counsellorSpec;
	}
}
