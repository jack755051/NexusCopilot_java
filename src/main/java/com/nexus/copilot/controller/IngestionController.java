package com.nexus.copilot.controller;

import com.nexus.copilot.service.IngestionService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/ingestion")
public class IngestionController {
    private final IngestionService ingestionService;

    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        // 將上傳的檔案轉成 Resource 並交給 Service 處理
        var resource = new InputStreamResource(file.getInputStream());
        ingestionService.ingest(resource);
        return "文件已成功向量化並存入資料庫！";
    }
}
