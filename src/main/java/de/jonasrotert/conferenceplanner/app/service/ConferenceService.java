package de.jonasrotert.conferenceplanner.app.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.jonasrotert.conferenceplanner.app.controller.conference.create.dto.CreateConferenceStep1DTO;
import de.jonasrotert.conferenceplanner.app.controller.conference.create.dto.CreateConferenceStep2DTO;
import de.jonasrotert.conferenceplanner.app.controller.conference.create.dto.CreateConferenceStep3DTO;
import de.jonasrotert.conferenceplanner.app.domain.CapacityConfiguration;
import de.jonasrotert.conferenceplanner.app.domain.Conference;
import de.jonasrotert.conferenceplanner.app.domain.ConferenceState;
import de.jonasrotert.conferenceplanner.app.domain.DeadlineConfiguration;
import de.jonasrotert.conferenceplanner.app.repository.ConferenceRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConferenceService {

	@Autowired
	private ConferenceRepository conferenceRepository;

	public List<Conference> findAllPublished() {
		log.info("Find all published conferences");
		return this.conferenceRepository.findByState(ConferenceState.PUBLISHED);
	}

	public Conference loadConferenceById(final String id) {
		log.info("Load conference by UUID {}", id);
		return this.loadConferenceById(UUID.fromString(id));
	}

	public Conference loadConferenceById(final UUID id) {
		final var optConf = this.conferenceRepository.findById(id);

		if (optConf.isPresent()) {
			log.info("Found the conference with uuid " + id);
			return optConf.get();
		}

		log.info("Didn't find the conference with uuid " + id);

		return null;
	}

	public Conference saveStep1(final CreateConferenceStep1DTO inputDTO) {
		final var c = Conference.builder().title(inputDTO.getTitle()).description(inputDTO.getDescription()).startDate(inputDTO.getStartDate()).endDate(inputDTO.getEndDate()).state(ConferenceState.CREATE_STEP1).build();

		this.conferenceRepository.save(c);

		log.info("Created new conference with uuid " + c.getId());

		return c;
	}

	public Conference saveStep2(final CreateConferenceStep2DTO inputDTO) {
		final var c = this.loadConferenceById(inputDTO.getId());

		if (c != null) {
			c.setDeadlineConfiguration(DeadlineConfiguration.builder().deadline(inputDTO.getDeadlineDate()).queueIfDeadlineExceeds(true).build());
			c.setState(ConferenceState.CREATE_STEP2);
			this.conferenceRepository.save(c);
			log.info("Updated conference with uuid " + c.getId() + " with deadline configuration");
			return c;
		}

		return null;
	}

	public Conference saveStep3(final CreateConferenceStep3DTO inputDTO) {
		final var c = this.loadConferenceById(inputDTO.getId());

		if (c != null) {
			c.setCapacityConfiguration(CapacityConfiguration.builder().maximumCapacity(inputDTO.getMaximumCapacity()).queueIfMaximimumCapacityExceeds(true).build());
			c.setState(ConferenceState.PUBLISHED);
			this.conferenceRepository.save(c);
			log.info("Updated conference with uuid " + c.getId() + " with capacity configuration");
			return c;
		}

		return null;
	}

}
