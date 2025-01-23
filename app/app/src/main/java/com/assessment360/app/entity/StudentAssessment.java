package com.assessment360.app.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="student_assessment")
public class StudentAssessment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_assessment_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="student_assessment_evaluator_id")
	private Student evaluator;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="student_assessment_evaluatee_id")
	private Student evaluatee;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="assessment_id")
	private Assessment assessment;
	
}
