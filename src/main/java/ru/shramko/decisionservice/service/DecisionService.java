package ru.shramko.decisionservice.service;

import ru.shramko.decisionservice.model.Decision;
import ru.shramko.decisionservice.model.DecisionRequestDto;

public interface DecisionService {
	Decision getDecision(DecisionRequestDto dto);
}
