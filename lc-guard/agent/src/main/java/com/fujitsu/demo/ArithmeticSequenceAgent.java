package com.fujitsu.demo;

import dev.langchain4j.guardrail.GuardrailResult;
import dev.langchain4j.guardrail.OutputGuardrailException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ArithmeticSequenceAgent {

  @Inject
  ArithmeticSequenceWorkflowAgent workflowAgent;

  public String process() {
    try {
      return workflowAgent.process();
    } catch (RuntimeException exception) {
      var guardrailException = findGuardrailException(exception);
      if (guardrailException == null) {
        throw exception;
      }
      return guardrailException.result().failures().stream()
          .map(GuardrailResult.Failure::message)
          .findFirst()
          .orElseGet(guardrailException::getMessage);
    }
  }

  private static OutputGuardrailException findGuardrailException(Throwable exception) {
    var cause = exception;
    while (cause != null) {
      if (cause instanceof OutputGuardrailException guardrailException) {
        return guardrailException;
      }
      cause = cause.getCause();
    }
    return null;
  }
}