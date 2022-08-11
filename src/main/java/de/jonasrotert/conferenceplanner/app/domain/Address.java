package de.jonasrotert.conferenceplanner.app.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@Entity
public class Address {

	@Id
	@GeneratedValue
	private UUID id;

	@NotBlank
	private String street;

	@NotBlank
	private String postalCode;

	@NotBlank
	private String village;

}
