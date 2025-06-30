package ru.shramko.decisionservice.check.impl;

import java.time.LocalDate;
import java.time.Period;

import lombok.RequiredArgsConstructor;
import ru.shramko.decisionservice.check.Check;
import ru.shramko.decisionservice.check.CheckResult;
import ru.shramko.decisionservice.check.enums.CheckResultType;
import ru.shramko.decisionservice.common.enums.Sex;

@RequiredArgsConstructor
public class AgeCheck implements Check {

	private final LocalDate birthDate;
	private final Sex sex;
	
	@Override
	public CheckResult check() {
		
		CheckResult result = new CheckResult();
		result.setName("Age Check");
		
		int age = this.calculateAge();
		if (age < 18) {
			result.setResult(CheckResultType.REJECTED);
			result.setRejectReason("The client is under 18 years old");
		}
		else {
			switch (sex) {
			case MALE:
				
				if (age >= 65) {
					result.setResult(CheckResultType.REJECTED);
					result.setRejectReason("Retirement age client");
				}
				else {
					result.setResult(CheckResultType.APPROVED);
				}
				
				break;
			case FEMALE:
				
				if (age >= 60) {
					result.setResult(CheckResultType.REJECTED);
					result.setRejectReason("Retirement age client");
				}
				else {
					result.setResult(CheckResultType.APPROVED);
				}
				
				break;
			}
		}
		
		return result;
	}
	
	private int calculateAge() {
		LocalDate today = LocalDate.now();
		return Period.between(birthDate, today).getYears();
	}

}
