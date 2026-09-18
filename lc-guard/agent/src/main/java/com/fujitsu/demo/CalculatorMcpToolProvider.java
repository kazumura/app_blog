package com.fujitsu.demo;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.http.StreamableHttpMcpTransport;
import dev.langchain4j.service.tool.ToolProvider;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import java.util.Map;
import org.eclipse.microprofile.config.ConfigProvider;

@ApplicationScoped
public class CalculatorMcpToolProvider {

  private McpClient mcpClient;

  @Produces
  @ApplicationScoped
  @Named("calculatorMcpToolProvider")
  public ToolProvider calculatorMcpToolProvider() {
    var mcpUrl = ConfigProvider.getConfig()
        .getOptionalValue("calculator.mcp.url", String.class)
        .orElse("http://localhost:9090/mcp");
    var token = ConfigProvider.getConfig()
      .getValue("calculator.mcp.token", String.class);
    var transport = StreamableHttpMcpTransport.builder()
        .url(mcpUrl)
      .customHeaders(Map.of("Authorization", "Bearer " + token))
        .build();
    mcpClient = DefaultMcpClient.builder()
        .key("calculator")
        .transport(transport)
        .protocolVersion("2024-11-05")
        .build();
    return McpToolProvider.builder()
        .mcpClients(mcpClient)
        .filterToolNames("add", "sub", "mul", "div")
        .failIfOneServerFails(true)
        .build();
  }

  @PreDestroy
  void close() {
    if (mcpClient != null) {
      try {
        mcpClient.close();
      } catch (Exception e) {
        throw new IllegalStateException("Failed to close the calculator MCP client", e);
      }
    }
  }
}