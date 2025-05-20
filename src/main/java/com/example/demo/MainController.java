package com.example.demo;

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
    
    @GetMapping("/copilot/prompt")
    public String getCopilotPrompt() {
        return "Copilot is an AI assistant powered by large language models that helps users with "
               + "coding and answering questions. It is trained on publicly available code and text, "
               + "and uses context from your files, comments, and code to provide relevant suggestions "
               + "and responses. The prompt contains instructions about coding best practices, "
               + "documentation, and how to respond to different types of queries.";
    }
}
