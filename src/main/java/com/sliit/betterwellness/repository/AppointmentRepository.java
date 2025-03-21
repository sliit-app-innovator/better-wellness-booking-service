package com.sliit.betterwellness.repository;

import com.sliit.betterwellness.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	@Transactional
	@Query(value ="SELECT * FROM appointment WHERE customer_id=:customerId OR 0=:customerId", nativeQuery = true)
	List<Appointment> findByCustomerId(int customerId);

	@Transactional
	@Query(value ="SELECT * FROM appointment WHERE counsellor_id=:counsellorId OR 0=:counsellorId", nativeQuery = true)
	List<Appointment> findByCounsellorId(int counsellorId);
	Appointment findByAvailabilityId(int availabilityId);
}
