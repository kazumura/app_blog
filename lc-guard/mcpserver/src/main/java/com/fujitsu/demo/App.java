package com.fujitsu.demo;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("/")
public class App extends Application {

  public Set<Class<?>> getClasses() {
    return Set.of(HelloResource.class, 
                  JwtAuthenticationFilter.class,
                  dev.langchain4j.cdi.mcp.server.transport.McpEndpoint.class);

  }

}

