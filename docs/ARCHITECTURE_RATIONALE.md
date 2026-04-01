# 🧠 NexusCopilot: 數位第二大腦與架構演進實踐

## 🎯 專案初衷 (The Core Mission)

NexusCopilot 的誕生並非為了重複造輪子，而是為了解決 AI 時代下的 **「資訊淹沒」** 與 **「知識斷層」**。本專案致力於構建一個私有化、高安全性的 **RAG (Retrieval-Augmented Generation)** 系統。

### 1. 數據主權 (Data Sovereignty)
在公用 AI 普及的時代，私有數據的安全性是核心痛點。NexusCopilot 讓用戶能夠在完全掌控的基礎設施（Docker/私有雲）中處理敏感文件，確保「數據不出門，智慧隨手得」。

### 2. 語義記憶 (Semantic Memory)
超越傳統的關鍵字搜尋，利用 **向量嵌入 (Vector Embeddings)** 技術，讓電腦具備「理解」文本意圖的能力，實現從「資料檢索」到「知識理解」的跨越。

### 3. 消除幻覺 (Grounding AI)
透過 RAG 架構，強迫大語言模型（LLM）基於特定的私有知識庫進行回答。
> **原則：無據不言，有本可循。**

---

## 🛠️ 技術炫技點 (Engineering Excellence)

本專案不僅是功能實作，更是對現代軟體工程深度的探索，重點展示以下技術維度：

### 🏗️ 基礎設施：雲端原生與容器編排
* **多階段編譯優化**：在 Dockerfile 中實現編譯環境與執行環境分離，極致縮減映像檔體積。
* **自動化環境對齊**：透過 Docker Compose 實現「一鍵啟動」包含向量擴展（pgvector）的複雜開發環境。

### 📊 資料科學：向量檢索與最佳化
* **高效能向量儲存**：在 PostgreSQL 中實作 1536 維度向量的索引與儲存。
* **檢索算法優化**：探索餘弦相似度（Cosine Similarity）在不同語境下的檢索精度。

### 🔄 架構演進：跨語言遷移實作 (C# ➔ Java)
* **工程適應力**：展示從 `.NET 10 (EF Core)` 到 `Spring Boot 3 (JPA)` 的架構遷移能力。
* **效能對照**：分析不同語言環境下，處理大規模向量計算與併發請求時的資源分配與 GC 表現。

### 🎨 前端革新：Next.js 極致體驗
* **流式輸出 (Streaming)**：實作 AI 逐字回覆（Server-Sent Events），提升用戶感官流暢度。
* **Server-side Excellence**：利用 Next.js 的 Edge Runtime 減少首屏加載時間。

---

## 📈 演進路徑 (Evolution Roadmap)

1. **[Phase 1] 基礎設施搭建**：Docker + pgvector 網路對齊。
2. **[Phase 2] C# 原型開發**：快速驗證 RAG 核心邏輯。
3. **[Phase 3] Java 企業級重構**：轉向 Spring Boot 生態，強化併發處理。
4. **[Phase 4] Next.js 前端重塑**：從 Angular 遷移至 React 生態，優化 AI 互動介面。

---
*NexusCopilot - 不只是程式碼，是數據向智慧進化的過程。*