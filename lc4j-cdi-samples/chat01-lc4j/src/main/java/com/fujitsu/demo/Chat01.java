/*
 * Copyright (c) 2026 Fujitsu Limited. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.fujitsu.demo;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;

public class Chat01 {
  public static void main(String ... args) {
    var apiKey = System.getenv("GOOGLE_API_KEY");
    var model = GoogleAiGeminiChatModel.builder()
                 .apiKey(apiKey)
		             .modelName("gemini-2.5-flash-lite")
		             .build();
    var svc = AiServices.create(ChatService.class, model);
    var response = svc.chat(args[0]);
    System.out.println(response);
  }

}
