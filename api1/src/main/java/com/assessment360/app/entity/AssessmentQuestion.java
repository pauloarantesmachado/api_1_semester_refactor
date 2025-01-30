package com.assessment360.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="assessment_question")
public class AssessmentQuestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="asses_question_id")
	private Long assesQuestionId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="assessment_id")
	private Assessment assessment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="question_id")
	private Question question;
	
	@Column(name="question_value")
	private Integer value;
}
