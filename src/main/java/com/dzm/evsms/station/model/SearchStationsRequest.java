package com.dzm.evsms.station.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchStationsRequest {

	private long distance;
	
	private double latitude;

	private double longitude;

	private long companyId;
}
