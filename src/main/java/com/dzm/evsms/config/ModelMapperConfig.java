package com.dzm.evsms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dzm.evsms.station.converter.CreateStationRequestToStationConverter;
import com.dzm.evsms.station.converter.StationToStationWithDistanceModelConverter;
import com.dzm.evsms.station.converter.UpdateStationRequestToStationConverter;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.addConverter(new CreateStationRequestToStationConverter());
		mapper.addConverter(new UpdateStationRequestToStationConverter());
		mapper.addConverter(new StationToStationWithDistanceModelConverter());
		return mapper;
	}

}
