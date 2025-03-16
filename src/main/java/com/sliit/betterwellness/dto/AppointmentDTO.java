package com.sliit.betterwellness.dto;

import com.sliit.betterwellness.entity.Appointment;
import lombok.Data;
import org.modelmapper.ModelMapper;
import java.util.Date;

@Data
public class AppointmentDTO {
    private Long id;
    private String alias;
    private int counsellorId;
    private int customerId;
    private int availabilityId;
    private Date createdDate;
    private String notes;

    public Long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Appointment toEntity() {
        ModelMapper modelMapper = new ModelMapper();
        Appointment appointment = modelMapper.map(this, Appointment.class);
        appointment.setCreatedDate(new Date());
        return appointment;
    }

}
