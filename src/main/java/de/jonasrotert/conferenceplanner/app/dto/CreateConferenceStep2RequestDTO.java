package de.jonasrotert.conferenceplanner.app.dto;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateConferenceStep2RequestDTO {

	@NotNull
	private UUID id;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Future
	private LocalDate deadlineDate;
}
