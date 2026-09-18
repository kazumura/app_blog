package com.fujitsu.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;

import dev.langchain4j.agentic.agent.AgentInvocationException;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.guardrail.OutputGuardrailException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import org.junit.Test;

public class ArithmeticSequenceAgentTest {

  @Test
  public void returnsGuardrailFailureMessage() {
    var result = new DivisionProblemOutputGuardrail().validate(AiMessage.from("12 / 3"));
    var guardrailException = new OutputGuardrailException("Output rejected", null, result);
    var invocationException = new AgentInvocationException(
        new UndeclaredThrowableException(new InvocationTargetException(guardrailException)));
    var agent = new ArithmeticSequenceAgent();
    agent.workflowAgent = () -> {
      throw invocationException;
    };

    assertEquals("Division problem detected: 12 / 3", agent.process());
  }

  @Test
  public void rethrowsNonGuardrailFailures() {
    var invocationException = new AgentInvocationException("Agent failed");
    var agent = new ArithmeticSequenceAgent();
    agent.workflowAgent = () -> {
      throw invocationException;
    };

    var thrown = assertThrows(AgentInvocationException.class, agent::process);

    assertSame(invocationException, thrown);
  }
}