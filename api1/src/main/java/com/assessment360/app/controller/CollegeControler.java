package com.assessment360.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment360.app.dto.CreateNewCollegeDto;
import com.assessment360.app.entity.College;
import com.assessment360.app.exception.collegeexception.CollegeCreateException;
import com.assessment360.app.exception.collegeexception.CollegeIdInvalidException;
import com.assessment360.app.exception.collegeexception.CollegeNameAlreadyExistException;
import com.assessment360.app.jsonview.PublicView;
import com.assessment360.app.service.CollegeService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/college")
@CrossOrigin
public class CollegeControler {
	
	@Autowired
	private CollegeService collegeService;
	
	@GetMapping("/all")
	@JsonView(PublicView.CollegePublic.class)
	public ResponseEntity<ObjectNode> getAllColleges(){
		List<College> collegeList = collegeService.getAllCollege();
		ObjectMapper objectMapper = new ObjectMapper();
	    ObjectNode responseNode = objectMapper.createObjectNode();
	    ArrayNode collegeArrayNode = objectMapper.createArrayNode();
	    collegeList.stream()
        .map(college -> {
            ObjectNode collegeNode = objectMapper.createObjectNode();
            collegeNode.put("id", college.getId());
            collegeNode.put("collegeName", college.getCollegeName());
            return collegeNode;
        })
        .forEach(collegeArrayNode::add);
	    responseNode.set("collegeList", collegeArrayNode);
	    return ResponseEntity.status(200).body(responseNode);
	}
	
	@GetMapping("/{pCollegeId}")
	@JsonView(PublicView.CollegePublic.class)
	public ResponseEntity<?> findCollegeByID(@PathVariable Long pCollegeId){
		try {
			College college = collegeService.getCollegeById(pCollegeId);
			return ResponseEntity.status(200).body(college);
		}catch (CollegeIdInvalidException e) {
			return ResponseEntity.status(400).body("Error: " + e.getMessage());
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createNewCollege(@RequestBody CreateNewCollegeDto pNewCollege){
		try {
			this.collegeService.createCollege(pNewCollege);
			return ResponseEntity.status(201).body("College created");
		}catch(CollegeNameAlreadyExistException  | CollegeCreateException e) {
			return ResponseEntity.status(400).body("Error: " + e.getMessage());
		}
	}
	
	@PutMapping("/update")
	@JsonView(PublicView.CollegePublic.class)
	public ResponseEntity<?> updateCollege(@RequestBody College pCollege){
		try {
			College updatedCollege = this.collegeService.updateCollege(pCollege);
			return ResponseEntity.status(200).body(updatedCollege);
		}catch(CollegeNameAlreadyExistException | CollegeIdInvalidException e) {
			return ResponseEntity.status(400).body("Error: " + e.getMessage());
		}
	}
	
	@DeleteMapping("/disabled/{pIdCollege}")
	@JsonView(PublicView.CollegePublic.class)
	public ResponseEntity<?> disabledCollege(@PathVariable Long pIdCollege){
		try {
			College collegeDisabled = this.collegeService.disableCollege(pIdCollege);
			return ResponseEntity.status(200).body(collegeDisabled);
		}catch(CollegeIdInvalidException e) {
			return ResponseEntity.status(400).body("Error: " + e.getMessage());
		}	
	}

}
