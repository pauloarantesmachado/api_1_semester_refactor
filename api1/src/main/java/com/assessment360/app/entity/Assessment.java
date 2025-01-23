package com.assessment360.app.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="assessment")
public class Assessment {
	
	@Id
	@Column(name="assessment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="assessment_name")
	private String name;
	
	@Column(name="assessment_start_date")
	private LocalDateTime startDate;
	
	@Column(name="assessment_end_date")
	private LocalDateTime endDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="class_id")
	private CollegeClass classId;
	
	@OneToMany(mappedBy = "assessment", fetch = FetchType.LAZY)
	private List<StudentAssessment> studentAssessmentList;
	
	@OneToMany(mappedBy = "assessment", fetch = FetchType.LAZY)
	private List<AssessmentQuestion> listAssessmentQuestion;
}
