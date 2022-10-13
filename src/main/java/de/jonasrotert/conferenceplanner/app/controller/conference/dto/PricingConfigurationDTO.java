package de.jonasrotert.conferenceplanner.app.controller.conference.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class PricingConfigurationDTO {

	private Double basePrice;

	private Boolean showPricing;
}
