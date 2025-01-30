package com.assessment360.app.service;

import java.util.List;

import com.assessment360.app.dto.CreateNewCollegeDto;
import com.assessment360.app.entity.College;

public interface CollegeService {
	
	public void createCollege(CreateNewCollegeDto pNewCollege);
	
	public List<College> getAllCollege();
	
	public College getCollegeById(Long pCollegeId);
	
	public College updateCollege(College pCollege);
	
	public College disableCollege(Long pCollegeId);
	
}
