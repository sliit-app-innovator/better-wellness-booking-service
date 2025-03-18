package com.sliit.betterwellness.controller;

import com.sliit.betterwellness.dto.AppointmentDTO;
import com.sliit.betterwellness.dto.CustomerDTO;
import com.sliit.betterwellness.service.AppointmentService;
import com.sliit.betterwellness.service.CustomerService;
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
public class AppointmentController {


	private final AppointmentService appointmentService;

	@Autowired
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	@PostMapping("/appointment")
	public ResponseEntity<AppointmentDTO> save(@RequestBody AppointmentDTO appointmentDTO, @RequestHeader Map<String, String> headers){
	//	log.info("Customer save request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.save(appointmentDTO));
	}

	@GetMapping("/appointment/customer")
	public ResponseEntity<List<AppointmentDTO>> searchCustomerAppointment(@RequestParam(name = "customerId", required = false, defaultValue = "") String customerId, @RequestHeader Map<String, String> headers){
	//	log.info("Customer search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.OK).body(appointmentService.searchCustomerId(customerId != null && !customerId.isEmpty()? Integer.parseInt(customerId): 0));
	}

	@GetMapping("/appointment/counsellor")
	public ResponseEntity<List<AppointmentDTO>> searchCounsellorAppointment(@RequestParam(name = "counsellorId", required = false, defaultValue = "") String counsellorId, @RequestHeader Map<String, String> headers){
	//	log.info("Customer get request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.OK).body(appointmentService.searchCounsellor(counsellorId != null && !counsellorId.isEmpty()? Integer.parseInt(counsellorId): 0));
	}

	@DeleteMapping("/appointment/{appointmentId}")
	public ResponseEntity<AppointmentDTO> deleteUser(@PathVariable int appointmentId, @RequestHeader Map<String, String> headers){
	//	log.info("Customer delete request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.delete(appointmentId));
	}
}
