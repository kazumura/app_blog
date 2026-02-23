/*
 * Copyright (c) 2026 Fujitsu Limited. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.fujitsu.demo;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.guardrail.OutputGuardrail;
import dev.langchain4j.guardrail.OutputGuardrailResult;

public class SpecOutputGuardrail implements OutputGuardrail {
  @Override
  public OutputGuardrailResult validate(AiMessage msg) {
    String text = msg.text();
    System.out.println("response from LLM: \n" + text);
    if (!text.contains("Jakarta"))
      return failure("No Jakarta keyword!");
    return success();
  }
}

