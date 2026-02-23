/*
 * Copyright (c) 2026 Fujitsu Limited. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.fujitsu.demo;

import dev.langchain4j.service.UserMessage;

public interface ChatService {
  String chat(@UserMessage String prompt);
}

