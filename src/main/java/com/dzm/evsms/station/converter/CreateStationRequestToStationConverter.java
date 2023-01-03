package com.dzm.evsms.station.converter;

import org.modelmapper.AbstractConverter;

import com.dzm.evsms.company.Company;
import com.dzm.evsms.station.Station;
import com.dzm.evsms.station.model.CreateStationRequest;
import com.dzm.evsms.util.GISUtils;

public class CreateStationRequestToStationConverter extends AbstractConverter<CreateStationRequest, Station> {

	@Override
	protected Station convert(CreateStationRequest source) {
		return Station.builder()
				.name(source.getName())
				.company(Company.builder().id(source.getCompanyId()).build())
				.location(GISUtils.createPoint(source.getLatitude(), source.getLongitude()))
				.build();
	}

}
