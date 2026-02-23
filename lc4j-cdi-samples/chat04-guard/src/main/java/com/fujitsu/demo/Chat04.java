/*
 * Copyright (c) 2026 Fujitsu Limited. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.fujitsu.demo;

import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class Chat04 {
  public static void main(String ... args) {
    int number = Integer.parseInt(args[0]);
    try (var container = SeContainerInitializer.newInstance().initialize()) {
      var chat = container.select(Chat04.class).get();
      chat.run(number);
    }
  }

  @Inject 
  private ChatService svc;

  private void run(int number) {
    Specification spec = svc.chat(number);
    System.out.println("""
	  %sは、%sに、%d番目の仕様として策定されました。
	""".formatted(spec.name(), spec.date(), number));
  }
}
