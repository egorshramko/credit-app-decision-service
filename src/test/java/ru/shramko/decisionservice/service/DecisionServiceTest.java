package ru.shramko.decisionservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.shramko.decisionservice.model.Decision;
import ru.shramko.decisionservice.model.DecisionRequestDto;

@SpringBootTest
public class DecisionServiceTest {
	
	@Autowired
	private DecisionService decisionService;
	
	@Test
	public void getDecision_whenCorrectStage_thenReturnDecision() {
		
		DecisionRequestDto request = new DecisionRequestDto(
				"profile",
				"MALE",
				"2001-04-03",
				"2024-12-06");
		
		Decision actualResult = decisionService.getDecision(request);
		
		Assertions.assertNotNull(actualResult);
	}
	
	@Test
	public void getDecision_whenIncorrectStage_thenThrowException() {
		DecisionRequestDto request = new DecisionRequestDto(
				"some data",
				"some data",
				"some data",
				"some data");
		
		Assertions.assertThrows(IllegalArgumentException.class, 
				() -> decisionService.getDecision(request));
	}
	
}
