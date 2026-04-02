package com.nexus.copilot.service;

import com.nexus.copilot.strategy.DocumentReaderStrategy;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngestionService {
    private final VectorStore vectorStore;
    private final List<DocumentReaderStrategy> strategies;

    public IngestionService(VectorStore vectorStore, List<DocumentReaderStrategy> strategies) {
        this.vectorStore = vectorStore;
        this.strategies = strategies;
    }

    public void ingest(Resource resource,String originalFilename) {
        String extension = getFileExtension(originalFilename).toLowerCase();

        // 1. 核心：遍歷所有策略，找到第一個支援該副檔名的工人
        var documents = strategies.stream()
                .filter(strategy -> strategy.supports(extension))
                .findFirst()
                .map(strategy->strategy.read(resource, originalFilename))
                .orElseThrow(()->new IllegalArgumentException("Unsupported file format: "+extension));

        // 2. 統一切片 (這部分的邏輯不論什麼格式都要做，所以留在這裡)
        var splitter = TokenTextSplitter.builder()
                .withChunkSize(800)
                .build();

        var splitDocuments = splitter.apply(documents);

        // 3. 向量化並存入資料庫
        vectorStore.add(splitDocuments);
    }

    private String getFileExtension(String filename){
        return (filename != null && filename.contains("."))
            ? filename.substring(filename.lastIndexOf(".") + 1) :"";

    }
}
