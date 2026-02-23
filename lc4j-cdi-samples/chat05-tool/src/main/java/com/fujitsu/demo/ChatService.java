/*
 * Copyright (c) 2026 Fujitsu Limited. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.fujitsu.demo;

import dev.langchain4j.cdi.spi.RegisterAIService;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import jakarta.enterprise.context.ApplicationScoped;
import dev.langchain4j.agent.tool.Tool;

@RegisterAIService(chatModelName = "demo-model",
                   tools = {History.class},
                   scope = ApplicationScoped.class)
public interface ChatService {
  @UserMessage(
    """
      日本国の {{number}}代 内閣総理大臣の名前と出身地を教えてください。
    """)
  PrimeMinister chat(@V("number") int number);

}

