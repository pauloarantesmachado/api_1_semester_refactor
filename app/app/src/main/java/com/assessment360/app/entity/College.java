package com.assessment360.app.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="college")
public class College {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="college_id")
	private Long id;
	
	@Column(name="college_name")
	private String collegeName;
	
	@OneToMany(mappedBy = "college", fetch = FetchType.LAZY)
	private List<CollegeClass> listClass;
	
	public College(String collegeName) {
		this.collegeName = collegeName;
	}

}
