package ru.shramko.decisionservice.executor.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.shramko.decisionservice.check.enums.CheckResultType;
import ru.shramko.decisionservice.executor.DecisionExecutor;
import ru.shramko.decisionservice.model.Decision;
import ru.shramko.decisionservice.model.DecisionRequestDto;
import ru.shramko.decisionservice.stage.impl.ProfileDecisionFactory;

@SpringBootTest
public class DecisionExecutorImplTest {
	
	@Autowired
	private DecisionExecutor decisionExecutor;
	
	private DecisionRequestDto correctRequest;
	private DecisionRequestDto rejectRequest;
	
	@BeforeEach
	public void prepareCorrectRequest() {
		correctRequest = new DecisionRequestDto(
				"profile",
				"MALE",
				"2000-05-16",
				"2021-01-09"
				);
		
		rejectRequest = new DecisionRequestDto(
				"profile",
				"MALE",
				"2000-05-16",
				"2018-04-13"
				);
	}
	
	@Test
	public void getDecision_whenExecuteProfileChecksWithCorrectData_thenReturnsDecision() {
		
		
		Decision actualResult = decisionExecutor.getDecision(
				new ProfileDecisionFactory(correctRequest));
		
		Assertions.assertNotNull(actualResult);
	}
	
	@Test
	public void getDecision_whenExecuteProfileChecksWithCorrectData_thenReturnsApprovedDecision() {
		Decision actualResult = decisionExecutor.getDecision(
				new ProfileDecisionFactory(correctRequest));
		
		Assertions.assertEquals(CheckResultType.APPROVED, actualResult.getDecision());
	}
	
	@Test
	public void getDecision_whenExecuteProfileChecksWithDataToReject_thenReturnsRejectedDecision() {
		Decision actualResult = decisionExecutor.getDecision(
				new ProfileDecisionFactory(rejectRequest));
		
		Assertions.assertEquals(CheckResultType.REJECTED, actualResult.getDecision());
	}
	
}
