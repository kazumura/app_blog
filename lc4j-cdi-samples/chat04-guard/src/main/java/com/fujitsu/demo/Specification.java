/*
 * Copyright (c) 2026 Fujitsu Limited. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.fujitsu.demo;

import dev.langchain4j.model.output.structured.Description;

public record Specification(@Description("name of specification")String name, 
		            @Description("createion date of specification") String date) {}

