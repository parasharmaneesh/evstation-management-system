package com.dzm.evsms.station.model;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateStationRequest {

	@NotBlank(message = "Name is mandatory")
	private String name;

	private double latitude;

	private double longitude;

	private long companyId;

}
