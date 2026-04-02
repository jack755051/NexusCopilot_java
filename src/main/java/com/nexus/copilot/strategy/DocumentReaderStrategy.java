package com.nexus.copilot.strategy;

import org.springframework.ai.document.Document;
import org.springframework.core.io.Resource;
import java.util.List;

public interface DocumentReaderStrategy {
    /**
     * 檢查此策略是否支援該副檔名 (例如 "pdf", "txt")
     */
    boolean supports(String extension);

    /**
     * 執行讀取邏輯
     * @param resource 檔案資源
     * @param filename 原始檔名 (用於寫入 Metadata 溯源)
     * @return 讀取出來的 Document 清單
     */
    List<Document> read(Resource resource, String filename);
}