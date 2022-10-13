package de.jonasrotert.conferenceplanner.app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import de.jonasrotert.conferenceplanner.app.controller.conference.dto.ConferenceDTO;
import de.jonasrotert.conferenceplanner.app.domain.Conference;

@Configuration
public class MappingConfiguration {

	@Bean
	public ModelMapper modelmapper() {
		final var m = new ModelMapper();

		final var typeMap1 = m.createTypeMap(Conference.class, ConferenceDTO.class);
		typeMap1.addMappings(mapper -> mapper.map(src -> src.getCapacityConfiguration().getMaximumCapacity(), ConferenceDTO::setMaximumCapacity));
		typeMap1.addMappings(mapper -> mapper.map(src -> src.getDeadlineConfiguration().getDeadline(), ConferenceDTO::setDeadline));

		return m;
	}

	@Bean
	public ISpringTemplateEngine templateEngine(final ITemplateResolver templateResolver) {
		final var engine = new SpringTemplateEngine();
		engine.addDialect(new Java8TimeDialect());
		engine.setTemplateResolver(templateResolver);
		return engine;
	}
}
