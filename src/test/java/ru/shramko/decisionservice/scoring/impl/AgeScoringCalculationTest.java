package ru.shramko.decisionservice.scoring.impl;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import ru.shramko.decisionservice.scoring.ScoringCalculationResult;

public class AgeScoringCalculationTest {
	
	@Test
	public void calculate_whenAgeClientIsTwentyTwo_thenReturnsScoreOfThisValue() {
		
		LocalDate today = LocalDate.of(2025, 7, 1);
		
		try (MockedStatic<LocalDate> mockedStatic = Mockito.mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
			mockedStatic.when(() -> LocalDate.now()).thenReturn(today);
			
			AgeScoringCalculation ageScoringCalculation = new AgeScoringCalculation(LocalDate.parse("2003-06-30"));
			
			ScoringCalculationResult calcResult = ageScoringCalculation.calculate();
			
			Assertions.assertNotNull(calcResult);
			Assertions.assertEquals("22", calcResult.getValue());
		}
		
	}
	
}
