package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void toolsEndpointShouldReturnListOfTools() throws Exception {
        mockMvc.perform(get("/tools"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"bash - Run commands in a bash shell\", \"str_replace_editor - Custom editing tool for viewing, creating and editing files\", \"report_progress - Report progress on the task\", \"think - Use the tool to think about something\", \"get_code_scanning_alert - Get details of a specific code scanning alert\", \"get_commit - Get details for a commit from a GitHub repository\", \"get_file_contents - Get the contents of a file or directory from a GitHub repository\", \"get_issue - Get details of a specific issue in a GitHub repository\", \"get_issue_comments - Get comments for a GitHub issue\", \"get_me - Get details of the authenticated GitHub user\", \"get_pull_request - Get details of a specific pull request\", \"get_pull_request_comments - Get the review comments on a pull request\", \"get_pull_request_files - Get the list of files changed in a pull request\", \"get_pull_request_reviews - Get the reviews on a pull request\", \"get_pull_request_status - Get the combined status of all status checks for a pull request\", \"get_secret_scanning_alert - Get details of a specific secret scanning alert\", \"list_branches - List branches in a GitHub repository\", \"list_code_scanning_alerts - List code scanning alerts in a GitHub repository\", \"list_commits - Get list of commits of a branch in a GitHub repository\", \"list_issues - List issues in a GitHub repository with filtering options\", \"list_pull_requests - List and filter repository pull requests\", \"list_secret_scanning_alerts - List secret scanning alerts in a GitHub repository\", \"search_code - Search for code across GitHub repositories\", \"search_issues - Search for issues and pull requests across GitHub repositories\", \"search_repositories - Search for GitHub repositories\", \"search_users - Search for GitHub users\"]"));
    }

    @Test
    void toolsEndpointShouldReturnExpectedNumberOfTools() throws Exception {
        mockMvc.perform(get("/tools"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("bash")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("str_replace_editor")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("search_users")));
    }
}