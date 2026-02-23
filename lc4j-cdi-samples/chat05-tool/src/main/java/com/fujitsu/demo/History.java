/*
 * Copyright (c) 2026 Fujitsu Limited. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.fujitsu.demo;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.cdi.spi.RegisterAIService;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

public class History {
  @Tool("指定された代の日本国内閣総理大臣の名前を返却する")
  String getPrimeMinsterName(int era) throws Exception {
	if (era > 11)
	  throw new Exception("Sorry, I cannot answer to your question.");
	return PRIME_MINISTERS[era];
  }

  static final String[] PRIME_MINISTERS = {
   "", // N/A
   "伊藤博文", // 1st
   "黑田清隆", // 2nd
   "山縣有朋", // 3th
   "松方正義", // 4th
   "伊藤博文", // 5th
   "松方正義", // 6th
   "伊藤博文", // 7th
   "大隈重信", // 8th
   "山縣有朋", // 9th
   "伊藤博文", // 10th
   "桂太郎", // 11th
  };

  @Inject 
  private PlaceService ps;
  
  @Tool("指定された日本国内閣総理大臣の出身地を返却する")
  String getBornPlaceForPrimeMinster(String primeMinisterName) {
    return ps.bornPlace(primeMinisterName);
  }
  
  @RegisterAIService(chatModelName = "demo-model",
                     scope = ApplicationScoped.class)
  static public interface PlaceService {
    @UserMessage("""
        日本国の内閣総理大臣であった {{name}} の出身地を教えてください。
	      出身地名を一つだけ回答してください。
	  """)
    String bornPlace(@V("name") String primeMinistreName);
  }
}
