package com.sliit.betterwellness.dto;

import com.sliit.betterwellness.entity.Counsellor;
import com.sliit.betterwellness.entity.CounsellorAvailability;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CounsellorAvailabilityDTO {
    private int id;
    private String sessionName;
    private String sessionDesc;
    private int counsellorId;
    private String date;
    private String startTime;
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

    public CounsellorAvailability toEntity() {
        CounsellorAvailability counsellor = new CounsellorAvailability();
        counsellor.setCounsellorId(this.getCounsellorId());
        counsellor.setDate(this.getDate());
        counsellor.setSessionName(this.getSessionName());
        counsellor.setSessionDesc(this.getSessionDesc());
        counsellor.setStartTime(this.getStartTime());
        counsellor.setEndTime(this.getEndTime());
        return counsellor;
    }
}
