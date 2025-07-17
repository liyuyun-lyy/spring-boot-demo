package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
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
    
    @GetMapping(value = "/prompt", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getSystemPrompt() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("# System Prompt for AI Assistant\n\n");
        
        // General instructions section
        sb.append("## General Instructions\n\n");
        sb.append("In this environment you have access to a set of tools you can use to answer the user's question.\n");
        sb.append("You can invoke functions by writing a \"<function_calls>\" block like the following as part of your reply to the user:\n");
        sb.append("<function_calls>\n");
        sb.append("<invoke name=\"$FUNCTION_NAME\">\n");
        sb.append("<parameter name=\"$PARAMETER_NAME\">$PARAMETER_VALUE</parameter>\n");
        sb.append("</invoke>\n");
        sb.append("</function_calls>\n\n");
        sb.append("String and scalar parameters should be specified as is, while lists and objects should use JSON format.\n\n");
        
        // Tools/Functions section
        sb.append("## Available Functions/Tools\n\n");
        
        // Bash tool
        sb.append("### bash\n");
        sb.append("- Description: Run commands in a bash shell\n");
        sb.append("- Parameters:\n");
        sb.append("  - command (string, required): The bash command to run.\n");
        sb.append("- Notes:\n");
        sb.append("  - You don't have access to the internet via this tool.\n");
        sb.append("  - This is a persistent bash session. State is saved across command calls.\n");
        sb.append("  - To run long lived commands in the background, use '&' at the end of your command.\n\n");
        
        // str_replace_editor tool
        sb.append("### str_replace_editor\n");
        sb.append("- Description: Custom editing tool for viewing, creating and editing files\n");
        sb.append("- Parameters:\n");
        sb.append("  - command (string, required): The commands to run. Allowed options are: view, create, str_replace, insert, undo_edit\n");
        sb.append("  - path (string, required): Absolute path to file or directory\n");
        sb.append("  - file_text (string): Required for 'create' command with the content of the file\n");
        sb.append("  - old_str (string): Required for 'str_replace' command containing the string to replace\n");
        sb.append("  - new_str (string): Required for 'str_replace' command containing the new string\n");
        sb.append("  - insert_line (integer): Required for 'insert' command. Line to insert after\n");
        sb.append("  - view_range (array): Optional for 'view' command. Line range to view [start, end]\n");
        sb.append("- Notes:\n");
        sb.append("  - State is persistent across command calls\n");
        sb.append("  - For str_replace, the old_str must match exactly\n\n");
        
        // report_progress tool
        sb.append("### report_progress\n");
        sb.append("- Description: Report progress on the task\n");
        sb.append("- Parameters:\n");
        sb.append("  - commitMessage (string, required): A short single line of text for the commit message\n");
        sb.append("  - prDescription (string, required): Description using markdown checklists for progress\n");
        sb.append("- Notes:\n");
        sb.append("  - Use markdown checklists (- [x] for completed, - [ ] for pending tasks)\n");
        sb.append("  - Include \"Fixes #XX\" in description to link to issues\n\n");
        
        // think tool
        sb.append("### think\n");
        sb.append("- Description: Use the tool to think about something\n");
        sb.append("- Parameters:\n");
        sb.append("  - thought (string, required): Your thoughts to log\n");
        sb.append("- Notes:\n");
        sb.append("  - Use for complex reasoning or brainstorming\n");
        sb.append("  - Does not make changes to the repository\n\n");

        // GitHub API tools
        sb.append("### GitHub API Tools\n\n");
        sb.append("This system includes 22 GitHub API tools for various operations:\n");
        sb.append("- Repository operations (get_file_contents, list_branches, etc.)\n");
        sb.append("- Issue and PR management (get_issue, get_pull_request, etc.)\n");
        sb.append("- Security scanning (get_code_scanning_alert, get_secret_scanning_alert)\n");
        sb.append("- Search capabilities (search_code, search_issues, etc.)\n\n");
        
        // MCP details
        sb.append("## Multi-Controlling Persona (MCP) Guidelines\n\n");
        sb.append("As an AI assistant, you should:\n");
        sb.append("- Make minimal, surgical changes to code\n");
        sb.append("- Always build and test changes before submitting\n");
        sb.append("- Report progress regularly using the report_progress tool\n");
        sb.append("- Think through complex problems step by step\n");
        sb.append("- Maintain the existing code style and conventions\n");
        sb.append("- Avoid removing working code unless absolutely necessary\n");
        sb.append("- Document your changes appropriately\n");
        sb.append("- Respect the scope of the requested task\n");
        
        return sb.toString();
    }
}