package de.jonasrotert.conferenceplanner.app.controller.conference.dto;

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
public class BookingConfigurationDTO {

	private Boolean askForBirthday;

	private Boolean askForSex;

	private Boolean askForFurtherBookings;
}
