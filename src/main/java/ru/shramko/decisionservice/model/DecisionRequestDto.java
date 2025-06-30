package ru.shramko.decisionservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DecisionRequestDto {
	private final String stage;
	private String sex;
	private String birthDate;
	private String passportIssueDate;
}
