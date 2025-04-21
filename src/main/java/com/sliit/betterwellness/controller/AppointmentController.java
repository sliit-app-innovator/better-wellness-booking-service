package com.sliit.betterwellness.controller;

import com.sliit.betterwellness.dto.AppointmentDTO;
import com.sliit.betterwellness.dto.AppointmentViewDTO;
import com.sliit.betterwellness.dto.CustomerDTO;
import com.sliit.betterwellness.service.AppointmentService;
import com.sliit.betterwellness.service.CustomerService;
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
public class AppointmentController {

	private static final Logger log = LogManager.getLogger(AppointmentController.class);
	private final AppointmentService appointmentService;

	@Autowired
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	@PostMapping("/appointment")
	public ResponseEntity<AppointmentDTO> save(@RequestBody AppointmentDTO appointmentDTO, @RequestHeader Map<String, String> headers){
		log.info("[IN] Appointment save request correlation-id : {}, Request {}", headers.get(Constants.HEADER_CORRELATION_ID), appointmentDTO);
		AppointmentDTO response = appointmentService.save(appointmentDTO);
		log.info("[OUT] Appointment save request correlation-id : {}, Request {}, Response {}", headers.get(Constants.HEADER_CORRELATION_ID), appointmentDTO, response);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/appointment/customer")
	public ResponseEntity<List<AppointmentViewDTO>> searchCustomerAppointment(@RequestParam(name = "customerId", required = false, defaultValue = "") String customerId, @RequestHeader Map<String, String> headers){
		log.info("[IN] Customer search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		List<AppointmentViewDTO> response = appointmentService.searchCustomerId(customerId != null && !customerId.isEmpty()? Integer.parseInt(customerId): 0);
		log.info("[OUT] Customer search request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/appointment/counsellor")
	public ResponseEntity<List<AppointmentViewDTO>> searchCounsellorAppointment(@RequestParam(name = "counsellorId", required = false, defaultValue = "") String counsellorId, @RequestHeader Map<String, String> headers){
		log.info("[IN] Customer get request correlation-id : {}, counsellorId {} ", headers.get(Constants.HEADER_CORRELATION_ID), counsellorId);
		List<AppointmentViewDTO> response = appointmentService.searchCounsellor(counsellorId != null && !counsellorId.isEmpty()? Integer.parseInt(counsellorId): 0);
		log.info("[OUT] Customer get request correlation-id : {}, counsellorId {}, Response {} ", headers.get(Constants.HEADER_CORRELATION_ID), counsellorId, response);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/appointment/{appointmentId}")
	public ResponseEntity<AppointmentDTO> deleteUser(@PathVariable int appointmentId, @RequestHeader Map<String, String> headers){
		log.info("[IN] Customer delete request correlation-id : {}", headers.get(Constants.HEADER_CORRELATION_ID));
		AppointmentDTO response = appointmentService.delete(appointmentId);
		log.info("[OUT] Customer delete request correlation-id : {}, appointmentId {}", headers.get(Constants.HEADER_CORRELATION_ID), appointmentId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
