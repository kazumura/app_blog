package com.fujitsu.demo;

import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class Main {

  public static void main(String ... args) throws Exception {
    try (var cdi = SeContainerInitializer.newInstance().initialize()) {
      var h = cdi.select(Main.class).get();
      h.run();
    }
  }

  @Inject
  private ArithmeticSequenceAgent arithmeticSequenceAgent;

  private void run() throws Exception {
    var result = arithmeticSequenceAgent.process();
    System.out.println(result);
  }

}

