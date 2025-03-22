package com.sliit.betterwellness.service;

import com.sliit.betterwellness.dto.AppointmentDTO;
import com.sliit.betterwellness.dto.AppointmentViewDTO;
import com.sliit.betterwellness.entity.Appointment;
import com.sliit.betterwellness.entity.Counsellor;
import com.sliit.betterwellness.repository.AppointmentRepository;
import com.sliit.betterwellness.repository.CounsellorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final CounsellorRepository counsellorRepository;

	@Autowired
	public AppointmentService(AppointmentRepository appointmentRepository, CounsellorRepository counsellorRepository) {
		this.appointmentRepository = appointmentRepository;
		this.counsellorRepository = counsellorRepository;
	}

	public AppointmentDTO save(AppointmentDTO appointmentDTO){
		appointmentDTO.setCreatedDate(new Date());
		appointmentRepository.save(appointmentDTO.toEntity());
		Appointment appointment = appointmentRepository.findByAvailabilityId(appointmentDTO.getAvailabilityId());
		return appointment.toDTO();
	}

	public List<AppointmentViewDTO> searchCustomerId(int customerId){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		List<Appointment> appointments = appointmentRepository.findByCustomerId(customerId);
		List<AppointmentViewDTO> list = appointments.stream()
				.map(Appointment::toAppointmentViewDTO)
				.toList();
		if (!list.isEmpty()) {
			for (AppointmentViewDTO appointmentViewDTO : list){
				Counsellor counsellor = counsellorRepository.findById(appointmentViewDTO.getCounsellorId()).get();
				appointmentViewDTO.setCounsellorName(counsellor.getFirstName() + " " + counsellor.getLastName());
				appointmentViewDTO.setDate(dateFormat.format(appointmentViewDTO.getCreatedDate()));
				appointmentViewDTO.setTime(timeFormat.format(appointmentViewDTO.getCreatedDate()));
				appointmentViewDTO.setCounsellorSpec(counsellor.getSpecializations());
			}
		}
		return list;
	}

	public List<AppointmentViewDTO> searchCounsellor(int counsellorId){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		List<Appointment> appointments = appointmentRepository.findByCounsellorId(counsellorId);
		List<AppointmentViewDTO> list = appointments.stream()
				.map(Appointment::toAppointmentViewDTO)
				.toList();
		if (!list.isEmpty()) {
			for (AppointmentViewDTO appointmentViewDTO : list){
				appointmentViewDTO.setDate(dateFormat.format(appointmentViewDTO.getCreatedDate()));
				appointmentViewDTO.setTime(timeFormat.format(appointmentViewDTO.getCreatedDate()));
			}
		}
		return list;
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
