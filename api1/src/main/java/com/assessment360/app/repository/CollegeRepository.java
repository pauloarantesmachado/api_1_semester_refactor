package com.assessment360.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assessment360.app.entity.College;

public interface CollegeRepository extends JpaRepository<College,Long> {
	
	@Query("""
			SELECT c
			FROM College c
			WHERE
			REPLACE(LOWER(c.collegeName),' ','') = REPLACE(LOWER(:pCollegeName),' ','')
			""")
	public Optional<College> collegeNameAlreadyExist(String pCollegeName);
	
	@Query("""
			SELECT c
			FROM College c
			WHERE
			c.id = :pIdCollege AND c.status =1
			""")
	public Optional<College> findByIdAndStatusActive(Long pIdCollege);
		
	@Query("""
		    SELECT c
		    FROM College c
		    WHERE c.status = 1
		    ORDER BY c.collegeName ASC
		""")
	public List<College> findAllByStatusActiveOrderedByName();

}
