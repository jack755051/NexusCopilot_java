package com.nexus.copilot.service;

import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class IngestionService {
    private final VectorStore vectorStore;

    public IngestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void ingest(Resource resource) {
        // 1. 讀取文件內容
        var reader = new TextReader(resource);

        // 2. 切片 (Chunking)：把大文章切成 500 字的小塊，避免 AI 記不住
        var splitter = TokenTextSplitter.builder()
                .withChunkSize(800)    // 每個切片的大小 (Token 數)
                .withKeepSeparator(true) // 是否保留分隔符
                .build();
        var documents = splitter.apply(reader.get());

        // 3. 向量化並存入 pgvector
        // vectorStore 會自動呼叫 OpenAI/DeepSeek 的 Embedding API
        vectorStore.add(documents);
    }
}
