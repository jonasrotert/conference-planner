package de.jonasrotert.conferenceplanner.app.controller.landing;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import de.jonasrotert.conferenceplanner.app.controller.conference.dto.ConferenceDTO;
import de.jonasrotert.conferenceplanner.app.controller.landing.dto.BookingDTO;
import de.jonasrotert.conferenceplanner.app.service.BookingService;
import de.jonasrotert.conferenceplanner.app.service.ConferenceService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping({ "/i", "/invitation" })
@Validated
@Slf4j
public class LandingPageController {

	@Autowired
	private ConferenceService conferenceService;

	@Autowired
	private BookingService bookingService;


	@Autowired
	private ModelMapper mapper;

	@PostMapping("/book")
	public RedirectView book(final Model model, @ModelAttribute final BookingDTO bookingDTO, final RedirectAttributes redirectAttributes) {
		this.bookingService.book(bookingDTO);
		return new RedirectView("/");
	}

	@GetMapping("/{uuidAsString}")
	public ModelAndView getLandingpage(@PathVariable final String uuidAsString) {
		final var conference = this.conferenceService.loadConferenceById(uuidAsString);
		final var c = this.mapper.map(conference, ConferenceDTO.class);

		final var mv =  new ModelAndView("conference/landingPage");
		mv.addObject("conferenceDTO", c);
		mv.addObject("bookingDTO", BookingDTO.builder().conferenceId(uuidAsString).build());

		return mv;
	}

}
