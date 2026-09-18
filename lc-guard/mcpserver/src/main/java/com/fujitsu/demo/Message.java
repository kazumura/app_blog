package com.fujitsu.demo;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Message {

  public String getText() {
    return "Hello Hello\n";
  }

}

