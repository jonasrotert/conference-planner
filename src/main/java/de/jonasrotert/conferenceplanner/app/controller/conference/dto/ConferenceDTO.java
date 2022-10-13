package de.jonasrotert.conferenceplanner.app.controller.conference.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import de.jonasrotert.conferenceplanner.app.controller.landing.dto.BookingDTO;
import de.jonasrotert.conferenceplanner.app.domain.ConferenceState;
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
public class ConferenceDTO {

	private String title;

	private String intro;

	private String description;

	private LocalDate startDate;

	private LocalDate endDate;

	private ConferenceState state;

	private Long maximumCapacity;

	private LocalDate deadline;

	private AddressDTO address;

	private BookingConfigurationDTO bookingConfiguration;

	private PricingConfigurationDTO pricingConfiguration;

	private final List<BookingDTO> bookings = new ArrayList<>();

}
