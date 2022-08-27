package de.jonasrotert.conferenceplanner.app.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
public class CapacityConfiguration {

	@Id
	@GeneratedValue
	private UUID id;

	@NotNull
	private Boolean queueIfMaximimumCapacityExceeds;

	@NotNull
	private Long maximumCapacity;
}
