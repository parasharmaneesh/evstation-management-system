package com.dzm.evsms.station.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationWithDistanceModel {

	private Long id;
	private String name;
	private Long companyId;
	private double distance;
	private double latitude;
	private double longitude;
}
