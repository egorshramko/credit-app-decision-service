package ru.shramko.decisionservice.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import ru.shramko.decisionservice.check.CheckResult;
import ru.shramko.decisionservice.check.enums.CheckResultType;
import ru.shramko.decisionservice.scoring.ScoringCalculationResult;

@Data
public class Decision {
	private CheckResultType decision = CheckResultType.APPROVED;
	private List<CheckResult> checkResults = new ArrayList<>();
	private ScoringResult scoringResult = new ScoringResult();
	
	public boolean addCheckResult(CheckResult checkResult) {
		if (checkResult.getResult() == CheckResultType.REJECTED) {
			decision = CheckResultType.REJECTED;
		}
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
