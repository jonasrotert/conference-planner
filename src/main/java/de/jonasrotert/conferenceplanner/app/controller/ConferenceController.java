package de.jonasrotert.conferenceplanner.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.jonasrotert.conferenceplanner.app.repository.ConferenceRepository;

@Controller
@RequestMapping("conference")
public class ConferenceController {

	@Autowired
	private ConferenceRepository conferenceRepository;

	@GetMapping(path = { "/create/step1" })
	public String create(Model model) {
		return "conference/create/step1";
	}
	
}
