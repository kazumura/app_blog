package com.fujitsu.demo;

import jakarta.ws.rs.SeBootstrap;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Main {

  public static void main(String ... args) throws Exception {
    try (var cdi = SeContainerInitializer.newInstance().initialize()) {
      var h = cdi.select(Main.class).get();
      h.run();
    }
  }


  private void run() throws Exception {
    var config = SeBootstrap.Configuration.builder()
                   .host("0.0.0.0")
                   .port(9090)
                   .build();
    var instance = SeBootstrap.start(new App(), config)
                     .toCompletableFuture()
                     .join();
    Runtime.getRuntime().addShutdownHook(
       new Thread( () -> instance.stop().toCompletableFuture().join()));
    
    Thread.currentThread().join();   

  }

}
