# 一套骨架收下 Agent 工程师面试全部高频题

2025–2026 的 Agent / AI 应用工程师面试题可以全部挂到一张 12 节点的骨架上：LLM 基础、Prompt、结构化输出、工具调用与 MCP/Skills、Agent 循环与 Harness、多 Agent、记忆与上下文、RAG、评测、可靠性与安全、成本与生产工程、行为与 AI 编程习惯。中英文来源里真正反复出现（≥5 个独立来源、且中英文一手面经都有）的只有十来个题簇：ReAct 及其与 Plan-and-Execute 的选型、MCP vs Function Calling vs Skills、死循环检测、上下文压缩与长短期记忆、RAG 全链路（切块 / 混合检索 / Rerank / 评测 / 幻觉）、Prompt Injection 与高危操作管控、Agent 评测体系与指标口径、Token 成本治理、以及"设计一个客服 Agent"这道标准系统设计题（[KK]、[ML155]、[XL]、[GH-Shekhar]、[TI-Applied]、[PracHub]）。轮次上国内是"项目追问 15–20 题 + 一道 LeetCode Medium"，海外 Agent 公司是"DS&A 电面 → take-home 造一个 Agent → onsite 答辩 + 系统设计 + 客户模拟"；两边都把项目深挖当主线，套路一致：先验真实性（上线没有、日均多少请求），再沿失败路径追问（超时、重复调用、压缩丢信息、崩溃恢复），再逼你比较"为什么不用 Workflow / 规则 / 小模型 / 长上下文 / grep"，最后追指标口径（[KK]、[KM-MT]、[Aced-Sierra]）。算法八股在应用岗只剩"基础覆盖"（Attention 概述、采样参数、KV Cache、LoRA、SFT vs RLHF 的取舍），GRPO/PPO loss、手撕 MHA、分布式训练留在算法岗，但腾讯、百度文心、快手这类混合团队会把 GRPO/QLoRA 带进应用岗面试（[KK]、[KK2]）。2026 下半年的新增重点是 Harness Engineering、Claude Code 机制级问题、Skill vs Tool 边界、grep vs RAG、幂等与断点恢复，2025 题库里的 LangChain 组件名词题（LCEL、Output Parser）在 2026 一手面经里已基本消失（[ML155]、[XL]、[CSDN100]）。

标注约定
- 题型：概念 / 设计 / 手撕 / 深挖 / 行为 / Dbg（"你的 Agent 出了 X 怎么办"的场景排障题，英文面试的主流题型）
- 频率：高 = ≥5 个独立来源或中英文一手面经都出现；中 = 3–4 个来源；低 = 1–2 个来源或只在题库（频率数的是来源数不是面试场次，没有任何来源给过真实面试统计）
- 来源可信度：一手面经（[NC-BD2]、[NC-TXBD]、[KM-MT]、[KM-QW]、[KM-BD]、[JJ]）> 逐条带原帖链接的二手汇总（[KK]、[KK2]）> 二手题库（[ML155]、[ML265]、[XL]、[KM]、[GH-Shekhar]、[LDS-50]、[AV-30]、[PracHub]、[Aced-45]）> 面试官 / 招聘方自述（[PromptLayer]、[TianPan]、[Hamel]、[Gauntlet]、各 JD）> 公司标签无出处的 [ADG]（本文凡引 ADG 均标"标 X 公司"）
- 二手题库之间的公司标签高度重合但互相无法核实，频率判断以一手帖和 KK 为准

## 1. 知识骨架：四层十二节点

四层的分法来自 20 多份官方教材的共同结构：模型本身 → Agent 内核（循环 / 工具 / 记忆 / 多 Agent）→ 围绕模型的工程（RAG / 评测 / 安全）→ 生产与人（成本 / 轮次 / 行为）；2024 的材料（Lilian Weng、Google Agents、Anthropic BEA）按 Agent 内部解剖组织，2025–2026 的材料（OpenAI 指南、Anthropic 后续文章、LangChain 文档、Hello-Agents）按工程生命周期组织，并把上下文工程和工具设计升格成独立章节（[Anthropic-BEA]、[Anthropic-CE]、[OpenAI-Guide]、[HA]、[Weng]）。

- 第一层 模型基础
  - 节点 1 LLM 基础（Token / 上下文窗口 / 幻觉 / 采样参数 / KV Cache / 量化 / 部署；应用岗只做基础覆盖，见第 6 节）
  - 节点 2 Prompt 工程与指令（System / Few-shot / CoT、版本管理、Prompt vs Context vs Harness 三层）
  - 节点 3 结构化输出（Structured Output vs Function Calling、解析失败降级、应用契约）
- 第二层 Agent 内核
  - 节点 4 工具调用与协议（Function Calling 原理、MCP / Skills / A2A、工具路由、工具失败、Tool Schema、沙箱）
  - 节点 5 Agent 循环与编排（Agent vs Workflow、ReAct / Plan-and-Execute / Reflection、停止条件、Harness、框架 vs 手搓、Claude Code 机制）
  - 节点 6 多 Agent（主子通信、异常、隔离、冲突、是否真的更有效）
  - 节点 7 记忆与上下文工程（长短期记忆、Context vs Memory、压缩策略、分层上下文）
- 第三层 围绕模型的工程
  - 节点 8 RAG（全流程、vs 微调 / 长上下文 / grep、切块、混合检索与 Rerank、Query 改写、更新与冲突、权限、评测）
  - 节点 9 评测与可观测（评测集设计、LLM-as-judge 校准、回归门禁、指标口径、Trace）
  - 节点 10 可靠性与安全（死循环、Prompt Injection、高危操作与 HITL、幂等与断点恢复、guardrails）
- 第四层 生产与人
  - 节点 11 成本与生产工程（Token 治理、预算、路由与缓存、推理服务、流式、限流、网关）
  - 节点 12 行为与 AI 编程习惯（项目真实性、AI 工具使用、Vibe vs Spec、行业观点、客户沟通）
- 系统设计题和手撕题是题型不是节点，分别见第 4 节和第 3 节

## 2. 逐节点题库

### 2.1 节点 1 LLM 基础

- [概念·高] Token、上下文窗口、上下文腐化是什么，怎么影响成本和效果（字节 Agent 开发四面原题；英文版"超出 context window 会坏什么、生产里怎么处理"）（[KM-BD]、[Aced-45]、[LDS-50]、[GH-Shekhar]）
- [概念·高] 幻觉怎么产生、Agent 场景怎么缓解（Anthropic 变体："模型在高风险场景给出自信但错误的答案，怎么排查和缓解" -> 先建评测集量 confident-wrong 率，再分检索缺口 / 校准 / Prompt 三类根因，再加引用、教弃答、加验证、路人工）（[KM-BD]、[XL]、[ADG]、[GH-Shekhar]、[LDS-50]、[Aced-45]）
- [概念·中] Temperature / Top-P / Top-K 各是什么、Agent 场景怎么调；Temperature=0 为什么同输入还不同输出（[XL]、[ADG]、[KK]、[Aced-45]）
- [概念·中] KV Cache 和 Prompt Caching 原理、怎么提高命中率（字节 Agent 实习一面、美团 Agent 一面都问；Cursor 问 KV-cache 跨按键复用）（[XL]、[NC-BD2]、[KK]、[LDS-50]、[TI-Cursor]）
- [概念·中] 为什么长上下文会 Lost in the Middle、窗口越大越好吗（[XL]、[LDS-50]）
- [概念·低] 量化 INT8 / INT4 / AWQ / GPTQ 怎么选、vLLM / TGI / llama.cpp / SGLang 怎么选（[XL]、[Aced-45]）
- [概念·低] Embedding 是什么、语义相似度怎么支撑检索（[Aced-45]、[PromptLayer]）
- 海外面试官默认你"transformer 烂熟"，开场就跳到 RAG 取舍、Agent 设计、评测策略和生产问题（[Reddit-LLMDevs]）

### 2.2 节点 2 Prompt 工程与指令

- [概念·高] Prompt / RAG / 微调怎么选、微调什么时候真的有用（英文 6 个来源，中文 5 个来源，两边都是最高频概念题之一）（[Aced-45]、[GH-Shekhar]、[PromptLayer]、[LDS-50]、[TI-MLE]、[XL]、[ML265]、[KM-BD]）
- [概念·高] CoT 是什么、为什么有效、缺点和局限；self-consistency（[KM-BD]、[XL]、[GH-Shekhar]、[TI-Agents]）
- [概念·中] System Prompt / Few-shot / CoT 各起什么作用；为什么同一件事换个说法效果差十倍；怎么写好 Prompt（[KM-BD]、[XL]、[CSDN100]）
- [概念·中] Prompt 版本管理、Prompt A/B 怎么做（[GH-Shekhar]、[LDS-50]、[TI-MLE]、[KK]）
- [概念·中·2026 新] Prompt Engineering、Context Engineering、Harness Engineering 三者的区别（钉钉、百度 Agent 研发一面 2026-08-18）（[ML155]、[KK]）
- [概念·低] Cursor / Claude Code 的 System Prompt 怎么写（小红书 2025-10 二面）（[KK]）
- [Dbg·低] 模型不听指令、输出格式不稳怎么办（[GH-Shekhar]）
- 答题锚点：Anthropic 把 System Prompt 的"高度"定义为介于硬编码逻辑和模糊指导之间，少量多样的典型示例好过边角案例清单；OpenAI 建议把现有 SOP 文档拆成清晰步骤并写明边角情况（[Anthropic-CE]、[OpenAI-Guide]）

### 2.3 节点 3 结构化输出

