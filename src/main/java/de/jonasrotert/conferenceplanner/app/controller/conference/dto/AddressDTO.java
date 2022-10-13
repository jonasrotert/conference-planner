package de.jonasrotert.conferenceplanner.app.controller.conference.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AddressDTO {
	private String description;

	private String street;

	private String postalCode;

	private String village;

	private String country;
}
