/*
 * Copyright (c) 2026 Fujitsu Limited. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.fujitsu.demo;

import dev.langchain4j.cdi.spi.RegisterAIService;
import dev.langchain4j.service.UserMessage;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAIService(chatModelName="demo-model",
                   contentRetrieverName="demo-retriever",
                   scope=ApplicationScoped.class)
public interface ChatService {
  String chat(@UserMessage String prompt);
}