- [概念·中] Structured Output 和 Function Calling 的区别、各用在哪（[GH-Shekhar]、[LDS-50]、[KM-BD]）
- [概念·中] JSON 解析失败怎么降级；Function Call 和"Prompt + 正则解析"的区别（百度、小红书）（[ML265]、[KM]、[CSDN100]）
- [设计·低] 把 LLM 放在可靠的应用契约后面（定义 schema、每次响应都校验、处理拒答和缺字段、超时 + 有界重试、prompt / model / schema / 参数一起打版本、副作用前先验计算和权限）（[PracHub]）
- 2026 中文一手面经里结构化输出几乎不单独成题，被"工具调用返回结构怎么设计"吸收（见 2.4）（[ML155]、[XL]、[NC-BD2]）
- 面试官眼里的反模式：评测没设计就先说"让模型输出 JSON" -> 说明你不知道系统什么时候是错的（[TianPan]）

### 2.4 节点 4 工具调用、MCP、Skills、协议

- [概念·高] Function Calling 原理、LLM 怎么学会调外部工具、这个能力怎么训练出来的（答题骨架：开发者给 schema -> 模型产出结构化调用 -> 应用执行 -> 模型写最终回答）（[XL]、[NC-BDFW]、[ML155]、[GH-Shekhar]、[TI-Agents]）
- [概念·高] MCP 和 Function Calling 的本质区别（中文 ≥7 来源；美团 2025 后端问 MCP 的 SSE / stdio）（[KM]、[ML155]、[GH-Shekhar]、[LDS-50]、[KK]、[ADG]）
- [概念·高·2026 新] MCP vs Skills vs Function Calling 三者区别（ML155 里标注公司最多的一题：TikTok、京东、字节、美团、腾讯、阿里云、小红书）；Skills 的渐进式披露、为什么省 Token、懒加载；Skill 和 Tool 的边界（[ML155]、[XL]、[KM-BD]、[KK]、[KMI]）
- [概念·中] A2A 是什么、和 MCP 的区别（[XL]、[NC-BDFW]、[KK]）
- [概念·中] MCP 用什么通信方式；WebSocket vs SSE 的区别和局限；WebRTC vs WebSocket 在对话流里的差异（[XL]、[KK]、[Educative]）
- [设计·高] 注册 30 个工具后选工具准确率下降怎么办；Tool Routing 怎么做；工具多了提示词过长怎么办；工具返回过大超出窗口怎么办（[ADG]、[XL]、[NC-BD2]、[KMI]、[KK]、[GH-Shekhar]、[TI-Agents]）
- [Dbg·高] 工具调用格式非法、参数错、超时、返回部分结果时怎么容错（重试、换工具、告知用户；高风险动作先确认；超时与异常隔离）（[XL]、[ML155]、[KK]、[LDS-50]、[TI-Agents]、[GH-Shekhar]）
- [Dbg·中] 工具幻觉：虚构工具能力、传错输入、没执行却声称完成（"任务幻觉"）-> 每次调用对照注册表校验（[GH-Shekhar]、[TI-Agents]、[XL]、[KM-BD]）
- [设计·中] 工具调用返回结构 / Tool Schema 怎么设计（美团 Agent 实习、淘宝闪购）（[KM-MT]、[KK]）
- [概念·中] Agent 的 thinking 怎么决定调工具还是直接回复（腾讯 2026-04-21 一面原题）（[NC-TXBD]、[GH-Shekhar]、[LDS-50]）
- [概念·低] 同时触发多个 Function Call 怎么处理；LLM 自己执行 Function Call 吗；Qwen 工具调用适配与空调用（[KM]、[KK]）
- [概念·低·2026 新] 接工具选 CLI 还是 MCP、为什么把工具执行封装成 CLI、代码执行沙箱怎么设计（[XL]、[KMI]）
- [手撕·中] 写一个 function calling handler / tool-use orchestrator（Anthropic 电面题之一，未经核实）（[GH-Shekhar]、[Perspective]）
- 答题锚点：Anthropic 工具设计原则（把多步操作合并成一个工具、命名空间、返回名字而非 UUID、分页 / 截断、Claude Code 工具返回上限 25,000 token、错误信息要可执行、参数名无歧义如 user_id 而非 user）；OpenAI 的拆分标准是工具重叠度不是数量（有的 Agent 跑 15+ 个工具没事，有的 10 个重叠工具就乱）；MCP 架构 host / client / server，服务端原语 tools / resources / prompts，传输 stdio 与 Streamable HTTP（[Anthropic-Tools]、[OpenAI-Guide]、[MCP-Arch]）

### 2.5 节点 5 Agent 循环、规划、Harness、框架

- [概念·高] LLM、Agent、Workflow、Tools 的区别；Agent 由哪些核心组件构成（中文 ≥4 来源，英文 3 来源）（[KM]、[ML155]、[XL]、[KK]、[NC-BDFW]、[NC-TXBD]、[GH-Shekhar]、[TI-Agents]、[AV-30]）
- [概念·高] 详细解释 ReAct、由哪几部分组成、优缺点（中文 ≥8 来源，腾讯二面 2026 原题"ReAct Agent 由哪几部分组成"）（[NC-BDFW]、[JJ]、[ML155]、[GH-Shekhar]、[LDS-50]、[TI-Agents]）
- [概念·高] ReAct vs Plan-and-Execute vs Reflection 怎么选（ReAct 贪心逐步、Plan 适合长流程但前步失败计划就断要 re-plan、ToT 每步多次调用成本高）（[XL]、[ML155]、[NC-BD2]、[KK]、[GH-Shekhar]、[TI-Agents]）
- [概念·中] 赋予 LLM 规划能力的主流方法（CoT / ReAct / ToT / GoT / Plan-and-Execute / Reflexion）；反思机制是什么、怎么提升效果（[NC-BDFW]、[XL]、[GH-Shekhar]、[TI-Agents]）
- [概念·中] 单次提示词 / Workflow / Agent 三种方案怎么选型；什么时候不该做 Agent（步骤已知用 Workflow，只有灵活规划和选工具值得付成本与不确定性时才上 Agent，并定义 schema、最小权限、停止条件、预算、审批点）（[ML155]、[PracHub]、[AV-30]）
- [概念·中] Agent Loop 怎么决定停、停止条件、长任务 Early Stop（[GH-Shekhar]、[TI-Agents]、[KK]）
- [概念·高·2026 新] Agent Harness 是什么、怎么设计一个完整的 Harness、Agent vs Harness、Harness 概念为什么近期走红（美团 2026-08-25、字节算法三面；ML155 称 Harness 与运行时、评估与安全兜底、多 Agent 编排是 2026 下半年新增的三大方向）（[XL]、[ML155]、[KK]）
- [概念·中·2026 新] Claude Code 的上下文管理思路、数据流、防重复 Tool Call；为什么已有 Claude Code 还要自研 Coding Agent；Claude Code / Harness / OpenClaw / Hermes 源码（快手、蔚来、百度 2026-08-21、腾讯 2026-04-04、蚂蚁 2026-05-20、字节社招）（[ML155]、[KK]）
- [概念·中] 为什么有时手搓 Agent 而不用成熟框架；LangGraph vs 自研（[XL]、[KK]）
- [概念·中] 框架题：用过哪些框架、怎么选；LangChain vs LangGraph；LangChain vs LlamaIndex；LangGraph 状态快照；Java 生态 Spring AI vs LangChain4j（京东、字节、快手）（[NC-BDFW]、[XL]、[ML155]）
  - 2025 题库仍以 LangChain 组件名词为主（LCEL、Output Parser、Text Splitter），2026 一手面经几乎不再问组件，转向 Harness、压缩、grep vs RAG 这类运行时问题（[CSDN100]、[ML155]、[NC-BD2]）
- [Dbg·中] 长任务跑偏、跑了几小时后自信地做错事；跨天跨周的长任务状态怎么维护；中途崩溃能否恢复（[GH-Shekhar]、[LDS-50]、[KM-BD]）
- [手撕·中·2026 新] 脱离框架手写最小 ReAct Agent / 带 step budget 的最小 agent loop（ML155 称 2026 下半年新出现的高频题；一手中文帖里尚未见到，推测是少数团队做法）（[ML155]、[ML265]、[ADG]、[GH-Shekhar]、[Reddit-LangChain]）
  - 从零实现要点：一个 while 循环把 thought-action-observation 追加进 prompt 直到停止（约 60 行）；System Prompt 钉死输出格式，正则解析会在模型漂移时失败所以优先结构化 JSON；最大迭代上限；用显式 submit 工具终止因为模型会"无意中停止调工具"（[DEV-ReAct]、[Inspect]、[Inngest]）
- 答题锚点：Anthropic 五种 Workflow 模式（prompt chaining、routing、parallelization、orchestrator-workers、evaluator-optimizer）+ 自主 Agent；OpenAI 区分"由 LLM 编排"与"由代码编排"并建议混用；12-Factor 的"own your control flow / own your context window"；LangChain 把 Agent 定义为"模型在循环里调工具直到任务完成"（[Anthropic-BEA]、[OpenAI-SDK-Orch]、[12factor]、[LangChain-Agents]）

### 2.6 节点 6 多 Agent

