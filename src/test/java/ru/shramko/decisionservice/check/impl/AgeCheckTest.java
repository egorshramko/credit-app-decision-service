package ru.shramko.decisionservice.check.impl;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import ru.shramko.decisionservice.check.CheckResult;
import ru.shramko.decisionservice.check.enums.CheckResultType;
import ru.shramko.decisionservice.common.enums.Sex;

public class AgeCheckTest {

	private LocalDate today;
	
	@BeforeEach
	public void setTodayDate() {
		today = LocalDate.of(2025, 7, 1);
	}
	
	@Test
	public void check_whenClientIsAdultAndNotRetirement_thenReturnsApproved() {
		
		try (MockedStatic<LocalDate> mockedStatic = Mockito.mockStatic(
				LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
			
			mockedStatic.when(() -> LocalDate.now()).thenReturn(today);
			
			AgeCheck ageCheck = new AgeCheck(LocalDate.of(2003, 6, 30), Sex.MALE);
			
			CheckResult actualResult = ageCheck.check();
			CheckResult expectedResult = getApprovedCheckResult();
			
			Assertions.assertNotNull(actualResult);
			Assertions.assertEquals(expectedResult, actualResult);
			
		}
		
	}
	
	@Test
	public void check_whenClientIsMinor_thenReturnsRejectWithMessage() {
		
		try (MockedStatic<LocalDate> mockedStatic = Mockito.mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
			
			mockedStatic.when(() -> LocalDate.now()).thenReturn(today);
			
			AgeCheck ageCheck = new AgeCheck(LocalDate.of(2020, 5, 12), Sex.FEMALE);
			
			CheckResult actualResult = ageCheck.check();
			CheckResult expectedResult = getRejectedMinorResult();
			
			Assertions.assertNotNull(actualResult);
			Assertions.assertEquals(expectedResult, actualResult);
			
		}
	}
	
	@Test
	public void check_whenClientIsRetirementMale_thenReturnsRejectWithMessage() {
		
		try (MockedStatic<LocalDate> mockedStatic = Mockito.mockStatic(
				LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
			
			mockedStatic.when(() -> LocalDate.now()).thenReturn(today);
			
			AgeCheck ageCheck = new AgeCheck(LocalDate.of(1960, 7, 1), Sex.MALE);
			
			CheckResult actualResult = ageCheck.check();
			CheckResult expectedResult = getRejectedRetirementResult();
			
			Assertions.assertNotNull(actualResult);
			Assertions.assertEquals(expectedResult, actualResult);
			
		}
		
	}
	
	@Test
	public void check_whenClientIsRetirementFemale_thenReturnsRejectWithMessage() {
		
		try (MockedStatic<LocalDate> mockedStatic = Mockito.mockStatic(
				LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
			
			mockedStatic.when(() -> LocalDate.now()).thenReturn(today);
			
			AgeCheck ageCheck = new AgeCheck(LocalDate.of(1965, 7, 1), Sex.FEMALE);
			
			CheckResult actualResult = ageCheck.check();
			CheckResult expectedResult = getRejectedRetirementResult();
			
			Assertions.assertNotNull(actualResult);
			Assertions.assertEquals(expectedResult, actualResult);
			
		}
		
	}
	
	private CheckResult getApprovedCheckResult() {
		CheckResult checkResult = new CheckResult();
		checkResult.setName("Age Check");
		checkResult.setResult(CheckResultType.APPROVED);
		
		return checkResult;
	}
	
	private CheckResult getRejectedMinorResult() {
		CheckResult checkResult = new CheckResult();
		checkResult.setName("Age Check");
		checkResult.setResult(CheckResultType.REJECTED);
		checkResult.setRejectReason("The client is under 18 years old");
		
		return checkResult;
	}
	
	private CheckResult getRejectedRetirementResult() {
		CheckResult checkResult = new CheckResult();
		checkResult.setName("Age Check");
		checkResult.setResult(CheckResultType.REJECTED);
		checkResult.setRejectReason("Retirement age client");
		
		return checkResult;
	}
	
}
