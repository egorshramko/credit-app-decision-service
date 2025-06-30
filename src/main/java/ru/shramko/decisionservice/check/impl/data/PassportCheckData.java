package ru.shramko.decisionservice.check.impl.data;

import java.time.LocalDate;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PassportCheckData {
	private final LocalDate birthDate;
	private final LocalDate passportIssueDate;
}
