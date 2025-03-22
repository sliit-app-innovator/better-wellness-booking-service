package com.sliit.betterwellness.repository;

import com.sliit.betterwellness.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	@Transactional
	@Query(value ="SELECT a.* FROM appointment a INNER JOIN counsellor_availability b ON a.availability_id = b.id WHERE a.customer_id=:customerId OR 0=:customerId ORDER BY b.date DESC", nativeQuery = true)
	List<Appointment> findByCustomerId(int customerId);

	@Transactional
	@Query(value ="SELECT a.* FROM appointment a INNER JOIN counsellor_availability b ON a.availability_id = b.id WHERE a.counsellor_id=:counsellorId OR 0=:counsellorId ORDER BY b.date DESC", nativeQuery = true)
	List<Appointment> findByCounsellorId(int counsellorId);

	Appointment findByAvailabilityId(int availabilityId);
}
