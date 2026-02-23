/*
 * Copyright (c) 2026 Fujitsu Limited. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.fujitsu.demo;

import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class Chat08 {
  public static void main(String ... args) {
    try (var container = SeContainerInitializer.newInstance().initialize()) {
      var chat = container.select(Chat08.class).get();
      chat.run(args[0]);
    }
  }

  @Inject 
  private ChatService svc;

  private void run(String prompt) {
    var response = svc.chat(prompt);
    System.out.println(response);
  }
}
