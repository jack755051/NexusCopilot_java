package com.nexus.copilot.strategy;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TextReaderStrategy implements DocumentReaderStrategy{
    @Override
    public boolean supports(String ext) {
        return List.of("txt", "md", "csv").contains(ext.toLowerCase());
    }

    @Override
    public List<Document> read(Resource resource, String filename) {
        var reader = new TextReader(resource);
        var docs = reader.get();
        docs.forEach(doc -> doc.getMetadata().put("source", filename));
        return docs;
    }
}

