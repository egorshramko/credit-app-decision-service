package ru.shramko.decisionservice.scoring.impl;

import java.time.LocalDate;
import java.time.Period;

import lombok.RequiredArgsConstructor;
import ru.shramko.decisionservice.scoring.ScoringCalculation;
import ru.shramko.decisionservice.scoring.ScoringCalculationResult;

@RequiredArgsConstructor
public class AgeScoringCalculation implements ScoringCalculation {

	private final LocalDate birthDate;
	
	@Override
	public ScoringCalculationResult calculate() {
		
		ScoringCalculationResult result = new ScoringCalculationResult();
		result.setName("age");
		
		int age = this.calculateAge();
		result.setValue(String.valueOf(age));
		
		if (age >= 0 && age < 20) {
			result.setScore(Double.valueOf(0.0));
		}
		else if (age >= 20 && age < 25) {
			result.setScore(Double.valueOf(1.0));
		}
		else if (age >= 25 && age < 37) {
			result.setScore(Double.valueOf(5.0));
		}
		else if (age >= 37 && age < 51) {
			result.setScore(Double.valueOf(10.0));
		}
		else if (age >= 51 && age < 61) {
			result.setScore(Double.valueOf(8.0));
		}
		else {
			result.setScore(Double.valueOf(5.0));
		}
		
		return result;
	}
	
	private int calculateAge() {
		LocalDate today = LocalDate.now();
		return Period.between(birthDate, today).getYears();
	}

}
