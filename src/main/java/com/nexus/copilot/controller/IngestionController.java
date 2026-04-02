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
    public String upload(@RequestParam("file") MultipartFile[] files) throws IOException {
        // 遍歷所有上傳的檔案
        for(MultipartFile file:files){
            if(!file.isEmpty()){
                // 將上傳的檔案轉成 Resource 並交給 Service 處理
                String contentType = file.getContentType();
                if (contentType != null && (contentType.contains("text") || contentType.contains("pdf"))) {
                    var resource = new InputStreamResource(file.getInputStream());
                    ingestionService.ingest(resource, file.getOriginalFilename());
                }
            }
        }
        return "已成功處理 " + files.length + " 個檔案並存入向量資料庫！";
    }
}
