package ru.shramko.decisionservice.check.impl;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import ru.shramko.decisionservice.check.CheckResult;
import ru.shramko.decisionservice.check.enums.CheckResultType;
import ru.shramko.decisionservice.check.impl.data.PassportCheckData;

public class PassportCheckTest {

	private LocalDate today;
	
	@BeforeEach
	public void prepareTodayDate() {
		today = LocalDate.of(2025, 7, 1);
	}
	
	@Test
	public void check_whenPassportExpired_thenReturnReject() {
		
		try (MockedStatic<LocalDate> mockedStatic = Mockito.mockStatic(
				LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
			
			mockedStatic.when(() -> LocalDate.now()).thenReturn(today);
			
			PassportCheckData passportCheckData = new PassportCheckData(
					LocalDate.of(2001, 9, 15), LocalDate.of(2016, 1, 25));
			
			PassportCheck passportCheck = new PassportCheck(passportCheckData);
			
			CheckResult actualResult = passportCheck.check();
			CheckResult expectedResult = getExpiredPassportResult();
			
			Assertions.assertNotNull(actualResult);
			Assertions.assertEquals(expectedResult, actualResult);
		}
		
	}
	
	@Test
	public void check_whenPassportCorrect_thenReturnApprove() {
		try (MockedStatic<LocalDate> mockedStatic = Mockito.mockStatic(
				LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
			
			mockedStatic.when(() -> LocalDate.now()).thenReturn(today);
			
			PassportCheckData passportCheckData = new PassportCheckData(
					LocalDate.of(2001, 9, 15), LocalDate.of(2022, 1, 25));
			
			PassportCheck passportCheck = new PassportCheck(passportCheckData);
			
			CheckResult actualResult = passportCheck.check();
			CheckResult expectedResult = getApprovedResult();
			
			Assertions.assertNotNull(actualResult);
			Assertions.assertEquals(expectedResult, actualResult);
			
		}
	}
	
	private CheckResult getExpiredPassportResult() {
		CheckResult result = new CheckResult();
		result.setName("Passport Check");
		result.setResult(CheckResultType.REJECTED);
		result.setRejectReason("Passport is expired");
		
		return result;
	}
	
	private CheckResult getApprovedResult() {
		CheckResult result = new CheckResult();
		result.setName("Passport Check");
		result.setResult(CheckResultType.APPROVED);
		
		return result;
	}
	
	
}
