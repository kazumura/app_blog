package com.fujitsu.demo;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.guardrail.OutputGuardrail;
import dev.langchain4j.guardrail.OutputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.regex.Pattern;

@ApplicationScoped
public class DivisionProblemOutputGuardrail implements OutputGuardrail {

  private static final Pattern DIVISION_PATTERN = Pattern.compile(
      "(?:[/÷]|\\b(?:divide(?:d|s)?|division|quotient)\\b|割り算|除算|商|割(?:る|った|って|れ))",
      Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

  @Override
  public OutputGuardrailResult validate(AiMessage responseFromLLM) {
    if (DIVISION_PATTERN.matcher(responseFromLLM.text()).find()) {
      return fatal("Division problem detected: " + responseFromLLM.text());
    }
    return success();
  }
}