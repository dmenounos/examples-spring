package com.example.template.resource;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.template.model.Entity;
import com.example.template.model.EntityBuilder;
import com.example.template.service.EntityService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class EntityControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EntityService entityService;

	@Test
	public void test_findAll() throws Exception {

		// Prepare the mock environment
		Entity mockEntity1 = createEntity1();
		Entity mockEntity2 = createEntity2();

		List<Entity> mockEntities = Arrays.asList(
			mockEntity1, mockEntity2);

		when(entityService.findAll())
		.thenReturn(mockEntities);

		// Invoke the service
		mockMvc.perform(get("/api/entities/"))

		// Assess the outcome
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].name", is("Entity 1")))
		.andExpect(jsonPath("$[0].totalShares", is(1000)))
		.andExpect(jsonPath("$[1].name", is("Entity 2")))
		.andExpect(jsonPath("$[1].totalShares", is(3000)));
	}

	@Test
	public void test_findByName() throws Exception {

		// Prepare the mock environment
		Entity mockEntity1 = createEntity1();

		when(entityService.findByName(any()))
		.thenReturn(mockEntity1);

		// Invoke the service
		mockMvc.perform(get("/api/entities/" + mockEntity1.getName()))

		// Assess the outcome
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON))
		.andExpect(jsonPath("$.name", is("Entity 1")))
		.andExpect(jsonPath("$.totalShares", is(1000)));
	}

	@Test
	public void test_create() throws Exception {

		// Prepare the mock environment
		Entity mockEntity1 = createEntity1();

		when(entityService.create(any()))
		.thenReturn(mockEntity1);

		// Invoke the service
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(mockEntity1);

		mockMvc.perform(post("/api/entities")
			.contentType(APPLICATION_JSON)
			.content(requestBody))

		// Assess the outcome
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON));
	}

	@Test
	public void test_update() throws Exception {

		// Prepare the mock environment
		Entity mockEntity1 = createEntity1();

		when(entityService.update(any()))
		.thenReturn(mockEntity1);

		// Invoke the service
		ObjectMapper mapper = new ObjectMapper();
		String requestBody = mapper.writeValueAsString(mockEntity1);

		mockMvc.perform(put("/api/entities")
			.contentType(APPLICATION_JSON)
			.content(requestBody))

		// Assess the outcome
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON));
	}

	@Test
	public void test_delete() throws Exception {

		// Prepare the mock environment
		doNothing().when(entityService).delete(any());

		// Invoke the service
		mockMvc
		.perform(delete("/api/entities/" + "Entity 1"))

		// Assess the outcome
		.andDo(print())
		.andExpect(status().isOk());
	}

	/*
	 * Convenience factory method.
	 */
	private static Entity createEntity1() {
		return new EntityBuilder()
			.setName("Entity 1")
			.setCountry(Entity.Country.US)
			.setIncorporationDate(new Date())
			.addShareholder("Share Holder A", 1000)
			.build();
	}

	/*
	 * Convenience factory method.
	 */
	private static Entity createEntity2() {
		return new EntityBuilder()
			.setName("Entity 2")
			.setCountry(Entity.Country.UK)
			.setIncorporationDate(new Date())
			.addShareholder("Share Holder A", 1000)
			.addShareholder("Share Holder B", 2000)
			.build();
	}
}
