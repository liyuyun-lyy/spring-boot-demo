package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
	
	@Test
	void toolsEndpointShouldReturnListOfTools() throws Exception {
		mockMvc.perform(get("/tools"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$.length()").value(26));
	}

	@Test
	void promptEndpointShouldReturnSystemPrompt() throws Exception {
		mockMvc.perform(get("/prompt"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/plain;charset=UTF-8"))
			.andExpect(content().string(org.hamcrest.Matchers.containsString("System Prompt for AI Assistant")))
			.andExpect(content().string(org.hamcrest.Matchers.containsString("Available Functions/Tools")))
			.andExpect(content().string(org.hamcrest.Matchers.containsString("Multi-Controlling Persona (MCP) Guidelines")));
	}
}
