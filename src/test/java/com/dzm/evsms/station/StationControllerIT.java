package com.dzm.evsms.station;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.dzm.evsms.station.model.CreateStationRequest;
import com.dzm.evsms.station.model.SearchStationsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StationControllerIT {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
	}

	@Test
	void shouldCreateStation() throws Exception {
		CreateStationRequest createStationRequest = CreateStationRequest.builder()
				.companyId(1)
				.latitude(0.0)
				.longitude(0.0)
				.name("ITest")
				.build();

		mockMvc.perform(post("/api/v1/station")
				.content(objectMapper.writeValueAsString(createStationRequest))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void shouldSearchStation() throws Exception {
		SearchStationsRequest searchStationsRequest = SearchStationsRequest.builder()
				.companyId(1)
				.distance(1)
				.latitude(0.0)
				.longitude(0.0)
				.build();

		mockMvc.perform(get("/api/v1/station/search")
				.content(objectMapper.writeValueAsString(searchStationsRequest))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
