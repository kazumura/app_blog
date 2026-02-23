/*
 * Copyright (c) 2026 Fujitsu Limited. All rights reserved.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.fujitsu.demo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2q.AllMiniLmL6V2QuantizedEmbeddingModel;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import java.util.List;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.inject.Inject;

@ApplicationScoped
public class RagIntegrator {
  @Produces
  @ApplicationScoped
  public EmbeddingModel embeddingModel() {
    return new AllMiniLmL6V2QuantizedEmbeddingModel();
  }

  @Inject
  @ApplicationScoped
  @ConfigProperty(name="app.doc.file")
  private String file;

  @Produces
  @ApplicationScoped
  public EmbeddingStore<TextSegment> embeddingStore() {
    EmbeddingStore<TextSegment> store = new InMemoryEmbeddingStore<>();
    Document doc = FileSystemDocumentLoader.loadDocument(file);
    EmbeddingStoreIngestor.ingest(doc, store);
    return store;
  }
}

