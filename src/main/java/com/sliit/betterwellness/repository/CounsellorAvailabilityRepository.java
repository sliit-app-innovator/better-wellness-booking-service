package com.sliit.betterwellness.repository;

import com.sliit.betterwellness.entity.CounsellorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CounsellorAvailabilityRepository extends JpaRepository<CounsellorAvailability, Integer> {


	@Transactional
	@Query(value ="SELECT * FROM counsellor_availability WHERE counsellor_id=:counsellorId AND date=:date AND start_time=:startTime LIMIT 1", nativeQuery = true)
	CounsellorAvailability getAvailability(int counsellorId, String date, String startTime);

	@Transactional
	@Query(value ="SELECT * FROM counsellor_availability WHERE (counsellor_id=:counsellorId OR 0=:counsellorId) AND (date=:date OR ''=:date)", nativeQuery = true)
	List<CounsellorAvailability> search(int counsellorId, String date);
}
