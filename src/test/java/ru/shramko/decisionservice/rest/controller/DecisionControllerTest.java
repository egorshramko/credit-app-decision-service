package ru.shramko.decisionservice.rest.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import ru.shramko.decisionservice.check.CheckResult;
import ru.shramko.decisionservice.check.enums.CheckResultType;
import ru.shramko.decisionservice.model.Decision;
import ru.shramko.decisionservice.scoring.ScoringCalculationResult;
import ru.shramko.decisionservice.service.DecisionService;

@WebMvcTest(DecisionController.class)
public class DecisionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private DecisionService decisionService;
	
	@InjectMocks
	private DecisionController controller;
	
	@Test
	public void getDecision_whenCorrectRequest_thenReturnDecisionJson() 
			throws Exception {
		
		Mockito.when(decisionService.getDecision(Mockito.any()))
			.thenReturn(getCorrectDecision());
		
		mockMvc.perform(post("/api/decision")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"stage\": \"profile\", "
						+ "\"sex\": \"MALE\", "
						+ "\"birthDate\": \"2000-01-01\", "
						+ "\"passportIssueDate\": \"2020-05-15\"}"))
				.andExpect(status().isOk());
				
			
	}
	
	@Test
	public void getDecision_whenIncorrectRequest_thenReturnBadRequest() 
			throws Exception {
		
		Mockito.when(decisionService.getDecision(Mockito.any()))
				.thenThrow(IllegalArgumentException.class);
		
		mockMvc.perform(post("/api/decision"))
				.andExpect(status().isBadRequest());
	}
	
	private Decision getCorrectDecision() {
		Decision decision = new Decision();
		
		decision.setDecision(CheckResultType.APPROVED);
		
		CheckResult ageCheckResult = new CheckResult();
		ageCheckResult.setName("Age Check");    
		ageCheckResult.setResult(CheckResultType.APPROVED);
		
		CheckResult passportCheckResult = new CheckResult();
		passportCheckResult.setName("Passport Check");
		passportCheckResult.setResult(CheckResultType.APPROVED);
		
		decision.addCheckResult(ageCheckResult);
		decision.addCheckResult(passportCheckResult);
		
		ScoringCalculationResult sexScoringCalc = new ScoringCalculationResult();
		sexScoringCalc.setName("sex");
		sexScoringCalc.setValue("MALE");
		sexScoringCalc.setScore(Double.valueOf(3.0));
		
		ScoringCalculationResult ageScoringCalc = new ScoringCalculationResult();
		ageScoringCalc.setName("age");
		ageScoringCalc.setValue("23");
		ageScoringCalc.setScore(Double.valueOf(1.0));
		
		decision.addScoringCalculationResult(sexScoringCalc);
		decision.addScoringCalculationResult(ageScoringCalc);
		
		return decision;
	}
	
}