- [设计·高] 主 Agent 与子 Agent 的通信链路怎么设计、异常怎么处理；子 Agent 超时、失联、并发修改同一文件怎么办；主 Agent 怎么判断子 Agent 完成并回收结果（字节四面、字节实习一面、蔚来、腾讯、MiniMax）（[KM-BD]、[NC-BD2]、[XL]、[ML155]、[KK]、[GH-Shekhar]）
- [概念·高] 多 Agent vs 单 Agent 的优势和复杂性、什么时候多 Agent 会崩（[NC-BDFW]、[ML155]、[GH-Shekhar]、[LDS-50]、[AV-30]）
- [概念·中] 任务拆分阶段的错误怎么防止逐级传播；Agent 之间上下文 / 权限 / 存储怎么隔离；子任务里冒出新任务怎么流转和审批（字节、蚂蚁、腾讯广告）（[ML155]）
- [概念·中] Subagent 幻觉、A2A 死循环、共享状态（字节社招、腾讯 2026-04-04）（[KK]）
- [概念·中] 主 Agent 和子 Agent 是否该用同等规模的模型；多 Agent 是否真的提升效率、怎么评估（阿里、滴滴、字节）（[ML155]）
- [概念·中] 编排和路由怎么实现；混合路由和限流器在多 Agent 系统里为什么重要（[GH-Shekhar]、[AV-30]、[KM-BD]）
- [设计·中] 现场设计一个多 Agent 系统并写各 Agent 的 Prompt（腾讯 2026-04-22 一面）；多个 Agent 协作产出带引用的研究报告（[NC-TXBD]、[GH-Shekhar]、[PromptLayer]）
- [Dbg·低] 链路里一个模型挂了，整条多模型 pipeline 怎么处理（[GH-Shekhar]）
- 英文来源里多 Agent 题在题库里很多，但带公司归属的真题几乎没有，海外真题偏单 Agent 客服设计（[GH-Shekhar]、[Aced-45]）
- 答题锚点：OpenAI 先把单 Agent 做到极限，只在逻辑分支太多或工具重叠时拆，manager（agents as tools，由一个 Agent 拥有最终答案）vs handoff（专家直接回复）；Anthropic 多 Agent 研究系统比单 Opus 4 高 90.2%，但 Token 是聊天的 15 倍，适合可并行的研究任务、不太适合 coding；子 Agent 要有隔离上下文（[OpenAI-Guide]、[OpenAI-SDK-Orch]、[Anthropic-MA]）

### 2.7 节点 7 记忆与上下文工程

- [设计·高] 短期 / 长期记忆怎么设计，记忆怎么存、粒度多大、怎么用、数据库怎么选（中文 ≥7 来源；英文版"四种记忆类型怎么存"）（[NC-BDFW]、[XL]、[ML155]、[NC-BD2]、[KM-BD]、[KK]、[GH-Shekhar]、[LDS-50]、[AV-30]）
- [概念·高] 当前对话上下文 vs 长期记忆；Context vs Memory；长期记忆和 RAG 的区别；RAG 算不算 Agent 记忆的一部分（字节实习一面、快手 Data Agent、拼多多、阿里千问 / 通义）（[NC-BD2]、[KK]、[ML155]、[KM]）
- [设计·高] 上下文超窗口怎么办：滑动窗口和动态摘要各有什么坑、压缩时保留什么、压缩后怎么不忘早先需求、压缩阈值怎么定、压缩放在链路哪一步、工具结果怎么卸载（美团、字节、腾讯、小红书、蔚来；英文版"聊到第 10 轮丢上下文"）（[KM-MT]、[NC-BD2]、[KM-BD]、[XL]、[ML155]、[GH-Shekhar]、[TI-Agents]）
- [概念·中·2026 新] 三级压缩 / 三层记忆 / 分层 Context（字节 2026-06-04、07-01、09-19，腾讯 04-04，百度 10-20）（[KK]、[KMI]）
- [概念·中] 记忆管理和上下文压缩为什么可以交给小模型（字节实习一面）（[NC-BD2]）
- [设计·中] 多轮对话状态怎么管理、怎么防跑偏并支持中断恢复；长对话摘要压缩怎么实现；对话历史感知改写怎么做（[XL]、[KM-QW]）
- [设计·低] 多用户 × 多角色记忆、跨周 Memory、Markdown Memory、Rules vs Memory（拼多多、淘宝闪购、蚂蚁财富、MiniMax）（[KK]）
- [概念·中] 记忆类型 semantic / episodic / procedural，更新走 hot-path 还是后台（[DLAI-Memory]、[GH-Shekhar]）
- [手撕·中] 实现对话记忆系统（滑动窗口 / 摘要 / buffer）；上下文压缩与滑动窗口摘要（[GH-Shekhar]、[ML155]、[ML265]）
- [手撕·低] Cursor：给定光标位置从代码库选最相关片段喂给 Prompt，比较 embedding 检索 vs 依赖图遍历 vs 最近编辑启发式，论证多少上下文值得它的延迟（[TI-Cursor]）
- 答题锚点：Anthropic 把上下文当有限资源（context rot、attention budget），目标是"最小的高信号 token 集合"，长任务三招按任务形状选：compaction（长来回）、结构化笔记（里程碑式迭代）、子 Agent（并行探索）；LangGraph 区分 checkpointer（线程内短期）与 store（跨线程长期），生产要用 PostgresSaver（[Anthropic-CE]、[LangGraph-Persist]）

### 2.8 节点 8 RAG

- [概念·高] RAG 完整流程；相比微调解决了什么问题、各自优劣（ML265 称"几乎必问"；Sierra 原题"Explain how RAG works"；英文版"200 页 PDF + 一个问题，从提交到回答发生了什么"，第二层追问 chunk 大小为什么、k 变大会坏什么、什么时候不该用检索）（[XL]、[ML265]、[KM-BD]、[KK]、[CSDN100]、[Aced-45]、[GH-Shekhar]、[TI-Applied]、[AV-30]）
- [概念·高·2026 新] 上下文窗口这么大为什么还要 RAG；十篇以上文档或大代码仓库，RAG 和 Agent 自己 grep 各有什么优劣（美团 2026-10-21 原题、美团 08-25、蚂蚁 05-20）（[KM-MT]、[XL]、[KK]、[GH-Shekhar]、[TI-Applied]、[DataTalks]）
- [概念·高] 切块策略、粒度多大、怎么规避语义被切断；Markdown 没有标题怎么切；父子索引解决什么问题、父块子块多大为什么；项目里的 CSV 怎么解析（[XL]、[KM-MT]、[KM-QW]、[ML265]、[KK]、[LDS-50]、[TI-Applied]）
- [概念·中] Embedding 模型怎么选怎么评、结构和输出维度；向量库对比选型、数据量级、性能瓶颈（Milvus 美团 2026-08-25）（[XL]、[KM-BD]、[ADG]、[KK]、[PromptLayer]）
- [概念·高] 向量 + BM25 双路检索好处和权重怎么分；关键词检索和向量检索各自缺点；一两百字的 Chunk 和三五个字的 Query 怎么比；RRF 融合；Rerank 为什么要加、Rerank 变差怎么诊断、NDCG（阿里千问、美团、腾讯、蚂蚁国际）（[KM-QW]、[KM-MT]、[XL]、[ML265]、[KK]、[LDS-50]、[ADG]）
- [概念·中] Query 改写目的和做法；多跳检索怎么实现；长 Query 除了压缩还怎么处理（[XL]、[KM-QW]、[KM-MT]）
- [概念·中] 知识库持续更新旧向量怎么失效、不重建全量怎么办、不停机热更新；召回结果互相矛盾谁说了算、不同来源冲突信谁（参考答案：模型解释证据，治理规则决定哪些证据合格）（[XL]、[KM-MT]、[NC-TXBD]、[ADG]、[Educative]）
- [概念·中] 文档访问控制怎么实现、ACL 先过滤还是后过滤；私人 RAG 为什么也要做访问控制；PII 脱敏是什么（海外版：OpenAI / Google / Lyft 都问过"设计一个尊重权限、给引用、该弃答时弃答的内部文档搜索 Agent"）（[KM-QW]、[ML265]、[PracHub]、[Dev-to]）
- [概念·高] 怎么量化 RAG 效果：Recall@K / Precision@K 取舍、MRR、NDCG，检索和生成分开评，消融（字节 2025 四面"Precision / Recall 怎么算"，Reddit 候选人"栽在检索评测题上"）（[XL]、[JJ]、[KK]、[ADG]、[LDS-50]、[TI-Applied]、[Aced-45]、[Reddit-LLMDevs]）
- [Dbg·高] 召回不准怎么优化、幻觉怎么治理；文档明明在却答错怎么 debug（按摄入 / 权限过滤 / 切块 / 检索 / 重排 / 拼装 / 生成逐段拆，记下检索到的 doc id 和分数，把失败分成"没索引 / 被过滤 / 排太低 / 被截断 / 没用上"，加进回归集，一次只改一个组件）；怎么发现剩余 1%–2% 的幻觉（[JJ]、[KK]、[PracHub]、[GH-Shekhar]、[Dev-to]）
- [概念·中] 更复杂的 RAG 范式：Agentic RAG 和传统 RAG 的区别（Agent 决定何时检索什么、改写、判断是否够、多源）；Graph RAG、什么场景用图数据库增强向量检索（[XL]、[ML265]、[ADG]、[LDS-50]、[TI-Agents]）
- [概念·低] 引用与溯源怎么实现；metadata 过滤的作用（[GH-Shekhar]）

### 2.9 节点 9 评测与可观测

