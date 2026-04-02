package com.nexus.copilot.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChatController {
    private final ChatClient chatClient;

    // 建構子注入：Spring AI 會自動幫你把 DeepSeek 設定裝進來
    public ChatController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder
                // 修正重點：使用 builder 模式，不要直接使用 new 構造函數
                .defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                .build();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam(defaultValue = "你好") String message){
        return chatClient.prompt().user(message).call().content();
        // chatClient.prompt() -> 開始建構問題
        // .user(message) -> 把用戶傳進來的字串塞進去
        // .call() -> 發送請求給 DeepSeek
        // .content() -> 只拿 AI 回傳的「文字內容」
    }
}
