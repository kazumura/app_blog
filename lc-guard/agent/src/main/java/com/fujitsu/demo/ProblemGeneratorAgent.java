package com.fujitsu.demo;

import dev.langchain4j.cdi.spi.RegisterSimpleAgent;
import dev.langchain4j.service.UserMessage;

@RegisterSimpleAgent(
    name = "problemGeneratorAgent",
    description = "Generates one simple arithmetic problem",
    outputKey = "problem",
  chatModelName = "#default",
  outputGuardrails = DivisionProblemOutputGuardrail.class)
public interface ProblemGeneratorAgent {

  @UserMessage("""
      Create one simple arithmetic problem containing exactly two integers and
      one operation: addition, subtraction, multiplication, or division. For
      division, use a nonzero divisor and ensure the result is an integer.
      Return only the problem statement without the answer.
      """)
  String generate();
}