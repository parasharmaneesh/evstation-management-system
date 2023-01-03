package com.dzm.evsms.station;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dzm.evsms.station.model.CreateStationRequest;
import com.dzm.evsms.station.model.CreateStationResponse;
import com.dzm.evsms.station.model.SearchStationsRequest;
import com.dzm.evsms.station.model.SearchStationsResponse;
import com.dzm.evsms.station.model.StationWithDistanceModel;
import com.dzm.evsms.station.model.UpdateStationRequest;
import com.dzm.evsms.util.GISUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/station")
@RequiredArgsConstructor
public class StationController {

	private final StationService stationService;
	private final ModelMapper modelMapper;

	@PostMapping
	public CreateStationResponse createStation(@Valid @RequestBody CreateStationRequest createStationRequest) {
		Station stationRequest = modelMapper.map(createStationRequest, Station.class);
		Station station = stationService.createStation(stationRequest);
		log.info("Station created: {}", station.getId());
		return modelMapper.map(station, CreateStationResponse.class);
	}

	@PutMapping("{id}")
	public ResponseEntity<Object> updateStation(@PathVariable long id,
			@Valid @RequestBody UpdateStationRequest updateStationRequest) {
		Station stationRequest = modelMapper.map(updateStationRequest, Station.class);
		stationRequest.setId(id);
		stationService.updateStation(stationRequest);
		log.info("Station updated: {}", id);
		return ResponseEntity.noContent().build();
	}


	@GetMapping("/search")
	public SearchStationsResponse searchStations(@Valid @RequestBody SearchStationsRequest searchStationsRequest) {
		List<Station> stations = stationService.searchStations(searchStationsRequest.getCompanyId(),
				searchStationsRequest.getDistance(),
				GISUtils.createPoint(searchStationsRequest.getLatitude(), searchStationsRequest.getLongitude()));
		log.info("{} stations found", stations.size());
		List<StationWithDistanceModel> stationsWithDistance =
				modelMapper.map(stations, new TypeToken<List<StationWithDistanceModel>>() {}.getType());
		return SearchStationsResponse.builder().stations(stationsWithDistance).build();
	}

}
