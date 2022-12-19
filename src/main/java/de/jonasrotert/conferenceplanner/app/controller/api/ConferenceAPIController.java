package de.jonasrotert.conferenceplanner.app.controller.api;

import de.jonasrotert.conferenceplanner.app.controller.conference.dto.ConferenceDTO;
import de.jonasrotert.conferenceplanner.app.controller.landing.dto.BookingDTO;
import de.jonasrotert.conferenceplanner.app.service.ConferenceService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
@Validated
@Slf4j
public class ConferenceAPIController {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/conferences")
    public List<ConferenceDTO> getAll() {
        return this.conferenceService.findAllPublished().stream().map(c -> this.mapper.map(c, ConferenceDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/conferences/{uuidAsString}")
    public ConferenceDTO getById(@PathVariable final String uuidAsString) {
        final var conference = this.conferenceService.loadConferenceById(uuidAsString);

        final var c = this.mapper.map(conference, ConferenceDTO.class);

        conference.getBookings().stream().forEach(b -> {
            b.getPersons().stream().forEach(p -> c.getBookings().add(BookingDTO.builder().firstName(p.getFirstName()).lastName(p.getLastName()).build()));
        });

        return c;
    }
}
