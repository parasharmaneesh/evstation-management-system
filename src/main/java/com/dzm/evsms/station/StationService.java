package com.dzm.evsms.station;

import java.util.List;
import java.util.Set;

import org.locationtech.jts.geom.Point;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dzm.evsms.company.CompanyService;
import com.dzm.evsms.util.GISUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StationService {

	private final StationRepository stationRepository;
	private final CompanyService companyService;

	@Transactional
	public Station createStation(Station station) {
		return stationRepository.save(station);
	}

	@Transactional
	public Station updateStation(Station stationRequest) {
		return stationRepository.findById(stationRequest.getId()).map(station -> {
			station.setLocation(stationRequest.getLocation());
			return stationRepository.save(station);
		}).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Station id not found!");
		});
	}

	@Transactional(readOnly = true)
	public List<Station> searchStations(long companyId, long distance, Point center) {
		Set<Long> validCompanies = companyService.getAllChildCompanies(companyId);
		log.info("{} valid companies found.", validCompanies.size());
		if (validCompanies.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid company id!");
		} else {
			return stationRepository.findWithInDistanceByComapanyIds(GISUtils.createCircle(center, distance), center,
					validCompanies);
		}
	}

}
