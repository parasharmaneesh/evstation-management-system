package com.dzm.evsms.station;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.dzm.evsms.company.CompanyService;
import com.dzm.evsms.util.GISUtils;

@ExtendWith(MockitoExtension.class)
class StationServiceTest {

	@InjectMocks
	private StationService stationService;

	@Mock
	private StationRepository stationRepository;

	@Mock
	private CompanyService companyService;

	@Test
	void shouldCreateStation() {
		Station station = Station.builder().build();
		stationService.createStation(station);

		verify(stationRepository, times(1)).save(station);
	}

	@Test
	void shouldUpdateStation() {
		double latitude = 0.0;
		double longitude = 0.0;
		Station station = Station.builder()
				.id(1l)
				.location(GISUtils.createPoint(latitude, longitude))
				.build();

		given(stationRepository.findById(station.getId())).willReturn(Optional.of(station));
		given(stationRepository.save(station)).willReturn(station);

		stationService.updateStation(station);

		verify(stationRepository, times(1)).save(station);
	}

	@Test
	void updateStationWillThrowNotFoundResponseStatusExceptionForIncorrectStationId() {
		Station station = Station.builder()
				.id(1L)
				.build();

		given(stationRepository.findById(station.getId())).willReturn(Optional.empty());

		assertThatThrownBy(() -> stationService.updateStation(station))
				.isInstanceOf(ResponseStatusException.class)
				.hasMessageContaining(HttpStatus.NOT_FOUND.name())
				.hasMessageContaining("Station id not found!");
	}

	@Test
	void shouldSearchStations() {
		long comapnyId = 1;
		long distance = 1;
		Point point = GISUtils.createPoint(0, 0);
		Geometry circle = GISUtils.createCircle(point, distance);
		Set<Long> validCompanies = new HashSet<>(2);
		validCompanies.add(1l);
		validCompanies.add(2l);

		given(companyService.getAllChildCompanies(comapnyId)).willReturn(validCompanies);
		stationService.searchStations(comapnyId, distance, point);

		verify(stationRepository, times(1)).findWithInDistanceByComapanyIds(circle, point, validCompanies);
	}

	@Test
	void searchStationsWillThrowNotFoundResponseStatusExceptionForInvalidCompanyId() {
		long comapnyId = 1;
		long distance = 1;
		Point point = GISUtils.createPoint(0, 0);
		Geometry circle = GISUtils.createCircle(point, distance);

		given(companyService.getAllChildCompanies(comapnyId)).willReturn(Collections.emptySet());


		assertThatThrownBy(() -> stationService.searchStations(comapnyId, distance, point))
				.isInstanceOf(ResponseStatusException.class)
				.hasMessageContaining(HttpStatus.NOT_FOUND.name())
				.hasMessageContaining("Invalid company id!");

		verify(stationRepository, never()).findWithInDistanceByComapanyIds(circle, point, Collections.emptySet());
	}

}
