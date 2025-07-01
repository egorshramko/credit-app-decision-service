package ru.shramko.decisionservice.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ru.shramko.decisionservice.model.Decision;
import ru.shramko.decisionservice.model.DecisionRequestDto;
import ru.shramko.decisionservice.service.DecisionService;

@Slf4j
@RestController
@RequestMapping(path = "/api/decision", produces = "application/json")
@CrossOrigin(origins="http://localhost:8080")
public class DecisionController {

	@Autowired
	private DecisionService decisionService;
	
	@PostMapping
	public ResponseEntity<Decision> getDecision(
			@RequestBody DecisionRequestDto request) {
		
		try {
			
			return new ResponseEntity<>(
					decisionService.getDecision(request), 
					HttpStatus.OK);
			
		}
		catch (IllegalArgumentException exception) {
			
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
