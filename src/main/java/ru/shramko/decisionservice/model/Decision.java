package ru.shramko.decisionservice.model;

import java.util.List;

import lombok.Data;
import ru.shramko.decisionservice.check.CheckResult;
import ru.shramko.decisionservice.scoring.ScoringCalculationResult;

@Data
public class Decision {
	private List<CheckResult> checkResults;
	private ScoringResult scoringResult;
	
	public boolean addCheckResult(CheckResult checkResult) {
		return checkResults.add(checkResult);
	}
	
	public boolean addScoringCalculationResult(
			ScoringCalculationResult scoringCalculationResult) {
		
		return scoringResult
				.addScoringCalculationResult(scoringCalculationResult);
	}
	
	public boolean removeCheckResult(CheckResult checkResult) {
		return checkResults.remove(checkResult);
	}
	
	public boolean removeScoringCalculationResult(
			ScoringCalculationResult scoringCalculationResult) {
		return scoringResult.removeScoringCalculationResult(scoringCalculationResult);
	}
}
