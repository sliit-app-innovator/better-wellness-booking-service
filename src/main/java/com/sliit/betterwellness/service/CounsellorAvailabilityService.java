package com.sliit.betterwellness.service;

import com.sliit.betterwellness.dto.CounsellorAvailabilityDTO;
import com.sliit.betterwellness.entity.Counsellor;
import com.sliit.betterwellness.entity.CounsellorAvailability;
import com.sliit.betterwellness.repository.CounsellorAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CounsellorAvailabilityService {

	private final CounsellorAvailabilityRepository counsellorAvailabilityRepository;

	@Autowired
	public CounsellorAvailabilityService(CounsellorAvailabilityRepository counsellorAvailabilityRepository) {
		this.counsellorAvailabilityRepository = counsellorAvailabilityRepository;
	}

	public CounsellorAvailabilityDTO save(CounsellorAvailabilityDTO counsellorAvailabilityDTO){
		counsellorAvailabilityRepository.save(counsellorAvailabilityDTO.toEntity());
		CounsellorAvailability counsellorAvailability = counsellorAvailabilityRepository.getAvailability(counsellorAvailabilityDTO.getCounsellorId(), counsellorAvailabilityDTO.getDate(), counsellorAvailabilityDTO.getStartTime());
		return counsellorAvailability.toDTO();
	}

	public List<CounsellorAvailabilityDTO> search(int counsellorId, String date){
		List<CounsellorAvailability> counsellors = counsellorAvailabilityRepository.search(counsellorId, date);
		return counsellors.stream()
				.map(CounsellorAvailability::toDTO)
				.toList();
	}

	public CounsellorAvailabilityDTO delete(int id){
		Optional<CounsellorAvailability> counsellorAvailabilityOp = counsellorAvailabilityRepository.findById(id);
		if(counsellorAvailabilityOp.isPresent()) {
			CounsellorAvailability counsellorAvailability = counsellorAvailabilityOp.get();
			counsellorAvailabilityRepository.deleteById(id);
			return counsellorAvailability.toDTO();
		}
		throw new RuntimeException();
	}
}
