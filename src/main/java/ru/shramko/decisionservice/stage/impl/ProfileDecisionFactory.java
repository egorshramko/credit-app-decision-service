package ru.shramko.decisionservice.stage.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.RequiredArgsConstructor;
import ru.shramko.decisionservice.check.Check;
import ru.shramko.decisionservice.check.impl.AgeCheck;
import ru.shramko.decisionservice.check.impl.PassportCheck;
import ru.shramko.decisionservice.check.impl.data.PassportCheckData;
import ru.shramko.decisionservice.common.enums.Sex;
import ru.shramko.decisionservice.model.DecisionRequestDto;
import ru.shramko.decisionservice.scoring.ScoringCalculation;
import ru.shramko.decisionservice.scoring.impl.AgeScoringCalculation;
import ru.shramko.decisionservice.scoring.impl.SexScoringCalculation;
import ru.shramko.decisionservice.stage.StageDecisionFactory;

@RequiredArgsConstructor
public class ProfileDecisionFactory implements StageDecisionFactory {

	private final DecisionRequestDto request;
	
	@Override
	public Collection<Check> createChecks() {
		
		List<Check> profileChecks = new ArrayList<>();
		
		PassportCheckData passportCheckData = new PassportCheckData(
				LocalDate.parse(request.getBirthDate()), 
				LocalDate.parse(request.getPassportIssueDate()));
		
		PassportCheck passportCheck = new PassportCheck(passportCheckData);
		AgeCheck ageCheck = new AgeCheck(
				LocalDate.parse(request.getBirthDate()), 
				Sex.valueOf(request.getSex()));
		
		profileChecks.add(passportCheck);
		profileChecks.add(ageCheck);
		
		return profileChecks;
	}

	@Override
	public Collection<ScoringCalculation> createScoringCalculations() {
		
		List<ScoringCalculation> profileCalcs = new ArrayList<>();
		
		SexScoringCalculation sexScoringCalculation = 
				new SexScoringCalculation(Sex.valueOf(request.getSex()));
		
		AgeScoringCalculation ageScoringCalculation = 
				new AgeScoringCalculation(
						LocalDate.parse(request.getBirthDate()));
		
		profileCalcs.add(sexScoringCalculation);
		profileCalcs.add(ageScoringCalculation);
		
		return profileCalcs;
	}

}
