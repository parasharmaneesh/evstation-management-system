package com.dzm.evsms.station.converter;

import org.modelmapper.AbstractConverter;

import com.dzm.evsms.station.Station;
import com.dzm.evsms.station.model.UpdateStationRequest;
import com.dzm.evsms.util.GISUtils;

public class UpdateStationRequestToStationConverter extends AbstractConverter<UpdateStationRequest, Station> {

	@Override
	protected Station convert(UpdateStationRequest source) {
		return Station.builder()
				.location(GISUtils.createPoint(source.getLatitude(), source.getLongitude()))
				.build();
	}

}
