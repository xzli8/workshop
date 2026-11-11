# Ocean AI Labs Beijing — 技术面试（Certificate Admin Console 笔试）

来源：笔试题 PDF（`~/Desktop/codes/oceanai/Ocean AI Labs Beijing - Technical Interview Assessment - 202611.pdf`，版本 20261102），2026-11-04 调研。
薪资、岗位名称、JD：都没有（公开渠道查不到招聘信息，只有这份笔试题）。

## 公司
- 公开渠道查不到 "Ocean AI Labs"（无官网、LinkedIn、BOSS、天眼查结果，oceanlabs.ai 是待售域名）
- 同名的都不是它（oceanai.so 是美国 Ocean AI, LLC 做聊天 / 听写工具，oceanai.com.cn 在湖北做视觉，Ocean 邮件安全公司）
- 线索：笔试提交邮箱是 michael.wang@teohcapital.com -> Teoh Capital
  - 悉尼的家族办公室式私募（2020 年成立，团队 4–10 人），管理 TPG Telecom 创始人 David Teoh 的资产
  - 投澳新的 B2B 软件（年经常性收入 100 万–5000 万澳元）和老牌品牌，垂直领域含教育、合规、物流、医疗等
  - ZoomInfo 显示近期招过 AI 产品经理（地点不明）
- 推断（未证实）：Teoh Capital 在北京设的 AI / 工程团队，给澳洲被投软件公司做产品
  - 依据：题目租户用 Australia/Sydney、Australia/Perth 时区，题目是培训平台发证书（Safety 101，对应教育 / 合规类 SaaS）

## 笔试
- 1 小时一次做完，从空仓库开始，允许 AI 生成全部代码，必须提交全部 AI 会话的原始导出 + 决策日志（至少一个采纳、一个否决或修正、一个取舍）
- 技术栈：后端 Go / Python / Node，SQLite / MySQL / PostgreSQL，前端 Vue 3 或 React，一条命令启动
- 验收 A1–A7：租户隔离、幂等键重试（并发也只算一次）、幂等键误用返回 409、学员发证结果、时区与本地日期、10 万行 CSV 导出（60 秒内、排序稳定、导出时列表接口仍可用）、撤销审计不重复
- 第 6 节 3 道书面题必须不用 AI 自己写（滚动发布下的列改名、缓存删除后被旧值回写、下游变慢拖垮线程池）
- 评分看：端到端正确性、系统设计、对 AI 输出的验证和修正、工程实践、取舍（不看 prompt 数量和工具品牌）
- 终面 60 分钟：追问提交内容、书面题、AI 会话和代码，加技术和行为面

## 注意
- GitHub 上有别的候选人公开的提交（Dyna829/ocean），不要参考（单人限时作答，终面会逐条追问）
- 待问：中国的签约主体（外资公司、代雇还是和 Teoh Capital 的关系），岗位名称和团队规模，产品是给哪家被投公司做的，是否远程、英语要求、薪资
