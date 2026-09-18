package com.fujitsu.demo;

import dev.langchain4j.cdi.spi.RegisterSequenceAgent;

@RegisterSequenceAgent(
    name = "arithmeticSequenceWorkflowAgent",
    description = "Generates a simple arithmetic problem and then solves it",
    outputKey = "answer",
    subAgentNames = {"problemGeneratorAgent", "problemSolverAgent"})
public interface ArithmeticSequenceWorkflowAgent {
  String process();
}