package de.jonasrotert.conferenceplanner.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.jonasrotert.conferenceplanner.app.domain.Conference;
import de.jonasrotert.conferenceplanner.app.domain.ConferenceState;
import de.jonasrotert.conferenceplanner.app.domain.DeadlineConfiguration;
import de.jonasrotert.conferenceplanner.app.dto.CreateConferenceStep1RequestDTO;
import de.jonasrotert.conferenceplanner.app.dto.CreateConferenceStep2RequestDTO;
import de.jonasrotert.conferenceplanner.app.repository.ConferenceRepository;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("conference")
@Validated
@Slf4j
public class ConferenceController {

	@Autowired
	private ConferenceRepository conferenceRepository;

	@GetMapping(path = { "/create/step1" })
	public String getCreateStep1(Model model) {
		log.info("get step 1");
		model.addAttribute("dtoStep1", new CreateConferenceStep1RequestDTO());
		return "conference/create/step1";
	}

	@GetMapping(path = { "/create/step2" })
	public String getCreateStep2(ModelMap model) {
		log.info("get step 2");

		final var modelDto = model.getAttribute("dtoStep2");

		if (modelDto != null) {
			log.info("model is not null");
		} else {
			log.info(modelDto.toString());
		}

		return "conference/create/step2";
	}

	@PostMapping(path = { "/create/step2" })
	public String postCreateStep2(@Valid @ModelAttribute("dtoStep1") CreateConferenceStep1RequestDTO conferenceStep1RequestDTO, BindingResult result, Model model) {

		final var c = Conference.builder().title(conferenceStep1RequestDTO.getTitle()).description(conferenceStep1RequestDTO.getDescription()).startDate(conferenceStep1RequestDTO.getStartDate()).endDate(conferenceStep1RequestDTO.getEndDate()).state(ConferenceState.CREATE_STEP1).build();

		conferenceRepository.save(c);

		log.info("Created new conference with uuid " + c.getId());

		var dtoStep2 = CreateConferenceStep2RequestDTO.builder().id(c.getId()).build();

		model.addAttribute("dtoStep2", dtoStep2);

		return "conference/create/step2";
	}

	@PostMapping(path = { "/create/step3" })
	public String postCreateStep3(@Valid @ModelAttribute("dtoStep2") CreateConferenceStep2RequestDTO inputDTO, BindingResult result, Model model) {

		conferenceRepository.findById(inputDTO.getId()).ifPresentOrElse(c -> {
			if (inputDTO.getDeadlineDate() != null) {
				log.info("Setting deadline information");
				c.setDeadlineConfiguration(DeadlineConfiguration.builder().deadline(inputDTO.getDeadlineDate()).build());
			}
		}, () -> {
			log.info("No conference found");
		});

		return "conference/create/step3";
	}

}
