package com.assessment360.app.entity;

import java.util.List;

import com.assessment360.app.jsonview.PublicView;
import com.fasterxml.jackson.annotation.JsonView;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="college")
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="college_id")
	@JsonView(PublicView.CollegePublic.class)
	private Long id;
	
	@Column(name="college_name")
	@JsonView(PublicView.CollegePublic.class)
	private String collegeName;
	
	@Column(name="college_status")
	@JsonView(PublicView.CollegePublic.class)
	private Integer status;
	
	@OneToMany(mappedBy = "college", fetch = FetchType.LAZY)
	private List<CollegeClass> listClass;
	
	public College(String pCollegeName) {
		this.collegeName = pCollegeName;
		this.status = 1;
	}

	public void updateCollege(College pCollege) {
		if(pCollege.collegeName != null &&
		   !pCollege.collegeName.isBlank()) {
				this.collegeName = pCollege.getCollegeName();
		}
	}
}
