package com.fujitsu.demo;

import static org.junit.Assert.assertTrue;

import dev.langchain4j.data.message.AiMessage;
import org.junit.Test;

public class DivisionProblemOutputGuardrailTest {

  private final DivisionProblemOutputGuardrail guardrail = new DivisionProblemOutputGuardrail();

  @Test
  public void rejectsDivisionProblems() {
    assertTrue(guardrail.validate(AiMessage.from("12 / 3")).isFatal());
    assertTrue(guardrail.validate(AiMessage.from("What is 12 divided by 3?")).isFatal());
    assertTrue(guardrail.validate(AiMessage.from("12 ÷ 3 を計算してください")).isFatal());
  }

  @Test
  public void acceptsOtherArithmeticProblems() {
    assertTrue(guardrail.validate(AiMessage.from("12 + 3")).isSuccess());
    assertTrue(guardrail.validate(AiMessage.from("12 - 3")).isSuccess());
    assertTrue(guardrail.validate(AiMessage.from("12 * 3")).isSuccess());
  }
}