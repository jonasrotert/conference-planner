package de.jonasrotert.conferenceplanner.app.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jonasrotert.conferenceplanner.app.controller.landing.dto.BookingDTO;
import de.jonasrotert.conferenceplanner.app.domain.Booking;
import de.jonasrotert.conferenceplanner.app.domain.BookingState;
import de.jonasrotert.conferenceplanner.app.domain.Person;
import de.jonasrotert.conferenceplanner.app.repository.ConferenceRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingService {

	@Autowired
	private ConferenceService conferenceService;

	@Autowired
	private ConferenceRepository conferenceRepository;

	public Booking book(final BookingDTO bookingDTO) {
		final var conference = this.conferenceService.loadConferenceById(bookingDTO.getConferenceId());

		if (conference != null) {
			final var booking = Booking.builder().conference(conference).state(BookingState.REQUESTED).bookingDate(LocalDateTime.now()).build();
			final var person = Person.builder().firstName(bookingDTO.getFirstName()).lastName(bookingDTO.getLastName()).email(bookingDTO.getEmail()).booking(booking).build();
			booking.getPersons().add(person);
			conference.getBookings().add(booking);

			this.conferenceRepository.save(conference);

			return booking;
		}

		return null;
	}
}
