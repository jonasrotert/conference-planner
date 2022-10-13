package de.jonasrotert.conferenceplanner.app.domain;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Booking {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "uuid")
	private UUID id;

	@NotNull
	private LocalDateTime bookingDate;

	@Enumerated(EnumType.STRING)
	@NotNull
	private BookingState state;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "booking")
	@NotEmpty
	private final List<Person> persons = new ArrayList<>();

	@ManyToOne
	@NotNull
	private Conference conference;
}
