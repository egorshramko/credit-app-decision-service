package ru.shramko.decisionservice.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shramko.decisionservice.scoring.ScoringCalculationResult;

@Data
@NoArgsConstructor
public class ScoringResult {
	private double value = 0;
	private List<ScoringCalculationResult> scoringCalculationResults = new ArrayList<>();
	
	public boolean addScoringCalculationResult(ScoringCalculationResult scoringResult) {
		value += scoringResult.getScore().doubleValue();
		return scoringCalculationResults.add(scoringResult);
	}
	
	public boolean removeScoringCalculationResult(ScoringCalculationResult scoringResult) {
		
		if (scoringCalculationResults.remove(scoringResult)) {
			value -= scoringResult.getScore().doubleValue();
			return true;
		}
		
		return false;

	}
}
