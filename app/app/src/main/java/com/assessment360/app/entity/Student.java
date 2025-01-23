package com.assessment360.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_id")
	private Long id;
	
	@Column(name="student_ra")
	private Long ra;
	
	@Column(name="student_email")
	private String email;
	
	@Column(name="student_first_name")
	private String firstName;
	
	@Column(name="student_last_name")
	private String lastName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="class_id")
	private CollegeClass collegeClass;

}