- [设计·高] 怎么评估一个 Agent 好不好、评测集和指标怎么设计、评估体系怎么搭、非确定性系统怎么测（Shopee、小红书、阿里；英文版期望 task completion rate、step efficiency、tool accuracy、用户满意度，staff 级 JD 点名 SWE-bench / GAIA / τ-bench）（[XL]、[ML155]、[ADG]、[GH-Shekhar]、[TI-Agents]、[AV-30]、[AgentJD-Staff]）
- [概念·高] 没有唯一正确答案的 LLM 功能怎么评；LLM-as-judge 能不能信、失败模式是什么（偏好长答案、成对比较偏好先出现的、偏好自家模型，先和人工标注对齐；TianPan 建议 75–90% 一致率后才让 judge 把关发布）（[Aced-45]、[PracHub]、[GH-Shekhar]、[TI-Applied]、[TianPan]、[TI-LangChain]）
- [设计·高] 改了 Prompt 或换了模型怎么抓回归、能不能发布（任务来自需求 + 线上失败 + 高危场景，可验证结果用确定性 grader，主观用 rubric 并与人工校准，重复多次试验，按失败切片，回归门禁同时带延迟 / 成本 / 安全约束；Decagon 追问"resolved"怎么定义，是解决了还是只是没转人工）（[Aced-45]、[GH-Shekhar]、[PracHub]、[TI-Applied]、[TI-Decagon]）
- [深挖·高] 指标口径追问：幻觉率怎么定义怎么算；你说准确率提升，基线、样本量、统计窗口和归因是什么；效果提升用哪个测试集算；评测集过拟合（字节 2026-06-04）（[ML155]、[KK]）
- [概念·中] 幻觉怎么检测度量、生产 RAG 里怎么量（[GH-Shekhar]、[LDS-50]、[Aced-45]）
- [设计·中] 线上 Agent 延迟明显升高怎么用 Trace 定位；上线要什么可观测基础设施、要记哪些指标（Sierra take-home 答辩固定问题）（[XL]、[LDS-50]、[Aced-Sierra]、[KMI]）
- [概念·中] 轨迹评估 vs 结果评估（exact match / in-order / precision-recall vs 终态 autorater）；pass@k vs pass^k；capability eval 起步低而 regression eval 保持 ~100%（[Anthropic-Evals]、[Google-Companion]、[TI-LangChain]）
- [概念·低] 多轮对话质量怎么评；持续评估怎么做；SWE-bench 通过率为什么会误导；代码补全是否有用怎么评（Cursor：看建议几分钟后是否还留在文件里）（[GH-Shekhar]、[TI-Cursor]）
- [设计·低·面试官提议] 40 分钟评测设计轮（给"客服工单分队列 + 置信度"的 spec，不碰 Prompt，按未点名失败模式、rubric 精度"两位 grader 会一致"、线上分布覆盖与刷新节奏、judge 与人工校准打分）；生产 trace 回放轮（哪个评测扩展本可以抓到这个问题）（[TianPan]）
- [手撕·中] 评测类 take-home：一个 Prompt + 约 30 条输入输出对，建 faithfulness / relevance 自动评测并对齐给定人工标签（期望确定性检查 exact match / regex / 引用来源是否存在 + 其余用 LLM judge）；约 50 篇 Markdown 建 QA 系统 + 20 题评测集报 recall@5 和 faithfulness（[TI-Applied]）
  - Hamel Husain 点名的评测面试陷阱：generic metrics、composite scores、hill-climbing；报告结构：先挖数据里的坑和噪声 -> 带基线的实验 -> 结果与取舍 -> 局限与有礼貌的 push-back -> 下一步（[Hamel]）
- 三位面试官（PromptLayer、TianPan、Hamel）一致把评测判断当最强信号，Hamel 称 evals 常是"weed out"环节；LangChain 2025 年底 1,340 人调查里 89% 有可观测但只有 52% 有评测 -> 问"你怎么评"比问"你记什么日志"更有区分度（[PromptLayer]、[TianPan]、[Hamel]、[LangChain-Report]）
- 答题锚点：Anthropic 评测词汇 task / trial / grader / transcript / outcome / harness / suite；grader 分 code / model / human；从 20–50 个任务起步、评结果不评步骤、judge 用人工校准、允许"Unknown"（[Anthropic-Evals]、[Anthropic-MA]）

### 2.10 节点 10 可靠性、guardrail、安全、HITL

- [Dbg·高] Agent 陷入死循环怎么办（KM 标"面试必考题"；字节 2026 校招"调了三个工具就死循环，异常处理写在哪"；路径震荡、重复调用；百度"防重复 Tool Call"；英文版"This agent loops forever on some inputs, find out why and fix it"；期望：迭代上限约 10、检测重复动作、超时、每请求花费上限、多步 trace、对固定用例回放）（[KM]、[JJ]、[XL]、[ML155]、[NC-BD2]、[KK]、[GH-Shekhar]、[TI-Applied]、[TI-Agents]）
- [概念·高] Prompt Injection 是什么、直接 vs 间接注入、怎么防（KM 标"高频考点"；钉钉、小红书、腾讯 2026-05-21、快手 Data Agent；海外期望：检索内容和工具输出一律视为不可信，授权在检索和动作时做、"Prompt 指令不等于授权"，工具 allowlist，输出校验，红队测间接注入 / 跨租户检索 / 外泄 / 不安全重试）（[KM]、[ML155]、[KK]、[CSDN100]、[ADG]、[LDS-50]、[PracHub]、[Educative]、[TI-LangChain]）
- [设计·高] 高危操作：Agent 操作数据库怎么保证不误删、不越权、不泄敏感数据；资金类操作怎么限制；什么情况下要人工确认；HITL 暂停与恢复；风险分级 / 熔断 / 沙箱 / 审计；写操作边界（腾讯 2026-04-24 一手原题"跨境汇款流程里 Agent 超时 / 失败如何保证资金安全"；淘宝闪购、小红书、蔚来、蚂蚁、唯品会；英文版"你的 Agent 删了生产库怎么防不可逆操作"）（[KM]、[XL]、[ML155]、[KK]、[NC-TXBD]、[GH-Shekhar]、[TI-Agents]、[PracHub]）
- [设计·高] 断点恢复与幂等：进程在第七步崩溃怎么从 Checkpoint 恢复而不重复副作用；Agent 失败或中断怎么处理、重试是否安全（腾讯 2026-04-24、字节、京东）（[KK]、[ML155]、[NC-TXBD]、[TI-LangChain]）
  - 幂等答题要点：区分"工作做了但响应丢了"的可靠性重试（返回缓存结果）和"输出没过校验"的采样重试（新 key、把上次错误附进 prompt）；幂等 key 按有外部副作用的逻辑单元而非每次推理；优先只读工具、写操作推到流程末尾；不可逆动作前先记意图；saga 模式区分"跑过"和"成功"；事故期间重试风暴可占意外推理开销的 30–60%（[TianPan-Idem]）
- [概念·中] 上下文漂移怎么解决、长任务怎么不偏离原始目标；"任务幻觉"没执行却声称完成（[KM-BD]、[XL]）
- [概念·中] 工具调用的安全问题（Key 泄露、敏感信息暴露）；怎么确保 Agent 行为安全可控；信任边界、最小权限（[KM-BD]、[NC-BDFW]、[PracHub]）
- [概念·中] 输入 / 输出 / 工具三层 guardrail 怎么实现；写一个检查跑题和 PII 泄露的输出 guardrail（[GH-Shekhar]、[OpenAI-SDK-Guard]）
- [Dbg·中] 生产环境 Agent 踩过什么坑；四类典型故障；LLM API 失败怎么感知定位、限流之外的异常；非 AI fallback（KM、字节、拼多多、京东告警 Agent）（[KM]、[ML155]、[KK]）
- [设计·低] 模型或 Prompt 改动后出事故怎么响应（回滚 / 禁用 / 人工审，保留 trace 和版本 id，对准确配置复现，失败切片进回归集，带中止阈值的 canary；AI 事故可能没有 500 错误，所以要 kill switch、限流、快速回滚）（[PracHub]、[Educative]）
- [行为·低] Anthropic 招聘官轮会问 RSP（Responsible Scaling Policy），泛泛的"负责任 AI"答案过不了；Constitutional AI vs RLHF、reward hacking 这类实验室安全概念（[Perspective]、[LDS-50]、[Aced-45]）
- 答题锚点：OpenAI 分层 guardrail（相关性分类器、安全分类器、PII 过滤、审核、工具风险分级 low / med / high 按只读 vs 写、可逆性、权限、资金影响，规则类，输出校验）+ 人工介入触发条件（失败超阈值、高风险动作）；Model Spec 的"默认忽略不可信数据"和"商定的自主范围"（哪些子目标可追、可接受的副作用、何时停下来问）（[OpenAI-Guide]、[ModelSpec]）

### 2.11 节点 11 成本、延迟、生产工程

