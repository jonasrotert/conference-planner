package de.jonasrotert.conferenceplanner.app.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Conference {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "uuid")
	private UUID id;

	@NotBlank
	private String title;

	private String intro;

	@NotBlank
	private String description;

	@NotNull
	private LocalDateTime created;

	@NotBlank
	private String createdBy;

	@NotNull
	private LocalDate startDate;

	@NotNull
	private LocalDate endDate;

	@Valid
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
	private Address address;

	@Enumerated(EnumType.STRING)
	private ConferenceState state;

	@Valid
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
	private DeadlineConfiguration deadlineConfiguration;

	@Valid
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
	private CapacityConfiguration capacityConfiguration;

	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	private BookingConfiguration bookingConfiguration;

	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	private PricingConfiguration pricingConfiguration;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "conference", orphanRemoval = true)
	private final List<Booking> bookings = new ArrayList<Booking>();

	@PrePersist
	@PreUpdate
	private void prePersist() {
		this.created = LocalDateTime.now();
		this.createdBy = "jonas-dev";
	}
}
