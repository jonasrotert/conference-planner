package de.jonasrotert.conferenceplanner.app.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.jonasrotert.conferenceplanner.app.domain.Conference;
import de.jonasrotert.conferenceplanner.app.repository.ConferenceRepository;

@Controller
public class IndexController {

	@Autowired
	private ConferenceRepository conferenceRepository;

	@GetMapping(path = { "/", "/index" })
	public String index(Model model) {

		final var conferences = this.conferenceRepository.findAll();
		//conferences.add(Conference.builder().title("Konferenz Katowice 2022").description("Dies ist eine kleine Beschreibung der Konferenz...").id(UUID.randomUUID()).build());
		model.addAttribute("conferences", conferences);
		return "index";
	}

}