- [Dbg·高] Agent Token 消耗很大怎么优化；一次对话花了 10 万 Token 怎么办；成本太高怎么降又不掉质量；多任务多 Agent 怎么权衡效率和准确性（字节四面、字节番茄小说"Token 治理"、飞猪 / 百度 / 腾讯）（[KM]、[KM-BD]、[ADG]、[ML265]、[KMI]、[GH-Shekhar]、[LDS-50]、[TI-MLE]）
- [设计·中] 长任务怎么管 Token；超预算怎么强制；按任务 token 预算与每完成一个 workflow 的成本（Anthropic 电面"token-budget allocator"，复杂任务 20–50 步）（[GH-Shekhar]、[TI-Agents]、[Perspective]、[AgentJD-Staff]）
- [Dbg·中] Agent 太慢怎么提速；峰值延迟抖动怎么稳；延迟 vs 质量怎么设计（800 ms 端到端目标、小模型做路由、语义缓存、并行工具调用）；质量上去了但延迟成本不可接受怎么办（先定延迟目标和成本单位如每解决一个任务的成本，按组件拆 trace 看尾延迟，在同一评测集上试最小干预）（[GH-Shekhar]、[TI-Applied]、[PracHub]）
- [行为·低] 权衡题：高级模型高 3 分但 6 倍成本，决定前问什么、最小实验是什么；一次工具调用修掉 40% 失败但给语音产品加 800 ms（[TianPan]）
- [概念·中] 大模型网关解决什么问题；统一模型服务架构与模型选型；模型路由（常规请求走便宜模型，KDnuggets 称 60–80% 请求是常规、可省 40–70%）；语义缓存、Prompt 压缩（[XL]、[ML265]、[GH-Shekhar]、[KDn]）
- [概念·中] 为什么先用小模型做初步召回（车载场景）；自托管 vs API；十几万请求怎么扩容；QPS 压测（[NC-BD2]、[KK]、[KMI]）
- [概念·中] 推理服务：continuous batching 为什么取代 static batching、PagedAttention / vLLM、speculative decoding 什么时候不划算、Prefill / Decode、KV Cache 内存（[LDS-50]、[TI-Cursor]、[KK]、[Educative]）
- [概念·中] 流式：SSE vs WebSocket 怎么选（单向 token 流用 SSE 对代理 / CDN 友好，需要取消 / 工具调用 / 进度的双向控制用 WebSocket）、断流怎么续、心跳与 backpressure、部分响应怎么记日志（[XL]、[Educative]、[TI-Decagon]）
- [概念·中] 限流与速率限制怎么处理；admission control、按租户 / 模型等级 / 地区路由、过载时谁优先、什么时候拒收（[CSDN100]、[GH-Shekhar]、[Educative]）
- [概念·中] Trace 要记什么：prompt hash（不记原文）、模型版本、检索元数据、工具 trace、安全决策、延迟、每请求成本；脱敏、留存、采样（[Educative]、[KDn]、[TI-LangChain]）
- [概念·低] 协程 / 线程 / 进程（美团 Agent 一面、腾讯）；后端团队会把 Java / MySQL / Redis / MQ 八股与 Agent 题各占一半（腾讯 2026-04-21 分库分表 + Redis Lua 锁 + RocketMQ 再问 Agent；蚂蚁财富 ACID + MQ + 限流）（[KK]、[NC-TXBD]）
- 答题锚点：Huyen 的架构演进顺序 增强上下文 -> 加 guardrail -> 加模型路由和网关 -> 用缓存降延迟 -> 加 Agent 模式；一次模型调用成本约为一次数据库读的 10,000 倍、耗时约 1,000 倍，所以容量按 tokens/s 而非 requests/s 估（[Huyen]、[DesignGurus]、[Educative]）

### 2.12 节点 12 行为、产品感、AI 编程习惯

- [行为·高] 系统上线了吗、日均多少请求、有没有真实用户、为什么没上线、项目是不是自发做的、是否自学 AI（美团 2026-10-21 第 16 题、腾讯 2026-04-21 / 04-24、字节 2025 四面）（[KM-MT]、[NC-TXBD]、[KK]）
- [行为·高] 平时用什么 AI 工具、界面类 AI 产品用过哪些；常用 AI 编程工具及使用方式；现场演示自己搭的 Agent；walk through 你的 Claude Code 工作流；比较 Cursor / Windsurf / Zed / Claude Code / Copilot 各赢在哪（[KM-QW]、[ML155]、[PromptLayer]、[Gauntlet]、[TI-Cursor]）
- [行为·中] Vibe Coding 和 Spec 驱动开发的区别；Coding Agent 怎么在大仓库定位代码；怎么审查看起来合理实际错误的 AI 代码；AI Coding 怎么避免改坏历史功能；Plan 模式与 Agent 模式区别；Skill 的使用与效果验证（美团 2026-08-25、字节 2026-06-04、阿里、小红书、快手）（[XL]、[KK]、[ML155]）
- [行为·中] 结合开发经历谈传统 Web 应用和 AI Agent 应用有什么不同（字节四面）（[KM-BD]）
- [行为·中] 对框架与 Skill 概念快速迭代的看法；To C 和 To B 的 Agent 哪个先爆发；Agent 商业化成本走势；最近关注的论文或进展；未来一年关键的 GenAI 技能；"Will AGI kill us all"（教科书式答案是红旗）（[ML155]、[PromptLayer]、[Aced-45]）
- [行为·中] Walk me through an AI system you shipped（用户问题、你负责什么、基线、架构决策、怎么评、上线护栏、拿到真实反馈后改了什么，把团队成果和个人贡献分开）；客户说 bot "feels dumb"先改什么（先把它变成可度量的问题）；老板要上线但证据弱你怎么说（[PracHub]、[TI-Applied]）
- [行为·中·海外] Why Anthropic / Why Sierra；把技术话题讲给非技术受众；曾自信后发现错；做过与个人价值观冲突的东西；怎么直接和客户解决问题；怎么定义 AI 功能的成功指标（Perplexity）；有没有端到端做过 Agent 系统（Glean）（[Aced-45]、[Aced-Sierra]）
- 招聘方说的强信号：主动谈"模型返回垃圾时、API 挂了时怎么办"比 happy-path demo 更有说服力；"能不能设计一个测试告诉你自己系统在变差"是最硬的区分点；强观点和失败实验 / workaround 的 tinkerer 心态被明确打分（[Gauntlet]、[PromptLayer]）

## 3. 面试轮次：国内"项目追问 + 一道 Medium"，海外"take-home + 答辩 + 客户模拟"

国内应用岗
- 通用流程：笔试或测评 -> 1–3+ 轮技术面 -> 主管面 -> HR（字节 Seed 官网 3–5 轮技术 + 1 轮 HR；字节 Agent 实习 2 轮；腾讯 WXG 一面二面各 2.5 小时 + 委员会面；华为 机试 -> 测评 -> 技术面 -> 主管面）（[KK2]、[KK]）
- 一面模板稳定：自我介绍 -> 围绕项目追问 15–20 题 -> 一道 LeetCode Medium（字节 AI Agent 实习一面 20 题最后一题判断平衡二叉树；腾讯 DFS 最大岛屿面积、两个栈实现队列并支持删中间元素；百度 前序 + 层序遍历；华为双机位 三数之和；快手 最小覆盖子串）（[NC-BD2]、[NC-TXBD]、[KK2]、[KK]、[KMI]）
  - 有没有算法题看团队：美团 Agent 日常实习 16 题全围绕项目无算法，阿里千问 AI 应用研发一面 40 分钟 15 题全围绕私人 RAG 项目无编程；字节 / 腾讯 / 百度 / 快手 / 华为有（[KM-MT]、[KM-QW]）
- 二面深到单个模块并加场景题（字节实习二面"明显问得更深"；腾讯 04-24 跨境汇款场景题）；三面 / 主管面问宏观架构和从零设计（华为主管面；字节暑期实习四面合计 22 题从 Prompt -> 输出控制 -> Agent 系统设计 -> 模型优化 -> 记忆 -> 工程落地）；系统设计"通常二面或三面"，字节追问可到 5–6 层（后者为 ADG 无出处）（[NC-BD2]、[NC-TXBD]、[KK]、[KM-BD]、[ADG]）
- 团队背景决定混考内容：后端团队（腾讯、蚂蚁、美团、京东、小红书）Java / MySQL / Redis / MQ 八股与 Agent 各半（腾讯 04-21 分库分表、深分页、隔离级别、Redis Lua 锁、RocketMQ 之后才问 Agent；腾讯 04-23 二面 Spring IoC / AOP、volatile、JVM、epoll、TIME_WAIT 之后问 Agent 与 Transformer 概述）；算法团队（百度文心一面、腾讯 04-04、快手二面、阶跃、智谱）把 PPO / GRPO 混进来（[NC-TXBD]、[KK]）
- 笔试：美团 2026 校招 大模型选择题 + 算法 + Kaggle 式 AI Coding（一候选人账 10 选择 30 分 + 1 算法 20 分 + AI Coding 50 分）；拼多多 4 道 ACM；京东 NumPy 逻辑回归 + AI Coding；阿里云 现场用 AI Coding 完成功能后人工 Review；华为 2025 ACM 风格（[KK2]、[KK]）
- 手撕题分三类：通用算法仍是主流（反转链表、链表相加、排序链表、二叉树层序、接雨水、无重复字符最长子串、最大正方形、路径总和 III、Top-K、字符串解码）；模型类归算法岗（MHA 扩展 cross-attention、MoE、numpy 手写 MHA / 位置编码）；Agent 工程类 2026 下半年才出现且"近一年权重明显上升"（脱离框架手写最小 ReAct Agent、上下文压缩 / 滑动窗口摘要、完整工具调用链路、多 Agent 消息路由与超时重试、死循环检测器"给定调用轨迹判断是否重复调用"），目前只见于二手题库（[ML265]、[ML155]、[ADG]）

