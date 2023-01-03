package com.dzm.evsms.station.converter;

import org.modelmapper.AbstractConverter;

import com.dzm.evsms.station.Station;
import com.dzm.evsms.station.model.StationWithDistanceModel;
import com.dzm.evsms.util.GISUtils;

public class StationToStationWithDistanceModelConverter extends AbstractConverter<Station, StationWithDistanceModel> {

	@Override
	protected StationWithDistanceModel convert(Station source) {
		return StationWithDistanceModel.builder()
				.id(source.getId())
				.companyId(source.getCompany().getId())
				.distance(GISUtils.convertDegreeToKM(source.getDistance()))
				.name(source.getName())
				.latitude(source.getLocation().getCoordinate().getX())
				.longitude(source.getLocation().getCoordinate().getY())
				.build();
	}

}
