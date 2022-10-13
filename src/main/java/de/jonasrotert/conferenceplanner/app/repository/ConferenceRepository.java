package de.jonasrotert.conferenceplanner.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.jonasrotert.conferenceplanner.app.domain.Conference;
import de.jonasrotert.conferenceplanner.app.domain.ConferenceState;

@Repository
public interface ConferenceRepository extends CrudRepository<Conference, UUID> {

	@Override
	public List<Conference> findAll();

	public List<Conference> findByState(ConferenceState state);

}
