package ru.shramko.decisionservice.stage;

import ru.shramko.decisionservice.check.Check;
import ru.shramko.decisionservice.scoring.ScoringCalculation;

public interface StageDecisionFactory {
	Iterable<Check> createChecks();
	Iterable<ScoringCalculation> createScoringCalculations();
}
