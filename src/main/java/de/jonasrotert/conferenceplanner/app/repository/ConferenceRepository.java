package de.jonasrotert.conferenceplanner.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.jonasrotert.conferenceplanner.app.domain.Conference;

@Repository
public interface ConferenceRepository extends CrudRepository<Conference, UUID> {

	public List<Conference> findAll();
	
}
