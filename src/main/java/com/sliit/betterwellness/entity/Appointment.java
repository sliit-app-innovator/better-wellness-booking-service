package com.sliit.betterwellness.entity;

import com.sliit.betterwellness.dto.AppointmentDTO;
import com.sliit.betterwellness.dto.AppointmentViewDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "appointment", uniqueConstraints = {
        @UniqueConstraint(columnNames = "availability_id")  // Ensuring availabilityId is unique
})
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "counsellor_id", nullable = false)
    private int counsellorId;

    @Column(name = "alias", nullable = true)
    private String alias;;

    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Column(name = "availability_id", nullable = false)
    private int availabilityId;

    @Column(name = "created_at", nullable = false)
    private Date createdDate;

    @Column(name = "notes")
    private String notes;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public AppointmentDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, AppointmentDTO.class);
    }

    public AppointmentViewDTO toAppointmentViewDTO() {
        return new AppointmentViewDTO(
                this.id, this.alias, this.counsellorId, this.customerId, this.availabilityId, this.createdDate, this.notes
        );
    }
}
