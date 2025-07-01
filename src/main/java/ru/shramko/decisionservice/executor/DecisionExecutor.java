package ru.shramko.decisionservice.executor;

import ru.shramko.decisionservice.model.Decision;
import ru.shramko.decisionservice.stage.StageDecisionFactory;

public interface DecisionExecutor {
	Decision getDecision(StageDecisionFactory factory);
}
