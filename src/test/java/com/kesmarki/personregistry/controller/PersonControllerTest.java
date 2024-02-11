package com.kesmarki.personregistry.controller;

import com.kesmarki.personregistry.dto.PersonDto;
import com.kesmarki.personregistry.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {


	@Autowired
	private JacksonTester<PersonDto> json;

	@MockBean
	private PersonService personService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void savePerson_ValidData_Success() throws Exception {
		final var inputDto = new PersonDto(UUID.randomUUID(), "Cserepes Virág", LocalDate.of(1991, 10, 2), "Hungary", null, null);
		final var outputDto = new PersonDto(UUID.randomUUID(), "Cserepes Virág", LocalDate.of(1991, 10, 2), "Hungary", null, null);
		given(this.personService.save(ArgumentMatchers.any(PersonDto.class))).willReturn(outputDto);
		this.mockMvc.perform(post("/persons/")
						.contentType(APPLICATION_JSON)
						.content(this.json.write(inputDto).getJson()))
				.andExpect(status().isCreated())
				.andExpect(content().json(this.json.write(outputDto).getJson()));
	}

}
