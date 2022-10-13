package de.jonasrotert.conferenceplanner.app.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class BookingConfiguration {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "uuid")
	private UUID id;

	private Boolean askForBirthday;

	private Boolean askForSex;

	private Boolean askForFurtherBookings;

}
