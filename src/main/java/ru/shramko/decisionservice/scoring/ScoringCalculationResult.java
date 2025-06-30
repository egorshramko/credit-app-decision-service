package ru.shramko.decisionservice.scoring;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoringCalculationResult {
	private String name;
	private String value;
	private Double score;
}
