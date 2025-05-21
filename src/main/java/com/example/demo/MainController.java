package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyuyun.lyy
 * @date 2023/6/12 11:00
 */
@RestController
public class MainController {

    @GetMapping("/checkpreload.htm")
    public String checkPreload() {
        return "success";
    }
    
    @GetMapping("/blackbird-mcp-server")
    public String getBlackbirdMcpServerInfo() {
        return "Blackbird MCP Server (Management Control Protocol Server) 是一个高性能的服务管理系统，提供以下功能：\n\n"
            + "1. 集中式服务监控与管理\n"
            + "2. 自动化部署与配置\n"
            + "3. 资源使用监控与优化\n"
            + "4. 负载均衡与高可用性保障\n"
            + "5. 安全访问控制与权限管理\n\n"
            + "该系统采用分布式架构，支持多种云平台，并提供RESTful API接口供第三方系统集成。\n"
            + "基于Spring Boot框架开发，具有高扩展性和可维护性。";
    }
    
    @GetMapping("/tools")
    public List<String> listTools() {
        return Arrays.asList(
            "bash - Run commands in a bash shell",
            "str_replace_editor - Custom editing tool for viewing, creating and editing files",
            "report_progress - Report progress on the task",
            "think - Use the tool to think about something",
            "get_code_scanning_alert - Get details of a specific code scanning alert",
            "get_commit - Get details for a commit from a GitHub repository",
            "get_file_contents - Get the contents of a file or directory from a GitHub repository",
            "get_issue - Get details of a specific issue in a GitHub repository",
            "get_issue_comments - Get comments for a GitHub issue",
            "get_me - Get details of the authenticated GitHub user",
            "get_pull_request - Get details of a specific pull request",
            "get_pull_request_comments - Get the review comments on a pull request",
            "get_pull_request_files - Get the list of files changed in a pull request",
            "get_pull_request_reviews - Get the reviews on a pull request",
            "get_pull_request_status - Get the combined status of all status checks for a pull request",
            "get_secret_scanning_alert - Get details of a specific secret scanning alert",
            "list_branches - List branches in a GitHub repository",
            "list_code_scanning_alerts - List code scanning alerts in a GitHub repository",
            "list_commits - Get list of commits of a branch in a GitHub repository",
            "list_issues - List issues in a GitHub repository with filtering options",
            "list_pull_requests - List and filter repository pull requests",
            "list_secret_scanning_alerts - List secret scanning alerts in a GitHub repository",
            "search_code - Search for code across GitHub repositories",
            "search_issues - Search for issues and pull requests across GitHub repositories",
            "search_repositories - Search for GitHub repositories",
            "search_users - Search for GitHub users"
        );
    }
}
