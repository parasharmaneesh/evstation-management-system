package com.dzm.evsms.station;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.locationtech.jts.geom.Geometry;

import com.dzm.evsms.company.Company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Station {

	public Station(Long id, String name, Geometry location, Long companyId, Double distance) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.company = new Company(companyId);
		this.distance = distance;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "station_id_seq")
	private Long id;

	@NotNull
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	private Geometry location;
	
	@Transient
	private Double distance;
}
