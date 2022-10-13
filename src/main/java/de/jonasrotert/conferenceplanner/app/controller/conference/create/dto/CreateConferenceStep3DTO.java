package de.jonasrotert.conferenceplanner.app.controller.conference.create.dto;

import javax.validation.constraints.NotNull;

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
public class CreateConferenceStep3DTO {

	@NotNull
	private String id;

	private Boolean queueIfMaximimumCapacityExceeds;

	private Long maximumCapacity;
}
