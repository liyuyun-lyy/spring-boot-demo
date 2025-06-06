package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void checkPreloadShouldReturnSuccess() throws Exception {
        mockMvc.perform(get("/checkpreload.htm"))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }

    @Test
    void checkPreloadShouldReturnTextContentType() throws Exception {
        mockMvc.perform(get("/checkpreload.htm"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"));
    }

    @Test
    void listToolsShouldReturnToolsList() throws Exception {
        mockMvc.perform(get("/tools"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(26))) // Expecting 26 tools in the list
                .andExpect(jsonPath("$[0]", containsString("bash")))
                .andExpect(jsonPath("$[1]", containsString("str_replace_editor")))
                .andExpect(jsonPath("$[2]", containsString("report_progress")));
    }

    @Test
    void listToolsShouldContainSpecificTools() throws Exception {
        mockMvc.perform(get("/tools"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasItem("bash - Run commands in a bash shell")))
                .andExpect(jsonPath("$", hasItem("get_file_contents - Get the contents of a file or directory from a GitHub repository")))
                .andExpect(jsonPath("$", hasItem("search_repositories - Search for GitHub repositories")));
    }

    @Test
    void listToolsShouldReturnNonEmptyList() throws Exception {
        mockMvc.perform(get("/tools"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void endpointsShouldBeAccessibleWithoutAuthentication() throws Exception {
        // Test that both endpoints are publicly accessible
        mockMvc.perform(get("/checkpreload.htm"))
                .andExpect(status().isOk());
        
        mockMvc.perform(get("/tools"))
                .andExpect(status().isOk());
    }
}