package ru.shramko.decisionservice.executor.impl;

import ru.shramko.decisionservice.check.Check;
import ru.shramko.decisionservice.check.CheckResult;
import ru.shramko.decisionservice.executor.DecisionExecutor;
import ru.shramko.decisionservice.model.Decision;
import ru.shramko.decisionservice.scoring.ScoringCalculation;
import ru.shramko.decisionservice.scoring.ScoringCalculationResult;
import ru.shramko.decisionservice.stage.StageDecisionFactory;

public class DecisionExecutorImpl implements DecisionExecutor {

	@Override
	public Decision getDecision(StageDecisionFactory factory) {
		
		Iterable<Check> stageChecks = factory.createChecks();
		Iterable<ScoringCalculation> stageScoringCalculations = factory.createScoringCalculations();
		
		Decision decision = new Decision();
		
		for (Check check : stageChecks) {
			CheckResult checkResult = check.check();
			decision.addCheckResult(checkResult);
		}
		
		for (ScoringCalculation scoringCalculation : stageScoringCalculations) {
			ScoringCalculationResult scoringResult = scoringCalculation.calculate();
			decision.addScoringCalculationResult(scoringResult);
		}
		
		return decision;
	}

}
