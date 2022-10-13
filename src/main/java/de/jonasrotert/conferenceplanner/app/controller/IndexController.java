package de.jonasrotert.conferenceplanner.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.jonasrotert.conferenceplanner.app.service.ConferenceService;

@Controller
public class IndexController {

	@Autowired
	private ConferenceService conferenceService;

	@GetMapping(path = { "/", "/index" })
	public String index(final Model model) {

		final var conferences = this.conferenceService.findAllPublished();
		model.addAttribute("conferences", conferences);
		return "index";
	}

}
