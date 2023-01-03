package com.dzm.evsms.company;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.dzm.evsms.station.Station;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Company {

	public Company(Long companyId) {
		this.id = companyId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
	private long id;

	@NotNull
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Company parentCompany;

	@OneToMany(mappedBy = "company")
	private Set<Station> stations;

}
