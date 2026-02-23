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

@RegisterAIService(chatModelName="demo-model",
                   scope=ApplicationScoped.class)
public interface ChatService {
  @UserMessage ("""
     Jakarta EE および Java EE 仕様の中で、 {{number}} 番目に策定された仕様について、
     正式な仕様名と策定された日付を教えてください。
  """)
  Specification chat(@V("number") int number);
}

