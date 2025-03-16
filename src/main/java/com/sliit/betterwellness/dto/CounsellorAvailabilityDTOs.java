package com.sliit.betterwellness.dto;

import com.sliit.betterwellness.entity.CounsellorAvailability;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CounsellorAvailabilityDTOs {
   private List<CounsellorAvailabilityDTO> slots = new ArrayList<>();

    public List<CounsellorAvailabilityDTO> getSlots() {
        return slots;
    }

    public void setSlots(List<CounsellorAvailabilityDTO> slots) {
        this.slots = slots;
    }
}
