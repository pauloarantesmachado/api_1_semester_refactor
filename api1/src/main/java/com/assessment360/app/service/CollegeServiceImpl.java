package com.assessment360.app.service;

import java.util.List;
import java.util.Optional;

import com.assessment360.app.dto.CreateNewCollegeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment360.app.entity.College;
import com.assessment360.app.exception.collegeexception.CollegeCreateException;
import com.assessment360.app.exception.collegeexception.CollegeIdInvalidException;
import com.assessment360.app.exception.collegeexception.CollegeNameAlreadyExistException;
import com.assessment360.app.repository.CollegeRepository;

@Service
public class CollegeServiceImpl implements CollegeService {
	
	@Autowired
	public CollegeRepository collegeRepository;

	@Override
	@Transactional
	public void createCollege(CreateNewCollegeDto pNewCollege) {
		
		if(!this.collegeValidateData(pNewCollege)) {
			throw new CollegeCreateException();
		}
		
		if(this.collegeNameAlreadyExist(pNewCollege.collegeName())) {
			throw new CollegeNameAlreadyExistException();
		}
		
		College newCollege = new College(pNewCollege.collegeName());
		this.collegeRepository.save(newCollege);
	}
	
	private Boolean collegeValidateData(CreateNewCollegeDto pCollege) {
		if(pCollege == null || 
		   pCollege.collegeName() == null ||
		   pCollege.collegeName().isBlank()){
		   return false;
		}
		return true;
	}
	
	private Boolean collegeNameAlreadyExist(String pCollegeName) {
		return this.collegeRepository.collegeNameAlreadyExist(pCollegeName).isPresent();
	}

	@Override
	public List<College> getAllCollege() {
		return this.collegeRepository.findAllByStatusActiveOrderedByName();
	}

	@Override
	public College getCollegeById(Long pCollegeId) {
		return this.collegeIdIsValid(pCollegeId);
	}
	
	private College collegeIdIsValid(Long pCollegeId) {
		Optional<College> searchResults = this.collegeRepository.findByIdAndStatusActive(pCollegeId);
		if(searchResults.isPresent()) {
			return searchResults.get();
		}
		throw new CollegeIdInvalidException();
	}

	@Override
	@Transactional
	public College updateCollege(College pCollege) {
		College collegeToUpdate = this.getCollegeById(pCollege.getId());
		if(!pCollege.getCollegeName().equals(collegeToUpdate.getCollegeName())) {
			
			if(this.collegeNameAlreadyExist(pCollege.getCollegeName())) {
				throw new CollegeNameAlreadyExistException();
			}
			
		}
		collegeToUpdate.updateCollege(pCollege);
		return collegeToUpdate;
	}

	@Override
	@Transactional
	public College disableCollege(Long pCollegeId) {
		College collegeDisabled = this.collegeIdIsValid(pCollegeId);
		collegeDisabled.setStatus(0);
		return collegeDisabled;
	}
}
