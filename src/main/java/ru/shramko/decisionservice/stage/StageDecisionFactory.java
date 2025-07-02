package ru.shramko.decisionservice.stage;

import java.util.Collection;

import ru.shramko.decisionservice.check.Check;
import ru.shramko.decisionservice.scoring.ScoringCalculation;

public interface StageDecisionFactory {
	Collection<Check> createChecks();
	Collection<ScoringCalculation> createScoringCalculations();
}
