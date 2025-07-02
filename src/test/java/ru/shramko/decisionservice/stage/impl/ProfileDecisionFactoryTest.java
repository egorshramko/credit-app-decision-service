package ru.shramko.decisionservice.stage.impl;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.shramko.decisionservice.check.Check;
import ru.shramko.decisionservice.check.impl.AgeCheck;
import ru.shramko.decisionservice.check.impl.PassportCheck;
import ru.shramko.decisionservice.model.DecisionRequestDto;
import ru.shramko.decisionservice.scoring.ScoringCalculation;
import ru.shramko.decisionservice.scoring.impl.AgeScoringCalculation;
import ru.shramko.decisionservice.scoring.impl.SexScoringCalculation;

public class ProfileDecisionFactoryTest {
	
	private DecisionRequestDto request;
	
	private ProfileDecisionFactory factory;
	
	@BeforeEach
	public void prepareCorrectRequest() {
		request = new DecisionRequestDto("profile", "MALE", "2001-07-15", "2022-01-13");
		factory = new ProfileDecisionFactory(request);
	}
	
	@Test
	public void createChecks_whenCalled_thenReturnNotNullCollection() {
		
		Collection<Check> actualResult = factory.createChecks();
		
		Assertions.assertNotNull(actualResult);
		
	}
	
	@Test
	public void createChecks_whenCalled_thenReturnTwoChecks() {
		
		Collection<Check> actualResult = factory.createChecks();
		
		Assertions.assertEquals(2, actualResult.size());
		
	}
	
	@Test
	public void createChecks_whenCalled_thenReturnOneAgeCheckInCollection() {
		
		Collection<Check> actualResult = factory.createChecks();
		
		int ageChecksCount = 0;
		for (Check check : actualResult) {
			if (check instanceof AgeCheck) {
				ageChecksCount++;
			}
		}
		
		Assertions.assertEquals(1, ageChecksCount);
		
	}
	
	@Test
	public void createChecks_whenCalled_thenReturnOnePassportCheckInCollection() {
		
		Collection<Check> actualResult = factory.createChecks();
		
		int passportChecksCount = 0;
		for (Check check : actualResult) {
			if (check instanceof PassportCheck) {
				passportChecksCount++;
			}
		}
		
		Assertions.assertEquals(1, passportChecksCount);
		
	}
	
	@Test
	public void createScoringCalculations_whenCalled_thenReturnNotNullCollection() {
		
		Collection<ScoringCalculation> actualResult = factory.createScoringCalculations();
		
		Assertions.assertNotNull(actualResult);
		
	}
	
	@Test
	public void createScoringCalculations_whenCalled_thenReturnTwoScoringCalculation() {
		Collection<ScoringCalculation> actualResult = factory.createScoringCalculations();
		
		Assertions.assertEquals(2, actualResult.size());
	}
	
	@Test
	public void createScoringCalculations_whenCalled_thenReturnOneAgeScoringCalculationInCollection() {
		Collection<ScoringCalculation> actualResult = factory.createScoringCalculations();
		
		int ageScoringCalcCount = 0;
		for (ScoringCalculation calc : actualResult) {
			if (calc instanceof AgeScoringCalculation) {
				ageScoringCalcCount++;
			}
		}
		
		Assertions.assertEquals(1, ageScoringCalcCount);
	}
	
	@Test
	public void createScoringCalculations_whenCalled_thenReturnOneSexScoringCalculationInCollection() {
		
		Collection<ScoringCalculation> actualResult = factory.createScoringCalculations();
		
		int sexScoringCalcCount = 0;
		for (ScoringCalculation calc : actualResult) {
			if (calc instanceof SexScoringCalculation) {
				sexScoringCalcCount++;
			}
		}
		
		Assertions.assertEquals(1, sexScoringCalcCount);
		
	}
	
	
}
