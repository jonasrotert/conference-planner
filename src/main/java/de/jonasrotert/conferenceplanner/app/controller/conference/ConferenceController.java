package de.jonasrotert.conferenceplanner.app.controller.conference;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.jonasrotert.conferenceplanner.app.controller.conference.dto.ConferenceDTO;
import de.jonasrotert.conferenceplanner.app.controller.landing.dto.BookingDTO;
import de.jonasrotert.conferenceplanner.app.service.ConferenceService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("conference")
@Validated
@Slf4j
public class ConferenceController {

	@Autowired
	private ConferenceService conferenceService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/detail/{uuidAsString}")
	public ModelAndView getById(@PathVariable final String uuidAsString) {
		final var conference = this.conferenceService.loadConferenceById(uuidAsString);

		final var c = this.mapper.map(conference, ConferenceDTO.class);

		conference.getBookings().stream().forEach(b -> {
			b.getPersons().stream().forEach(p -> c.getBookings().add(BookingDTO.builder().firstName(p.getFirstName()).lastName(p.getLastName()).build()));
		});


		return new ModelAndView("conference/detail", "conferenceDTO", c);
	}
}