海外 Agent 公司
- 通用 loop：DS&A 电面 -> 大型 take-home（造一个 Agent）-> onsite（debug + take-home 答辩 + 系统设计 + 客户 / 产品轮 + 行为）（[Aced-Sierra]、[TI-Applied]、[Perspective]）
- Anthropic Applied AI Engineer 五阶段约 4–6 周（Perspective 自述引自 Glassdoor 但无链接，未经核实）：招聘官筛（含 RSP 安全题）-> 60 分钟"LLM-adjacent" Python 电面（retrieval scorer / token-budget allocator / tool-use orchestrator，标准是"能跑且会讲失败模式"不是 LeetCode 难度）-> 3–4 小时 take-home（对一份虚构客户 brief 做一个 Claude 应用，评 shipped behavior、API hygiene、怎么处理含糊需求）-> 60–90 分钟客户模拟（面试官扮 5,000 人金融科技公司 VP："Claude 有什么 ChatGPT 做不到的，说服我"，强候选人问评估标准、过去失败的 AI 部署、合规 / 延迟 / 数据驻留约束，不开编辑器不推销）-> onsite 多租户 Claude 部署设计（越来越偏评测 harness 而非 RAG 架构）+ values + 技术深挖 + bar-raiser；techinterview 同样说客户模拟轮"悄悄淘汰很多过了 coding 的人"，Interview Query 称共 8–9 轮、最常挂在 AI 伦理与安全的 culture fit 轮（[Perspective]、[TI-Applied]、[IQ-Anthropic]）
- Sierra Agent Engineer 2–5 周：30 分钟招聘官 -> 60 分钟 coding（电子表格单元格循环引用检测，或"给订阅取消流程设计一个 agentic service"）-> take-home（给虚构客户造客服 Agent、自选要做的 feature、提供 OpenAI key，指南建议不用框架直接 model + tools 循环）-> onsite 3×60 分钟（修一个 React 应用里的几个 bug、Agent 答辩"为什么选这些 feature / 为什么这个架构 / 上线要追踪什么指标和可观测"、行为）；2026 宣布系统设计轮取代 coding screen、试点用 coding agent 改同事 PR 的 debug 轮（[Aced-Sierra]、[Aced-45]）
- Cursor：60 分钟 editor-primitive 题（语法感知编辑、处理流式 LLM 输出、文件树 diff）-> 4–8 小时 take-home（可用 AI 但"每一行都要能辩护"）-> onsite 3–5 轮（算法 + 应用 coding 多文件编辑协调 / 上下文检索 / 流式编辑应用、系统设计"设计多文件编辑的 Cursor agent 带验证和回滚"、ML 深挖、产品、行为）；另有付费约 8 小时真实代码库项目；"用 AI 用得差是最快的淘汰方式"（[TI-Cursor]、[Exponent-Cursor]）
- Decagon：60 分钟结对（解析 tool-call schema、处理流式响应、带 backoff 的重试、协调并发请求，"结构干净和合理错误处理比巧技重要"）；设计题 自动工单解决 Agent、知识摄入与检索、评测框架（[TI-Decagon]）
- LangChain：60 分钟中等偏难题 agent loop / token 流转结构化事件 / 从日志流建 trace 树 / 在数据集上跑 chain 的评测 harness（[TI-LangChain]）
- 格式趋势：take-home 越来越允许甚至期待用 AI，live round 关掉 AI 补全；用了 AI 但解释不了代码直接出局；"take-home 之后的答辩才是真正的评估"；面过 20 多家 AI startup 的候选人称 70–80% 至少一轮 coding challenge（[TI-Formats]、[TI-AI-Rules]、[1p3a]）
- 海外 live coding 的"标准五题"：带 step budget 和错误即观察的 agent loop、异步重试 / backoff / 超时包装、流解析器、带程序化 + LLM judge 评分的评测 harness、LLM 调用批处理与并发；每一题都能对到 LangChain / Decagon / Sierra / PromptLayer 的真题（[TI-LangChain]、[TI-Decagon]、[Aced-45]、[PromptLayer]）
- 海外通用算法题没有消失：OpenAI / xAI 带事务的 KV store、按过期计算剩余积分、字符串列表编解码、感染扩散模拟；Anthropic 重复文件扫描、profiler 采样转 trace、有序变换的图像处理管线、SQL + Python 清数据；但论坛称"比硬核 LeetCode 轻"（[Aced-45]、[Reddit-LangChain]）

## 4. 系统设计压轴题只有五个家族，评分多出四条 LLM 专属轴

五个家族（备一道深的即可覆盖几乎所有报道过的题）
- 家族 1 客服 Agent（调工具 + 升级人工）：techinterview 称这是 2026 最常见题型，"相当于以前的推荐系统"；Sierra 版 Spotify 客服 / 户外公司客服 / 订阅取消流；Decagon 版 读意图 -> 调退款 / 查单 / 改账户工具 -> 决定何时升级；国内版 电商客服 Agent 需求理解与成功指标（通义）、AI 客服 + RAG 分块与向量模型选择（阿里）；PromptLayer 版 分析工单自动起草回复并升级复杂问题（[TI-MLE]、[Aced-45]、[TI-Decagon]、[ML155]、[PromptLayer]）
- 家族 2 企业 RAG / 知识库（权限 + 引用 + 弃答）：OpenAI / Google / Lyft 都报道过"尊重权限、给引用、该弃答就弃答的内部文档搜索 Agent"；OpenAI "多租户 + 严格延迟成本目标的 RAG"；Shopee / 字节 企业知识库完整链路与权限隔离；Databricks 异构文档摄入索引管线；Scale AI 保险理赔 Agent 用 RAG 输出批准 / 拒绝 / 升级并控 Token（置信度或金额超阈值路人工，缓存、紧 top-k、路由、每单 token 上限，检索和决策分开评，带引用落日志）（[Dev-to]、[ML155]、[Aced-45]）
- 家族 3 Coding / Code Review Agent：字节"AI 代码 Review Agent 只提意见不改代码"、B 站多 Agent 自动 PR 系统、"设计一个 Coding Agent 和 Claude Code / Codex 有什么区别"（标字节）、百度"只有 LLM API 与 VS Code 时怎么造 Agent"；Cursor 多文件编辑带验证与回滚；Meta code-review agent 结合 LLM 编排与确定性工具、怎么防 coding agent 在训练和推理时泄露私有代码（[ML155]、[ADG]、[KK]、[TI-Cursor]、[Dev-to]）
- 家族 4 推理服务 / 网关：techinterview 称"设计 LLM inference API"是 2026 问得最多的 AI 时代题（token 流式、continuous batching、KV cache 管理、GPU 调度、多租户隔离、限流、计费）；Anthropic 单 GPU 100 条同步 batching（动态 batching 最大 batch + 最大等待窗、按序列长度分桶、backpressure、continuous batching）；小红书"十几万请求扩容"；京东 Agent 专家 JD 的 Model Gateway / Semantic Cache / 熔断（[TI-SD]、[Aced-45]、[KK]）
  - "多租户 LLM 网关"没有作为独立真题出现过，其关注点（租户隔离、限流、计费、路由）折进了推理 API 题（[TI-SD]、[Educative]）
- 家族 5 Agent Harness + 评测平台：OpenAI "安全运行工具型 Agent 的 harness 以及其上的评测平台"；LangChain 版 LangSmith 每日 10 亿 trace 摄入、跨数千数据集的异步评测平台、带 HITL 中断的 LangGraph 持久层、带版本和成本追踪的 prompt playground（[Dev-to]、[PracHub]、[TI-LangChain]）
- 国内特有变体：运维故障排查 Agent 与高危操作管控（字节、唯品会）、视频问答 / 股票分析 Agent 全链路（阿里、快手、京东）、内容标注 Agent（抖音）、多 Agent 文献调研系统、Deep Research 功能对比与 Manus 评价改进（抖音）、覆盖训练到服务的一体化 AI 开发平台（腾讯混元）、京东告警 Agent 的 fallback 与发布期准确性、淘宝闪购 HITL / 风险分级 / 熔断 / 沙箱 / 审计（[ML155]、[ML265]、[KK]）

答题结构（KDnuggets 7 步 / DataTalks 9 步 / Grigorev 时间分配合并）
- 澄清（5–10 分钟）：谁是用户、什么任务、source of truth 是什么、不确定时怎么办、"系统只能建议还是能调工具改状态"、延迟 / 成本 / 隐私 / 对事实错误的容忍度、API 还是自托管；"最常见的失败是不澄清就跳方案"（[KDn]、[DataTalks]、[Grigorev]）
- 估算：tokens/s、上下文大小、embedding 量、每次调用成本、峰值 QPS（[KDn]）
- 默认链路（10–15 分钟）：输入 -> 安全 / PII 层 -> 编排器 -> 检索（向量库 + reranker）-> 路由后的模型 -> 输出 guardrail -> 流式返回 -> 可观测（[KDn]）
- 深挖 1–2 个组件（20–30 分钟）：RAG 细节、工具权限与类型化输入、停止条件、人工审批（[KDn]、[DataTalks]）
- 取舍：延迟 vs 质量、RAG vs 微调、成本上限、fallback 模型（[KDn]）
- 失败模式 + 可观测（主动说，不等问）：幻觉、注入、供应商故障、embedding 漂移、多租户隔离，各自怎么检测（[KDn]）
- 演进：Prompt A/B、反馈回路、发布前 eval gate、逐步迁移模型、回滚与归属（[KDn]、[DataTalks]）

四条 LLM 专属评分轴（在经典 rubric 之外）
- 边界：模型能动什么、能走几步、谁审批（"Agent 发小额退款"这类题核心是控制不是架构）（[DesignGurus]）
- 闭环：质量信号怎么回流到评测和下一版模型，因为"模型答错时服务照样报成功"（[DesignGurus]）
- 成本与延迟真实感：每次模型调用都要有数（模型调用约为数据库读的 10,000 倍成本、1,000 倍耗时）（[DesignGurus]）
- Failure honesty：没被问就先说模型怎么挂、fallback 是什么（[DesignGurus]、[Grigorev]）
- 常见失分：把 LLM 当 source of truth、把控制流塞进 Prompt、不需要自主性却选 Agent、只报模型名不讲角色、只报工具名不讲理由、忽略注入 / 泄露 / 不安全工具执行；"Which model would you use" 要答角色不答名字；"假设模型已存在"意味着训练不在范围内（[Grigorev]、[DesignGurus]）
- 资深与中级的分界：资深要"在不确定性中推理"（输出不可预测但系统要可预测）和取舍流畅度，中级只需合理的高层架构；AI 画图被允许后"deep dive 更难不是更容易"，口头描述被打分（[Grigorev]、[TI-SD]）

