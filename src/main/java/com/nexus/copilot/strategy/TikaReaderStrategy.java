package com.nexus.copilot.strategy;

import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.document.Document;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TikaReaderStrategy implements DocumentReaderStrategy{
    @Override
    public boolean supports(String ext) { return List.of("doc", "docx", "pptx").contains(ext.toLowerCase()); }

    @Override
    public List<Document> read(Resource resource, String filename) {
        var reader = new TikaDocumentReader(resource);
        var docs = reader.get();
        docs.forEach(doc -> doc.getMetadata().put("source", filename));
        return docs;
    }
}
