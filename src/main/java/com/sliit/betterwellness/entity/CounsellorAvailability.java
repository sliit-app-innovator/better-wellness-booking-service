package com.sliit.betterwellness.entity;

import com.sliit.betterwellness.dto.CounsellorAvailabilityDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@Entity
@Table(name = "counsellor_availability")
public class CounsellorAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "counsellor_id", nullable = false)
    private int counsellorId;

    @Column(name = "session_name", nullable = false)
    private String sessionName;

    @Column(name = "session_desc", nullable = false,  length = 4000)
    private String sessionDesc;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "start_time", nullable = false)
    private String startTime;

    @Column(name = "end_time", nullable = false)
    private String endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCounsellorId() {
        return counsellorId;
    }

    public void setCounsellorId(int counsellorId) {
        this.counsellorId = counsellorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionDesc() {
        return sessionDesc;
    }

    public void setSessionDesc(String sessionDesc) {
        this.sessionDesc = sessionDesc;
    }

    public CounsellorAvailabilityDTO toDTO() {
        CounsellorAvailabilityDTO counsellor = new CounsellorAvailabilityDTO();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(this, counsellor);
        counsellor.setId(this.getId());
        return counsellor;
    }
}

