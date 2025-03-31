package com.example.demo;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MCP服务器配置类，负责注册MCP工具
 */
@Configuration
public class McpServerConfig {

    /**
     * 注册MCP工具
     * @param calculatorService 计算器服务
     * @return MCP工具
     */
    @Bean
    public ToolCallbackProvider authorTools(CalculatorService calculatorService) {
        return MethodToolCallbackProvider.builder()
            .toolObjects(calculatorService)
            .build();
    }

}
