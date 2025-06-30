package ru.shramko.decisionservice.scoring.impl;

import ru.shramko.decisionservice.common.enums.Sex;
import ru.shramko.decisionservice.scoring.ScoringCalculation;
import ru.shramko.decisionservice.scoring.ScoringCalculationResult;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SexScoringCalculation implements ScoringCalculation {

	private final Sex sex;
	
	@Override
	public ScoringCalculationResult calculate() {
		
		ScoringCalculationResult result = new ScoringCalculationResult();
		result.setName("sex");
		
		switch (sex) {
		case Sex.MALE:
			result.setValue("MALE");
			result.setScore(Double.valueOf(3.0));
			break;
		case Sex.FEMALE:
			result.setValue("FEMALE");
			result.setScore(Double.valueOf(7.0));
			break;
		}
		
		return result;
	}

}
