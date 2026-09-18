package com.fujitsu.demo;

import jakarta.enterprise.context.ApplicationScoped;
import org.mcpjava.server.tools.Tool;
import org.mcpjava.server.tools.ToolArg;

@ApplicationScoped
public class Calculator {

  @Tool(description = "Add two numbers")
  public int add(
           @ToolArg(description = "First number") int a,
           @ToolArg(description = "Second number") int b) {
    return a + b;
  }

  @Tool(description = "Substract two numbers")
  public int sub(
           @ToolArg(description = "First number") int a,
           @ToolArg(description = "Second number") int b) {
    return a - b;
  }

  @Tool(description = "Multiply two numbers")
  public int mul(
           @ToolArg(description = "First number") int a,
           @ToolArg(description = "Second number") int b) {
    return a * b;
  }

  @Tool(description = "Divide two numbers")
  public int div(
           @ToolArg(description = "First number") int a,
           @ToolArg(description = "Second number") int b) {
    return a / b;
  }

}
