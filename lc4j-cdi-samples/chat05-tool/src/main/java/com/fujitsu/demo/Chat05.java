/*
 * Copyright (c) 2026 Fujitsu Limited. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.fujitsu.demo;

import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class Chat05 {
  public static void main(String ... args) throws Exception {
    int number = Integer.parseInt(args[0]);
    try (var container = SeContainerInitializer.newInstance().initialize()) {
      var chat = container.select(Chat05.class).get();
      chat.run(number);
      chat.run(number+1);
      chat.run(number+2);
    }
  }

  @Inject 
  private ChatService svc;

  private void run(int number) {
    PrimeMinister pm = svc.chat(number);
    System.out.println(
      """
        %d代 内閣総理大臣は、%sです。出身は%sです。
      """.formatted(number, pm.name(), pm.place()));
  }
}
