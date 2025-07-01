package ru.shramko.decisionservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecisionRequestDto {
	private String stage;
	private String sex;
	private String birthDate;
	private String passportIssueDate;
}