追问簇（对应生产事故类型）
- 评测：resolved 怎么定义、离线 vs 在线、judge 怎么校准、检索和生成怎么分开评、不看最终答案怎么评 Agent（[TI-Decagon]、[TI-LangChain]、[LinkedIn-Prep]）
- 工具失败与循环：模型不调工具、调用格式错、永远循环；ReAct vs 原生 function calling vs 显式状态机各在什么时候选；模型会"无意中停止调工具"所以要显式 submit 工具（[TI-LangChain]、[TI-Decagon]、[Inspect]）
- 成本：路由、语义缓存、Prompt 压缩、每任务预算；GPU 池按 tokens/s 估、容量不够时的降级路径（短输出、小模型、缓存、关可选功能）（[KDn]、[Educative]）
- 幂等：超时重试会重复副作用（重复发 Slack、重复扣费），见 2.10（[TianPan-Idem]）
- 流式：SSE vs WebSocket、断流续传、心跳、backpressure、部分响应归属（[Educative]）
- 注入：检索内容当不可信、工具 allowlist、不可变 system prompt、输出校验、红队（[Educative]、[TI-LangChain]）
- 版本与发布：prompt / 模型 / 工具改动的静默回归、shadow / canary、多快能回滚、托管模型 API 漂移（[Educative]、[DataTalks]）
- 国内一手追问：2 秒 / 10 秒 / 90 秒三级缓存各对应什么（腾讯 04-21）、管理五台服务器做负载均衡（腾讯 04-22）、支付接口超时的排查流程（字节、唯品会）（[NC-TXBD]、[ML265]）

## 5. 项目深挖的四层套路：真实性 → 失败路径 → 替代方案 → 指标口径

四层推进（KK 的归纳，与美团 / 千问 / 字节 / 腾讯一手帖完全吻合）
- 第一层 验真实性：有没有真实用户、输入输出是什么、个人代码边界在哪、实际结果如何、上线没有、日均多少请求、训练样本从哪来、模型有没有调过、是不是自发做的（[KK]、[KM-MT]、[NC-TXBD]）
- 第二层 沿失败路径追问：工具超时或返回部分结果怎么办、重复调用、压缩丢信息、并发写冲突、进程在第七步崩溃怎么从 Checkpoint 恢复而不重复副作用（[KK]）
- 第三层 逼你做比较："为什么不用 Workflow、规则、小模型或一次 API 调用"、RAG vs 长上下文 vs grep、Permission vs Sandbox、为什么不用闭源 SOTA、为什么微调而不只用 Prompt / Spec（[KK]）
- 第四层 追指标口径："你说准确率提升，基线、样本量、统计窗口和归因是什么"、"哪个线上 bad case 最难、最终确认根因的证据是什么"、幻觉率怎么定义、用哪个测试集算、评测集有没有过拟合（[KK]、[ML155]）

一手样本里的追问链
- 美团智能运维 Agent（16 题全项目，每题约追三层，沿"为什么检索 -> 怎么检索 -> 证据能否相信"）：为什么想做 -> 系统怎么运转、中间件选型依据 -> 为什么用 RAG 不直接把文档全丢给 Agent -> 切块 / 索引 / 存储 / 检索 / 排序哪几步影响最大 -> 对比过长 Query 和三字 Query 的召回吗（候选人承认没做过实验）-> 旧向量怎么失效 -> 召回矛盾谁说了算 -> 工具返回结构怎么设计 -> 上线了吗日均多少（[KM-MT]）
- 阿里千问私人 RAG（15 题全项目）：解释项目 -> CSV 怎么解析 -> 怎么切片 -> 双路权重怎么分 -> 多跳怎么实现 -> Query 怎么改写 -> 访问控制怎么实现 -> "私人系统为什么要做访问控制"（最有辨识度的追问：场景为什么需要、不做会出什么问题、做了怎么验证）-> PII 脱敏 -> 长对话摘要 -> 最难的点 -> 用了 Harness 介绍数据怎么走（[KM-QW]）
- 字节 Coding Agent（一面 20 题）：开场讲架构 -> 18 题全是该项目会碰到的机制（压缩保留什么、压缩后怎么不忘早先需求、路径震荡原因、记忆提取存储检索、工具多提示词过长、工具返回过大、多 Agent 改同一文件冲突）-> 收尾"若优化一个 Coding Agent 优先从哪些方向入手"（[NC-BD2]）
- 其他公司的深挖点：字节 2026-07-01 拿候选人项目与 Claude Code / Codex / Hermes 比较并问 Harness 取舍；字节社招问 Memory / RAG 的实际用户收益；京东告警 Agent 问节假日 / 发布期准确性、非 AI fallback、4000 页多模态文档、RAG 更新；小米问代码生成为什么要微调不能只用 Prompt、静态 Spec 与动态环境反馈、自托管 vs API；MiniMax 问 PRD / design 怎么防需求丢失、Task 粒度、用户反馈有效性（[KK]）
- 各厂风格（ADG，无出处仅供参考）：字节层层深入 ReAct 细节与工具循环；阿里要求画架构图并考 RAG 工程化；小米追到代码级且反感背答案；美团后端基础与 Agent 各半（[ADG]）

海外 take-home 答辩的固定问题
- Sierra："这个客户你会优先做哪两个 feature、为什么""为什么选这个 Agent 架构""上线要追踪什么指标和可观测"；评 product judgment、realistic prioritization、agent fundamentals、production metrics、design rationale（[Aced-Sierra]）
- PromptLayer："它真的能跑吗""有没有有意义的评测指标""能不能解释设计选择"；"思路清楚的架构和评测比完美准确率重要"，没有评测指标是最常被点名的失分（[PromptLayer]）
- Cursor："每一行都要能辩护"（[TI-Cursor]）

准备方法（从上面套路反推）
- 项目里每个组件准备一个"被替代方案"的对比（Workflow / 规则 / 小模型 / 长上下文 / grep / 闭源 SOTA / 一次 API 调用），说清为什么没选它
- 每个组件准备一条失败路径和它的处理（超时、部分结果、重复调用、崩溃恢复、并发冲突）
- 准备一组带口径的数字（基线、样本量、测试集来源、统计窗口、归因方法）和一个最难的线上 bad case 及根因证据
- 上线状态、真实用户数、日均请求要能一句话答；没上线要有诚实且具体的原因

## 6. 八股在应用岗只剩"基础覆盖"，GRPO / 手撕 MHA 留在算法岗

应用岗会被问到的（概念和取舍，不要求推导 loss 或手撕）
- Transformer / Attention 概述、为什么除以根号 d_k、LayerNorm vs BatchNorm、位置编码 sin/cos vs RoPE vs ALiBi、分词器、Scaling Law 与涌现（腾讯 04-23 二面要求"Transformer 概述"；字节番茄小说 Agent 实习一面问 QKV）（[XL]、[ADG]、[NC-TXBD]、[KMI]）
- 采样与解码：Temperature / Top-P / Top-K、贪心 / Beam / 采样（[XL]）
- KV Cache / Prompt Caching、量化 INT8 / INT4 / AWQ / GPTQ 选型、MoE 为什么（DeepSeek V3 / Qwen）、vLLM / PagedAttention、部署方案选型（快手 Agent 开发一面问 KV Cache；小米 2025 问 Prefill / Decode）（[XL]、[ML265]、[KMI]、[KK]）
- 微调：LoRA 原理与优点、微调方案选型、SFT 之后的 Post-Training（RLHF / DPO / GRPO / 拒绝采样的关系）、DPO vs PPO 概念、"SFT 和 RLHF 哪个更适合快速迭代、基模越来越强时两者的破局点"（字节 Agent 开发四面原题）、RAG vs 微调（[XL]、[KM-BD]）
- 算法八股渗入应用岗的主要入口是"有没有微调过 Agent / 工具调用能力、数据集怎么收集"（字节框架帖涉及 loss masking、LoRA / QLoRA；小米、快手、阶跃、智谱都问）（[NC-BDFW]、[KK]）
- 这组题的"安全底线"约等于小林 coding 大模型工程专题 23 题；2025 应用题库（CSDN100）Transformer 八股占 24%，2026 应用题库（XL）降到约 19%，且更偏"应用场景下怎么选"（[XL]、[CSDN100]）
- 海外对照：面试官"假设你 transformer 烂熟"直接跳过；经典 ML（逻辑回归、树、GBM）"仍会出现但不再核心"，深推导很少问；attention 缩放、MQA / GQA、Flash Attention、MoE、MLA 被题库标为"所有级别"但主要在实验室；RLHF 端到端、DPO vs PPO 偶尔问（[Reddit-LLMDevs]、[TI-MLE]、[LDS-50]、[PromptLayer]）

