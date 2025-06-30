package ru.shramko.decisionservice.scoring;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoringCalculationResult {
	private String name;
	private String value;
	private Double score;
}
