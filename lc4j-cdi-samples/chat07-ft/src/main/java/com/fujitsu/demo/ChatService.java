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
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.faulttolerance.Fallback;
import java.time.temporal.ChronoUnit;

@RegisterAIService(chatModelName="demo-model",
                   scope=ApplicationScoped.class)
public interface ChatService {
  @Timeout(unit=ChronoUnit.SECONDS, value=1)
  @Fallback(fallbackMethod="chatFallback")
  @UserMessage(
    """
      日本国の {{number}}代 内閣総理大臣の名前と出身地を教えてください。
    """)
  PrimeMinister chat(@V("number") int number);

  default PrimeMinister chatFallback(int number) {
    return new PrimeMinister("N/A", "N/A");
  }
}

