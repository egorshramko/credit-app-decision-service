package ru.shramko.decisionservice.check;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shramko.decisionservice.check.enums.CheckResultType;

@Data
@NoArgsConstructor
public class CheckResult {
	private String name;
	private CheckResultType result;
	private String rejectReason;
}
