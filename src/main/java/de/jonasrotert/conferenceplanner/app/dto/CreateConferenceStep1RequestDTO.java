package de.jonasrotert.conferenceplanner.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class CreateConferenceStep1RequestDTO {

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Future
	private LocalDate startDate;

	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Future
	private LocalDate endDate;

}
