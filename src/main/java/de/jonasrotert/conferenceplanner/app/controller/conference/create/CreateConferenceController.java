package de.jonasrotert.conferenceplanner.app.controller.conference.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import de.jonasrotert.conferenceplanner.app.controller.conference.create.dto.CreateConferenceStep1DTO;
import de.jonasrotert.conferenceplanner.app.controller.conference.create.dto.CreateConferenceStep2DTO;
import de.jonasrotert.conferenceplanner.app.controller.conference.create.dto.CreateConferenceStep3DTO;
import de.jonasrotert.conferenceplanner.app.service.ConferenceService;

@Controller
@RequestMapping("conference/create")
@Validated
public class CreateConferenceController {

	@Autowired
	private ConferenceService conferenceService;

	@GetMapping("/step1")
	public ModelAndView getStep1() {
		return new ModelAndView("conference/create/step1", "dtoStep1", new CreateConferenceStep1DTO());
	}

	@GetMapping("/step2")
	public ModelAndView getStep2(@RequestParam(name = "id", required = true) final String idAsString) {
		final var c = this.conferenceService.loadConferenceById(idAsString);

		if (c != null) {
			final var dto = CreateConferenceStep2DTO.builder().id(idAsString).build();
			return new ModelAndView("/conference/create/step2", "dtoStep2", dto);
		}

		return new ModelAndView("/index");
	}

	@GetMapping("/step3")
	public ModelAndView getStep3(@RequestParam(name = "id", required = true) final String idAsString) {
		final var c = this.conferenceService.loadConferenceById(idAsString);

		if (c != null) {
			final var dto = CreateConferenceStep3DTO.builder().id(idAsString).build();
			return new ModelAndView("/conference/create/step3", "dtoStep3", dto);
		}

		return new ModelAndView("/index");
	}

	@PostMapping("/step1")
	public RedirectView postStep1(final Model model, @ModelAttribute final CreateConferenceStep1DTO conferenceStep1RequestDTO, final RedirectAttributes redirectAttributes) {
		final var c = this.conferenceService.saveStep1(conferenceStep1RequestDTO);
		redirectAttributes.addAttribute("id", c.getId().toString());
		return new RedirectView("/conference/create/step2");
	}

	@PostMapping("/step2")
	public RedirectView postStep2(final Model model, @ModelAttribute final CreateConferenceStep2DTO inputDTO, final RedirectAttributes redirectAttributes) {

		final var c = this.conferenceService.saveStep2(inputDTO);

		if (c != null) {
			redirectAttributes.addAttribute("id", c.getId().toString());
			return new RedirectView("/conference/create/step3");
		}

		return new RedirectView("/");
	}

	@PostMapping("/step3")
	public RedirectView postStep3(final Model model, @ModelAttribute final CreateConferenceStep3DTO inputDTO, final RedirectAttributes redirectAttributes) {

		final var c = this.conferenceService.saveStep3(inputDTO);

		if (c != null) {
			redirectAttributes.addAttribute("id", c.getId().toString());
			redirectAttributes.addFlashAttribute("message", "Conference successfully created");
			return new RedirectView("/");
		}

		return new RedirectView("/");
	}

}
