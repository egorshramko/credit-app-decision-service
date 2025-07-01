package ru.shramko.decisionservice.scoring.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.shramko.decisionservice.common.enums.Sex;
import ru.shramko.decisionservice.scoring.ScoringCalculationResult;

public class SexScoringCalculationTest {
	
	@Test
	public void calculate_whenClientIsMale_thenReturnsScoreForMale() {
		
		SexScoringCalculation sexScoringCalculation = new SexScoringCalculation(Sex.MALE);
		
		ScoringCalculationResult scoringCalc = sexScoringCalculation.calculate();
		
		Assertions.assertNotNull(scoringCalc);
		Assertions.assertEquals("MALE", scoringCalc.getValue());
		
	}
	
	@Test
	public void calculate_whenClientIsFemale_thenReturnsScoreForFemale() {
		
		SexScoringCalculation sexScoringCalculation = new SexScoringCalculation(Sex.FEMALE);
		
		ScoringCalculationResult scoringCalc = sexScoringCalculation.calculate();
		
		Assertions.assertNotNull(scoringCalc);
		Assertions.assertEquals("FEMALE", scoringCalc.getValue());
		
	}
	
}
