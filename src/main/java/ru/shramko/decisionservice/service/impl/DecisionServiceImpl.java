package ru.shramko.decisionservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.shramko.decisionservice.executor.DecisionExecutor;
import ru.shramko.decisionservice.model.Decision;
import ru.shramko.decisionservice.model.DecisionRequestDto;
import ru.shramko.decisionservice.service.DecisionService;
import ru.shramko.decisionservice.stage.impl.ProfileDecisionFactory;

@Service
public class DecisionServiceImpl implements DecisionService {

	@Autowired
	private DecisionExecutor decisionExecutor;
	
	@Override
	public Decision getDecision(DecisionRequestDto request) throws IllegalArgumentException {
		
		if (request.getStage().equals("profile")) {
			return decisionExecutor.getDecision(new ProfileDecisionFactory(request));
		}
		else {
			throw new IllegalArgumentException("Incorrect stage");
		}
		
	}

}