基本只在算法 / 后训练 / Infra 岗出现的
- 写出 GRPO loss、ε=0.2 的来源、reward hacking、KL 估计、DPO 负样本、reward / verifier 设计、Tool Response 是否进 loss mask、MCP 训练数据（阿里、字节、智谱、阶跃；百度文心一面全程后训练）（[KK2]、[KK]）
- 手撕 MHA / MoE / AdamW / PPO、bf16 / fp16 / fp32、RMSNorm vs LayerNorm 推导、softmax 加速、分布式训练、显存估算（腾讯 WXG 算法一面二面、字节 26 届算法二面、快手 2025 大模型应用一面）（[KK2]）
- 海外模型侧 coding：NumPy 写 greedy / top-k / top-p 解码、批量因果多头注意力、"修一个有 bug 的小 transformer 再加 KV cache"（Mistral、Meta、OpenAI 模型侧岗位）（[Dev-to]）
- 混合团队会把这些带进应用岗：腾讯应用一面 2026-04-04 问 GRPO / PPO、QLoRA、量化；百度文心一面偏后训练（SFT 何时足够、reward / verifier、reward hacking、PPO / GRPO）二面才偏系统；快手 26 届大模型应用一面 MHA、RMSNorm、KV Cache + 手撕（[KK]、[KK2]）
- 题库划分可作备考边界：ML265 九大主题里 1 Transformer、2 预训练 / 分布式 / 推理、3 SFT / LoRA / RLHF 归算法，4 RAG、5 Agent、6 应用工程归应用；wdndev/llm_interview_note（15.3k star）目录以算法为主、RAG + Agent 仅两条，不适合应用岗主攻（[ML265]、[WD]）
- Hello-Agents 第 3 章（LLM 基础）够应用岗八股底线，第 11 章 Agentic-RL（SFT -> GRPO）是算法混合团队的加分范围；第 4–12 章（范式 -> 低代码 -> 框架 -> 手搓 -> 记忆 / RAG -> 上下文工程 -> 协议 -> Agentic RL -> 评测）与本文节点几乎一一对应，可当备考目录（[HA]）

## 结论

把中英文来源叠在一起看，2026 的 Agent 面试已经从"你认识哪些框架组件"转成"你能不能把一个不确定的系统做到可预测"：中文一手面经里的 Harness、三层压缩、grep vs RAG、Skill vs Tool、幂等恢复，和海外面试官笔下的评测设计、取舍判断、failure honesty，是同一件事的两种说法；LangChain 组件题和 Dify / Coze 只剩中小公司 JD 里还在提（[ML155]、[XL]、[TianPan]、[DesignGurus]）。两边真正的差异在筛人机制：国内靠"项目真实性 + 指标口径 + 后端 / 算法八股混考"筛，海外靠"take-home 答辩 + 客户模拟 + 评测设计"筛，所以同一份项目经历要准备两套讲法，一套讲机制和数字，一套讲取舍和为什么不做（[KK]、[Perspective]、[Hamel]）。

最省力的备考路径是以自己的项目为轴，把 12 个节点各准备四句："机制上怎么做的 / 为什么不用更简单的方案 / 怎么评、数字口径是什么 / 怎么挂过、怎么恢复"，再单独备一道家族 1（客服 Agent）或家族 2（企业 RAG）的系统设计和一个 60 行的 ReAct 循环。需要明确的不确定性：本文频率是来源数不是面试场次；二手题库的公司标签互相无法核实；"手写最小 ReAct Agent"和 Anthropic 五阶段流程都还没有一手帖证实；没有任何公司公开过评分表，所有 rubric 都是面试官个人或备考站的推断（[ML155]、[Perspective]、[PromptLayer]）。

## 来源

中文（一手面经）

[NC-BD2]: https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea
[NC-TXBD]: https://www.nowcoder.com/discuss/878600528970735616
[NC-BDFW]: https://www.nowcoder.com/discuss/878709844730003456
[KM-MT]: https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html
[KM-QW]: https://notes.kamacoder.com/interview/llm/company/20261015alibaba_qianwen_rag_interview.html
[KM-BD]: https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html
[JJ]: https://juejin.cn/post/7674870309703319567
[1p3a]: https://www.1point3acres.com/bbs/thread-1148857-1-1.html

中文（带原帖链接的汇总与题库）

[KK]: https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/
[KK2]: https://keepkeen.github.io/blog/llm-recruit-interview-roundup/
[KMI]: https://notes.kamacoder.com/interview/llm/
[KM]: https://notes.kamacoder.com/interview/llm/agent_interview.html
[ML155]: https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/
[ML265]: https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/
[XL]: https://xiaolinnote.com/ai/
[CSDN100]: https://blog.csdn.net/teddyandwolf/article/details/148159001
[ADG]: https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html
[WD]: https://github.com/wdndev/llm_interview_note
[HA]: https://github.com/datawhalechina/hello-agents

英文（面试官 / 招聘方 / JD）

[PromptLayer]: https://blog.promptlayer.com/the-agentic-system-design-interview-how-to-evaluate-ai-engineers/
[TianPan]: https://tianpan.co/blog/2026/04/27/ai-engineer-interview-eval-design
[TianPan-Idem]: https://tianpan.co/blog/2026-04-20-idempotency-llm-pipelines
[Hamel]: https://maven.com/p/07e7c7/cracking-the-ai-evals-interview
[Gauntlet]: https://gauntletai.com/night-school/what-hiring-managers-actually-look-for-in-ai-engineers
[AgentJD-Staff]: https://100hires.com/j/TYaFyGz
[LangChain-Report]: https://langchain.com/state-of-agent-engineering

英文（公司指南与题库）

[Aced-45]: https://www.aced.io/blog/ai-engineer-interview-questions
[Aced-Sierra]: https://www.aced.io/guides/sierra-agent-engineer-interview
[Perspective]: https://getperspective.ai/blog/anthropic-applied-ai-engineer-interview-process-frontier-lab-2026/markdown
[IQ-Anthropic]: https://www.interviewquery.com/guides/anthropic-ai-engineer
[TI-Applied]: https://www.techinterview.org/post/3233476824/ai-engineer-interview-rag-agents-evals/
[TI-Agents]: https://www.techinterview.org/post/3233474444/ai-ml-interview-ai-agents-tool-use-chain-of-thought-reasoning-planning-function-calling-rag-agents-autonomous-systems/
[TI-MLE]: https://www.techinterview.org/post/3233474925/ml-engineer-interview-post-llm-era/
[TI-Cursor]: https://www.techinterview.org/companies/cursor/
[TI-Decagon]: https://www.techinterview.org/companies/decagon-interview-guide/?format=md
[TI-LangChain]: https://www.techinterview.org/companies/langchain/?format=md
[TI-SD]: https://www.techinterview.org/post/3233474919/ai-in-system-design-interviews-2026/?format=md
[TI-Formats]: https://www.techinterview.org/post/3233474920/post-llm-coding-interview-format/?format=md
[TI-AI-Rules]: https://www.techinterview.org/post/3233475415/ai-coding-interviews-rules-2026/?format=md
[Exponent-Cursor]: https://www.tryexponent.com/experiences/cursor-software-engineer-interview-a9c32f
[GH-Shekhar]: https://github.com/amitshekhariitbhu/ai-engineering-interview-questions
[PracHub]: https://prachub.com/resources/ai-engineer-interview-questions-2026-rag-agents-evals-and-production-systems
[LDS-50]: https://letsdatascience.com/blog/50-llm-and-ai-engineer-interview-questions-for-2026
[AV-30]: https://www.analyticsvidhya.com/blog/2025/05/ai-agent-interview-questions/
[Dev-to]: https://dev.to/adityajha2005/what-openai-anthropic-mistral-and-meta-actually-ask-in-ai-engineer-interviews-2adk
[KDn]: https://www.kdnuggets.com/how-to-answer-ai-system-design-interview-questions
[Educative]: https://www.educative.io/blog/ai-system-design-interview-questions
[DataTalks]: https://datatalksclub.github.io/podwiki/wiki/llm-system-design-interview/
[DesignGurus]: https://www.designgurus.io/course-play/grokking-the-ai-system-design-interview/doc/how-ai-system-design-shows-up-in-interviews
[Grigorev]: https://github.com/alexeygrigorev/ai-engineering-field-guide/blob/main/interview/questions/04-ai-system-design.md
[LinkedIn-Prep]: https://au.linkedin.com/in/alexowji
[Reddit-LLMDevs]: https://reddit.sentinel-team.org/posts/1sn7hc8/snapshots/2026-04-17T05%3A37%3A44.74927Z
[Reddit-LangChain]: https://reddit.sentinel-team.org/posts/1t1ywp7/snapshots/2026-05-09T00%3A32%3A05.66647Z
[DEV-ReAct]: https://dev.to/gabrielanhaia/the-react-loop-from-scratch-the-agent-baseline-before-any-framework-19pd
[Inspect]: https://inspect.aisi.org.uk/react-agent.html
[Inngest]: https://www.inngest.com/docs/ai-patterns/agent-tool-loops

官方教材与指南（答题锚点）

[Anthropic-BEA]: https://www.anthropic.com/engineering/building-effective-agents
[Anthropic-CE]: https://www.anthropic.com/engineering/effective-context-engineering-for-ai-agents
[Anthropic-Tools]: https://www.anthropic.com/engineering/writing-tools-for-agents
[Anthropic-MA]: https://www.anthropic.com/engineering/multi-agent-research-system
[Anthropic-Evals]: https://anthropic.com/engineering/demystifying-evals-for-ai-agents
[OpenAI-Guide]: https://cdn.openai.com/business-guides-and-resources/a-practical-guide-to-building-agents.pdf
[OpenAI-SDK-Orch]: https://openai.github.io/openai-agents-python/multi_agent/
[OpenAI-SDK-Guard]: https://openai.github.io/openai-agents-python/guardrails/
[ModelSpec]: https://model-spec.openai.com/2026-08-18.html
[MCP-Arch]: https://modelcontextprotocol.io/docs/learn/architecture
[LangChain-Agents]: https://docs.langchain.com/oss/python/langchain/agents
[LangGraph-Persist]: https://docs.langchain.com/oss/python/langgraph/durable-execution
[12factor]: https://github.com/humanlayer/12-factor-agents
[Huyen]: https://github.com/chiphuyen/aie-book/blob/main/ToC.md
[Weng]: https://lilianweng.github.io/posts/2023-06-23-agent/
[DLAI-Memory]: https://www.deeplearning.ai/short-courses/long-term-agentic-memory-with-langgraph/
[Google-Companion]: https://ibl.ai/blog/google-agents-companion.md
