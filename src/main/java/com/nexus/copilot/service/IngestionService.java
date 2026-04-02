package com.nexus.copilot.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class IngestionService {
    private final VectorStore vectorStore;

    public IngestionService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void ingest(Resource resource,String originalFilename) {
        // 1. 根據格式處理（目前範例：支援 .txt, .md, .pdf）
        List<Document> documents;

        String extension = getFileExtension(originalFilename);

        if(extension.equalsIgnoreCase("pdf")){
            throw new UnsupportedOperationException("PDF 功能尚未開啟，請先加入 Gradle 依賴");
        }else{
            // 預設當作純文字處理
            var reader = new TextReader(resource);
            documents = reader.get();
        }

        // 2. 注入 Metadata（這步很重要，方便之後過濾資料來源）
        documents.forEach(doc -> doc.getMetadata().put("source", originalFilename));

        // 3. 統一切片
        var splitter = TokenTextSplitter.builder()
                .withChunkSize(800)
                .withKeepSeparator(true)
                .build();

        var splitDocuments = splitter.apply(documents);

        // 4. 存入 pgvector
        vectorStore.add(splitDocuments);
    }

    private String getFileExtension(String filename){
        return (filename != null && filename.contains("."))
            ? filename.substring(filename.lastIndexOf(".") + 1) :"";

    }
}
