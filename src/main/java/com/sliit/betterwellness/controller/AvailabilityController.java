package com.sliit.betterwellness.controller;

import com.sliit.betterwellness.dto.CounsellorAvailabilityDTO;
import com.sliit.betterwellness.dto.CounsellorAvailabilityDTOs;
import com.sliit.betterwellness.dto.CounsellorDTO;
import com.sliit.betterwellness.service.CounsellorAvailabilityService;
import com.sliit.betterwellness.service.CounsellorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/better-wellness/booking")
@Slf4j
public class AvailabilityController {


	private final CounsellorAvailabilityService counsellorAvailabilityService;

	@Autowired
	public AvailabilityController(CounsellorAvailabilityService counsellorAvailabilityService) {
		this.counsellorAvailabilityService = counsellorAvailabilityService;
	}

	@PostMapping("/availability")
	public ResponseEntity<CounsellorAvailabilityDTO> updateUser(@RequestBody CounsellorAvailabilityDTO counsellorAvailabilityDTO, @RequestHeader Map<String, String> headers){
	//	log.info("Counsellor save request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.CREATED).body(counsellorAvailabilityService.save(counsellorAvailabilityDTO));
	}

	@GetMapping("/availability")
	public ResponseEntity<CounsellorAvailabilityDTOs> search(@RequestParam(name = "counsellorId", required = false, defaultValue = "") String counsellorId,
																  @RequestParam(name = "date", required = false, defaultValue = "") String date,
																  @RequestHeader Map<String, String> headers){
		//	log.info("Counsellor save request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		CounsellorAvailabilityDTOs counsellorAvailabilityDTOs = new CounsellorAvailabilityDTOs();
		counsellorAvailabilityDTOs.setSlots(counsellorAvailabilityService.search(Integer.parseInt(counsellorId != null && !counsellorId.isEmpty()? counsellorId : "0"), date));
		return ResponseEntity.status(HttpStatus.CREATED).body(counsellorAvailabilityDTOs);
	}

	@DeleteMapping("/availability/{id}")
	public ResponseEntity<CounsellorAvailabilityDTO> search(@PathVariable int id,
																  @RequestHeader Map<String, String> headers){
		//	log.info("Counsellor save request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.CREATED).body(counsellorAvailabilityService.delete(id));
	}
}
