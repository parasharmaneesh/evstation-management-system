package com.dzm.evsms.station.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateStationRequest {

	private double latitude;

	private double longitude;
}
