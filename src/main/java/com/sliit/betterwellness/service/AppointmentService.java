package com.sliit.betterwellness.service;

import com.sliit.betterwellness.dto.AppointmentDTO;
import com.sliit.betterwellness.dto.CounsellorAvailabilityDTO;
import com.sliit.betterwellness.entity.Appointment;
import com.sliit.betterwellness.entity.CounsellorAvailability;
import com.sliit.betterwellness.repository.AppointmentRepository;
import com.sliit.betterwellness.repository.CounsellorAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

	private final AppointmentRepository appointmentRepository;

	@Autowired
	public AppointmentService(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	public AppointmentDTO save(AppointmentDTO appointmentDTO){
		appointmentDTO.setCreatedDate(new Date());
		appointmentRepository.save(appointmentDTO.toEntity());
		Appointment appointment = appointmentRepository.findByAvailabilityId(appointmentDTO.getAvailabilityId());
		return appointment.toDTO();
	}

	public List<AppointmentDTO> searchCustomerId(int customerId){
		List<Appointment> appointments = appointmentRepository.findByCustomerId(customerId);
		return appointments.stream()
				.map(Appointment::toDTO)
				.toList();
	}

	public List<AppointmentDTO> searchCounsellor(int counsellorId){
		List<Appointment> appointments = appointmentRepository.findByCounsellorId(counsellorId);
		return appointments.stream()
				.map(Appointment::toDTO)
				.toList();
	}

	public AppointmentDTO delete(int id){
		Optional<Appointment> AppointmentOp = appointmentRepository.findById(id);
		if(AppointmentOp.isPresent()) {
			Appointment counsellorAvailability = AppointmentOp.get();
			appointmentRepository.deleteById(id);
			return counsellorAvailability.toDTO();
		}
		throw new RuntimeException();
	}
}
