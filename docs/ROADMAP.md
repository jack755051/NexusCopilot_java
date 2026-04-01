# 🚀 NexusCopilot RAG 系統開發路線圖 (Roadmap)

## 📌 專案願景
打造一個基於 **Java 25** 與 **Spring Boot 4.0** 的高效能 RAG (檢索增強生成) 知識庫助手。透過 **DeepSeek API** 進行推理，並使用 **PostgreSQL (pgvector)** 作為向量記憶體，實現精準、具備私有知識的文件問答系統。

---

## 🏗️ 技術棧 (Tech Stack)
* **語言 (Language)**: Java 25 (Amazon Corretto)
* **框架 (Framework)**: Spring Boot 4.0.5
* **AI 引擎 (AI Engine)**: Spring AI 2.0.0-M3 (DeepSeek / OpenAI API 兼容)
* **資料庫 (Database)**: PostgreSQL 16 + pgvector (向量擴展)
* **構建工具 (Build Tool)**: Gradle - Kotlin DSL
* **容器化 (DevOps)**: Docker Compose + env-file 管理

---

## 🗺️ 開發階段規劃

### 🟢 階段 1：基礎建設與點火測試 (Infrastructure & Smoke Test)
* [ ] **環境初始化**：完成 `compose.yaml` (pgvector) 與 `.env` 環境變數配置。
* [ ] **API 整合**：成功介接 DeepSeek API (透過 Spring AI OpenAI Starter)。
* [ ] **基礎對話接口**：實作 `ChatController`，支援同步與 **Streaming (SSE)** 串流回傳。
* [ ] **CORS 配置**：開放跨域請求，確保 Next.js 前端 (Port 3000) 可存取。

### 🟡 階段 2：對話記憶持久化 (Memory & Persistence)
* [ ] **資料表設計**：建立 `ChatLog` Entity，定義對話存儲結構。
* [ ] **JDBC Chat Memory**：配置 Spring AI 自動記憶機制，將對話紀錄存入 Postgres。
* [ ] **上下文對話**：實現 AI 能根據歷史紀錄進行連續性回覆。

### 🟠 階段 3：文檔攝入與向量化 (Ingestion & Embedding) - 核心關鍵
* [ ] **文件解析**：實作 `FileService`，支援 PDF、Markdown、TXT 上傳。
* [ ] **ETL 流程實作**：
    * **Document Reader**：讀取原始文字。
    * **Text Splitter**：將長文進行語義切片 (Chunking)。
* [ ] **向量化儲存**：呼叫 Embedding 模型並將數據存入 `VectorStore` (pgvector)。

### 🔴 階段 4：RAG 檢索生成邏輯 (Retrieval Augmented Generation)
* [ ] **向量檢索**：實作 `Similarity Search`，根據用戶問題找回最相關的 Top-K 文檔片段。
* [ ] **Prompt Engineering**：建構 RAG 專用 System Prompt 模板。
* [ ] **RagService 實作**：串連「檢索 -> 增強 -> 生成」完整閉環。

### 🔵 階段 5：前端整合與生產優化 (Next.js & Production)
* [ ] **Next.js 介面**：開發對話視窗、串流顯示效果與文件管理儀表板。
* [ ] **效能優化**：啟用 Java 25 **Virtual Threads** 處理高併發 AI 請求。
* [ ] **簡易 CI/CD**：編寫 `Dockerfile`，實現環境變數驅動的一鍵部署。

---

## 📂 專案 Package 結構建議 (Java)

```text
src/main/java/com/nexus/copilot/
├── controller/    # API 入口 (REST Endpoints)
├── service/       # 核心業務邏輯 (AI 邏輯, RAG 流程, 文件處理)
├── repository/    # 資料庫訪問 (JPA Repository / VectorStore)
├── model/         # 資料實體 (JPA Entities / DTOs)
├── config/        # 框架配置 (CORS, AI Bean, Security)
└── exception/     # 全域錯誤處理 (Global Exception Handling)