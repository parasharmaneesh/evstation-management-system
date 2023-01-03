package com.dzm.evsms.station;

import java.util.List;
import java.util.Set;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends CrudRepository<Station, Long> {

	@Query("SELECT new Station(st.id, st.name, st.location, st.company.id, distance(st.location, :center))" +
			" from Station st where within(st.location, :circle)=true " +
			"and (st.company.id in (:companyIds))" +
			"order by distance(st.location, :center)")
	List<Station> findWithInDistanceByComapanyIds(@Param("circle") Geometry cirlce,
			@Param("center") Point center, @Param("companyIds") Set<Long> companyIds);
}
