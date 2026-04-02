package com.nexus.copilot.strategy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.util.List;

@Component
public class ImageOcrReaderStrategy implements DocumentReaderStrategy{
    private final ChatClient chatClient;
    public ImageOcrReaderStrategy(ChatClient.Builder chatClient) {this.chatClient = chatClient.build();}

    @Override
    public boolean supports(String ext) { return List.of("png", "jpg", "jpeg").contains(ext.toLowerCase()); }

    @Override
    public List<Document> read(Resource res, String filename) {
        // 簡單判斷 MimeType
        var mimeType = filename.toLowerCase().endsWith("png") ?
                MimeTypeUtils.IMAGE_PNG : MimeTypeUtils.IMAGE_JPEG;

        String description = chatClient.prompt()
                .user(u -> u.text("請分析此圖片內容並轉為詳細文字描述，若有文字請完整提取。")
                        .media(mimeType, res)) // 使用動態變數
                .call().content();

        var doc = new Document(description);
        doc.getMetadata().put("source", filename); // 圖片也別忘了加 source
        return List.of(doc);
    }
}
