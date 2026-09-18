# BNTO 推荐系统设计文档（本地存档）

从公司 Notion 导出的原始设计文档，配合 [`../reco-platform-review.html`](../reco-platform-review.html) 使用。底稿按"最优方案自圆其说"整理，这里是当时真实冻结的设计，两者有出入时以底稿为面试口径、以这里为事实依据。

| 文件 | 内容 | Notion 原页 | 导出时页面最后编辑 |
|---|---|---|---|
| [reco-hld.md](reco-hld.md) | HLD：背景、名词、与前端的契约（卡片信封、cursor 分页、埋点五组）、与算法的排序契约 | https://app.notion.com/p/3a6128bc62c6812aab4bf5cf5035bd78 | 2026-07-28 |
| [reco-lld-online.md](reco-lld-online.md) | 在线 LLD：主流程八环节、分页缓存去重（含 SET/BF/ZSET 选型 ADR）、AB 实验模型与分流 | https://app.notion.com/p/3a7128bc62c6816ea66ecdac482cc40f | 2026-08-06 |
| [reco-pagination-offset-vs-cursor.md](reco-pagination-offset-vs-cursor.md) | HLD 子页：offset 与 cursor 分页对比 | https://app.notion.com/p/3a6128bc62c681e5b697efb60737f9b9 | 2026-07-23 |
| [reco-feed-card-contract.md](reco-feed-card-contract.md) | Feed 卡片契约（BNTO-8351）：请求响应、九种卡片样式、解析规则、样式常量表 | https://app.notion.com/p/3c3128bc62c6813eaef7da195b696196 | 2026-09-11 |

导出日期 2026-09-18。未导出：埋点 LLD（Notion 页已清空，内容并入在线 LLD Part 3 归因闭环）。

架构图源文件在 [`diagrams/`](diagrams/)：`reco-hld-architecture.svg`（HLD 整体架构，2026-07-27 版）、`reco-pipeline-overview.svg`（LLD pipeline 总览，2026-08-06 版），从当年 Claude 会话记录中上传 Notion 的附件原样恢复，可直接在浏览器打开。
