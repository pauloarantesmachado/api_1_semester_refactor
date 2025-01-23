package com.assessment360.app.entity;

import java.util.List;

import com.assessment360.app.utils.SemesterEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="class")
public class CollegeClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="class_id")
	private Long id;
	
	@Column(name="class_name")
	private String className;
	
	@Column(name="class_semester")
	@Enumerated(EnumType.STRING)
	private SemesterEnum semester;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="college_id")
	private College college;
	
	@OneToMany(mappedBy = "collegeClass", fetch = FetchType.LAZY)
	private List<Student> StudentList;
	
	@OneToMany(mappedBy = "classId", fetch = FetchType.LAZY)
	private List<Assessment> assessmentList;

}
