package ru.shramko.decisionservice.check.impl;

import java.time.LocalDate;
import java.time.Period;

import lombok.RequiredArgsConstructor;
import ru.shramko.decisionservice.check.Check;
import ru.shramko.decisionservice.check.CheckResult;
import ru.shramko.decisionservice.check.enums.CheckResultType;
import ru.shramko.decisionservice.check.impl.data.PassportCheckData;

@RequiredArgsConstructor
public class PassportCheck implements Check {

	private final PassportCheckData passportCheckData;
	
	@Override
	public CheckResult check() {
		
		CheckResult result = new CheckResult();
		result.setName("Passport Check");
		
		LocalDate today = LocalDate.now();
		LocalDate passportExpirationDate = this.getPassportExpirationDate();
		
		if (today.isBefore(passportExpirationDate)) {
			result.setResult(CheckResultType.APPROVED);
		}
		else {
			result.setResult(CheckResultType.REJECTED);
			result.setRejectReason("Passport is expired");
		}
		
		return result;
	}
	
	private int calculateAge() {
		LocalDate today = LocalDate.now();
		return Period.between(passportCheckData.getBirthDate(), today).getYears();
	}
	
	private LocalDate getPassportExpirationDate() {
		int age = this.calculateAge();
		
		if (age >= 14 && age < 20) {
			return passportCheckData.getBirthDate().plusYears(20);
		}
		else if (age >= 20 && age < 45) {
			return passportCheckData.getBirthDate().plusYears(45);
		}
		else if (age >= 45) {
			return passportCheckData.getBirthDate().plusYears(2000);
		}
		else {
			return passportCheckData.getBirthDate();
		}
	}

}
