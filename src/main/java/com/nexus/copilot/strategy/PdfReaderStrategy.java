package com.nexus.copilot.strategy;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PdfReaderStrategy implements DocumentReaderStrategy {
    @Override
    public boolean supports(String ext) { return ext.equalsIgnoreCase("pdf"); }

    @Override
    public List<Document> read(Resource res, String filename) {
        var reader = new PagePdfDocumentReader(res);
        var docs = reader.get();

        // 將檔名存入每個 Document 的 Metadata
        docs.forEach(doc -> doc.getMetadata().put("source", filename));

        return docs;
    }
}