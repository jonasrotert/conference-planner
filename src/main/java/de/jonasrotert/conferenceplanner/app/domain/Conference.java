package de.jonasrotert.conferenceplanner.app.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
public class Conference {

	@Id
	@GeneratedValue
	private UUID id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String description;
	
	@NotNull
	private Date created;
	
	@NotBlank
	private String createdBy;
	
	@NotNull
	private Date startDate;
	
	@NotNull
	private Date endDate;
	
	@Enumerated(EnumType.STRING)
	private ConferenceState state;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
	private DeadlineConfiguration deadlineConfiguration;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
	private CapacityConfiguration capacityConfiguration;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "conference", orphanRemoval = true)
	private final List<Booking> bookings = new ArrayList<Booking>();
}
