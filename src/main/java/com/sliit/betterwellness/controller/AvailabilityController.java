package com.sliit.betterwellness.controller;

import com.sliit.betterwellness.dto.CounsellorAvailabilityDTO;
import com.sliit.betterwellness.dto.CounsellorAvailabilityDTOs;
import com.sliit.betterwellness.dto.CounsellorDTO;
import com.sliit.betterwellness.service.CounsellorAvailabilityService;
import com.sliit.betterwellness.service.CounsellorService;
import com.sliit.betterwellness.utills.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointment-service")
public class AvailabilityController {

	private static final Logger log = LogManager.getLogger(AvailabilityController.class);
	private final CounsellorAvailabilityService counsellorAvailabilityService;

	@Autowired
	public AvailabilityController(CounsellorAvailabilityService counsellorAvailabilityService) {
		this.counsellorAvailabilityService = counsellorAvailabilityService;
	}

	@PostMapping("/availability")
	public ResponseEntity<CounsellorAvailabilityDTO> updateUser(@RequestBody CounsellorAvailabilityDTO counsellorAvailabilityDTO, @RequestHeader Map<String, String> headers){
		log.info("[IN] Counsellor save request correlation-id : {}, Request {}", headers.get(Constants.HEADER_CORRELATION_ID), counsellorAvailabilityDTO);
		CounsellorAvailabilityDTO response = counsellorAvailabilityService.save(counsellorAvailabilityDTO);
		log.info("[OUT] Counsellor save request correlation-id : {}, Request {}, Response {}", headers.get(Constants.HEADER_CORRELATION_ID), counsellorAvailabilityDTO, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/availability")
	public ResponseEntity<CounsellorAvailabilityDTOs> search(@RequestParam(name = "counsellorId", required = false, defaultValue = "") String counsellorId,
																  @RequestParam(name = "date", required = false, defaultValue = "") String date,
																  @RequestHeader Map<String, String> headers){
		log.info("[IN] Counsellor save request correlation-id : {}, counsellorId {} ", headers.get(Constants.HEADER_CORRELATION_ID), counsellorId);
		CounsellorAvailabilityDTOs counsellorAvailabilityDTOs = new CounsellorAvailabilityDTOs();
		counsellorAvailabilityDTOs.setSlots(counsellorAvailabilityService.search(Integer.parseInt(counsellorId != null && !counsellorId.isEmpty()? counsellorId : "0"), date));
		log.info("[OUT] Counsellor save request correlation-id : {}, counsellorId {} Response {}", headers.get(Constants.HEADER_CORRELATION_ID), counsellorId, counsellorAvailabilityDTOs);
		return ResponseEntity.status(HttpStatus.CREATED).body(counsellorAvailabilityDTOs);
	}

	@DeleteMapping("/availability/{id}")
	public ResponseEntity<CounsellorAvailabilityDTO> search(@PathVariable int id,
																  @RequestHeader Map<String, String> headers){
		log.info("[IN] Counsellor save request correlation-id : {}, ID {}", headers.get(Constants.HEADER_CORRELATION_ID), id);
		CounsellorAvailabilityDTO response = counsellorAvailabilityService.delete(id);
		log.info("[OUT] Counsellor save request correlation-id : {}, ID {}, response {}", headers.get(Constants.HEADER_CORRELATION_ID), id, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
