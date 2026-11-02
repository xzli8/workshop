# 中文来源：大模型 Agent / LLM 应用开发 / AI 应用工程师 面试题与面经体系（2025–2026）

说明
- 时间范围：以 2025–2026 材料为主；明确标注 2025 的条目写 [2025]，未标注的均为 2026 材料
- 题型标记：[概念] 概念题 / [设计] 系统设计题 / [手撕] 现场编码 / [深挖] 项目追问 / [行为] 行为面 / [八股] 模型原理
- 岗位标记：[应用] 应用岗（Agent / RAG / 应用研发）/ [算法] 算法岗（训练 / 后训练）/ [混] 两者都出现
- 来源缩写（正文中反复引用）
  - KK：keepkeen 2025–2026 国内大厂 Agent 面经汇总（二手整理，每条附牛客原帖链接）https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/
  - KK2：keepkeen 2025–2026 大模型算法岗招聘、面经与笔试汇总 https://keepkeen.github.io/blog/llm-recruit-interview-roundup/
  - ML155：面灵AI AI Agent 面试题 155 道（按公司标注）https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/
  - ML265：面灵AI 大模型与 Agent 面试题 265 道（九大主题）https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/
  - KM：卡码笔记 Agent 面试题页 https://notes.kamacoder.com/interview/llm/agent_interview.html
  - KMI：卡码笔记 大模型面经索引（7 份公司面经）https://notes.kamacoder.com/interview/llm/
  - KM-BD：卡码 字节 Agent 开发暑期实习四面（2026-05-06）https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html
  - KM-MT：卡码 美团 Agent 开发日常实习（2026-10-21）https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html
  - KM-QW：卡码 阿里千问 AI 应用研发一面（2026-10-15）https://notes.kamacoder.com/interview/llm/company/20261015alibaba_qianwen_rag_interview.html
  - XL：小林coding AI 面试题库（Agent 30 / RAG 23 / 工具调用 22 / 工程 23 / LangChain 12 / AI 编程 14）https://xiaolinnote.com/ai/
  - NC-TXBD：牛客 腾讯/百度 8 份面经合辑（2026-04-21～04-24）https://www.nowcoder.com/discuss/878600528970735616
  - NC-BDFW：牛客 "字节Agent面试问我：你了解哪些 Agent 框架？" https://www.nowcoder.com/discuss/878709844730003456
  - NC-BD2：牛客 字节 AI Agent 日常实习 一面/二面（2026-08-13/16，一手）https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea
  - JJ：掘金 "3 道大厂 AI 硬核真题"（字节/阿里淘天/腾讯，2026）https://juejin.cn/post/7674870309703319567
  - CSDN100 [2025]：100 道大模型应用开发面试题（编制 2025-05-23）https://blog.csdn.net/teddyandwolf/article/details/148159001
  - ADG：CSDN "2026 大厂 AI Agent 面试全攻略"（2026-08-17，公司标签无出处，可信度低）https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html
  - WD：GitHub wdndev/llm_interview_note（15.3k star，偏算法）https://github.com/wdndev/llm_interview_note
  - HA：Datawhale Hello-Agents 目录 https://github.com/datawhalechina/hello-agents

---

## Key Question 1：哪些题在多份面经 / 题库中反复出现？（按主题分类的题目清单）

### Takeaway
应用岗的高频核心集中在五块：ReAct vs Plan-and-Execute vs Workflow 的选型、Function Calling / MCP / Skills / A2A 的区别、上下文压缩与长短期记忆、RAG 全链路（切块 / 混合检索 / Rerank / 评测 / 幻觉）、以及死循环 / 工具失败 / Prompt Injection / 高危操作的可靠性与安全；2026 下半年新增 Harness Engineering、Claude Code 源码级问题和"手写最小 ReAct Agent"。

### Cited Findings

#### 1.1 LLM 基础 / 八股（在应用岗出现的部分，详见 Key Question 4）
- "Token、上下文窗口和上下文腐化是什么？它们如何影响模型的理解和生成？" [概念][应用] — 字节 Agent 开发四面 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)
- "幻觉（Hallucination）是怎么产生的？你有哪些方法可以减轻模型幻觉？" [概念][混] — 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)；"大模型为什么会出现幻觉？怎么缓解？" [XL](https://xiaolinnote.com/ai/)；"模型幻觉怎么产生的？Agent场景下如何缓解？"（标字节/阿里/美团，标签无出处）[ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)
- "大模型的参数：温度值、Top-P、Top-K 分别是什么？各个场景下的最佳设置是什么？" [概念][应用] — [XL](https://xiaolinnote.com/ai/)；"Temperature、Top-P、Top-K 三个参数有什么区别？Agent场景怎么调？" [ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)
- "为什么长上下文会出现 Lost in the Middle？上下文窗口越大越好吗？" [概念][应用] — [XL](https://xiaolinnote.com/ai/)
- "KV Cache 是什么？Prompt Caching 的原理是什么？" [概念][混] — [XL](https://xiaolinnote.com/ai/)；Prompt Cache 原理及如何提高命中率 — 字节 Agent 实习一面 [NC-BD2](https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea)；美团 Agent 开发一面 2026-08-25 也问 Prompt Cache [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "Temperature=0 的非确定性"（相同输入为何不同输出）[概念][应用] — 蚂蚁财富 Agent 开发 2026-08-29 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)；"相同输入为什么会得到不同的 Agent 执行结果？" [XL](https://xiaolinnote.com/ai/)
- "大模型部署有哪些主流方案？vLLM、TGI、llama.cpp、SGLang 实际项目里怎么选？" / "大模型量化是什么？INT8/INT4/AWQ/GPTQ 怎么选？" [概念][混] — [XL](https://xiaolinnote.com/ai/)

#### 1.2 Prompt 工程
- "Prompt Engineering 的核心目标是什么？为什么模型会对同一件事，换个说法效果差十倍？" [概念][应用] — 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)
- "System Prompt / Few-shot / Chain-of-Thought（CoT）在 Prompt 设计中有什么作用？" [概念] — 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)
- "什么是 CoT？为啥效果好？它有什么缺点或局限性？" / "如何写好 Prompt？分享下 Prompt 工程实践经验？" [概念] — [XL](https://xiaolinnote.com/ai/)
- "Prompt、Context、Harness Engineering 三者的区别"（钉钉；百度 Agent 研发一面 2026-08-18 也问）[概念][应用] — [ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "Cursor / Claude Code 的 System Prompt 怎么写"（小红书 2025-10 二面）[概念][应用] [2025] — [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "Prompt A/B"（淘天/夸克 2025 下半年）[深挖][应用] [2025] — [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- CoT 与 Few-shot、Prompt 设计与版本管理（快手、京东）[概念][应用] — [ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)
- [2025] "什么是提示工程？""零样本/少样本/CoT 提示""角色提示""思维链和思维树的区别""如何评估和优化提示的效果？" — [CSDN100](https://blog.csdn.net/teddyandwolf/article/details/148159001)

#### 1.3 结构化输出
- "什么是 Structured Output？如何通过它控制模型输出？" [概念][应用] — 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)
- 结构化输出与 JSON 解析失败的降级（百度、小红书）[概念][应用] — [ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)
- "Function Call 和普通的 Prompt + 正则解析有什么区别？" [概念] — [KM](https://notes.kamacoder.com/interview/llm/agent_interview.html)
- [2025] "在提示工程中，如何有效地控制大模型输出的格式？""LangChain 中的输出解析器（Output Parsers）是做什么的？" — [CSDN100](https://blog.csdn.net/teddyandwolf/article/details/148159001)
- 结构化输出相对其它主题出现频率低：在 ML155 / XL / NC-BD2 等 2026 一手面经中几乎不单独成题，通常被"工具调用返回结构怎么设计"吸收（见 1.4）

#### 1.4 工具调用 / Function Calling / MCP / Skills
- "什么是 Function Calling？原理是什么？""LLM 是如何学会调用外部工具的？""大模型的 Function Call 能力是怎么训练出来的？" [概念][应用] — [XL](https://xiaolinnote.com/ai/)；"LLM 是如何学会调用外部 API 或工具的？" — 字节 [NC-BDFW](https://www.nowcoder.com/discuss/878709844730003456)；Function Calling 原理（阿里、快手、京东）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- "MCP 和 Function Call 有什么本质区别？" [概念] — [KM](https://notes.kamacoder.com/interview/llm/agent_interview.html)；MCP 与 Function Calling 的区别（字节、快手、货拉拉、百度）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；美团 2025 后端暑期问 MCP 的 SSE/stdio [2025] [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "MCP 与 Skills 的区别" — ML155 标注公司最多的一题（TikTok、京东、字节、美团、腾讯、阿里云、小红书）[概念][应用] — [ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；"Function Calling、Skill、MCP 这三个有什么区别？" [XL](https://xiaolinnote.com/ai/)；"MCP（工具标准化接口）和 Skill（能力封装复用）在 Agent 系统中起什么作用？" 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)
- "Skills 的渐进式披露机制"（美团）、"Skills 为何能减少 token 消耗"（网易、快手、腾讯）、"Skill 懒加载"（蚂蚁财富）[概念][应用] — [ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "Skill vs Tool 边界"（小红书 Agentic 全栈一面 2026-08-06；百度 Agent 开发岗 2026-10-20）[概念] — [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)；[KMI](https://notes.kamacoder.com/interview/llm/)
- "A2A 协议是什么？它和 MCP 协议的区别是什么？" [概念] — [XL](https://xiaolinnote.com/ai/)；"了解 A2A 和 MCP 协议吗？" 字节 [NC-BDFW](https://www.nowcoder.com/discuss/878709844730003456)；蚂蚁国际 2025 问 MCP/A2A [2025] [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "MCP 协议通常采用什么通信方式？""WebSocket 和 SSE 通信的区别及局限性？""WebRTC 和 WebSocket 在 AI 对话流中的核心差异？" [概念][应用] — [XL](https://xiaolinnote.com/ai/)
- 大规模工具集选错工具：
  - "30个工具注册进去，选工具准确率下降怎么办？"（标字节/阿里）[设计][应用] — [ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)
  - "工具很多时，Agent 如何做 Tool Routing，减少 Token 并避免选错工具？" — [XL](https://xiaolinnote.com/ai/)
  - 工具很多时如何避免提示词过长；工具返回数据过大超出窗口时如何处理 — 字节 Agent 实习一面 [NC-BD2](https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea)
  - 大规模工具路由 — 百度 Agent 开发岗 [KMI](https://notes.kamacoder.com/interview/llm/)；工具选择准确率下降 — 京东 2025 [2025] [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 工具失败处理："工具调用格式非法、参数错误、超时或失败时，Agent 如何容错？" — [XL](https://xiaolinnote.com/ai/)；"工具调用的重试、超时与异常隔离"（快手、蔚来）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；"工具超时或返回部分结果怎么办？" [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 工具返回结构设计："十四、工具调用返回结构怎么设计？" [设计][应用] — 美团 Agent 日常实习 [KM-MT](https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html)；"Tool Schema" 淘宝闪购 2026-04-30 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "Qwen 工具调用适配、空调用"（腾讯 2026-04-10）[深挖] — [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "如果同时触发多个 Function Call 怎么处理？""LLM 自己执行 Function Call 吗？" — [KM](https://notes.kamacoder.com/interview/llm/agent_interview.html)
- "Agent 接入工具时应该如何选择 CLI 或 MCP？""为什么要把 Agent 的工具执行流程封装成 CLI？""Agent 的代码执行沙箱应该如何设计？" — [XL](https://xiaolinnote.com/ai/)；"MCP 与 CLI" 快手 Agent 开发一面 2026-09-28 [KMI](https://notes.kamacoder.com/interview/llm/)
- 一手帖"Agent 的 thinking 步骤如何决定调用工具还是直接回复" — 腾讯 AI 应用开发实习一面 2026-04-21 [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)

#### 1.5 Agent 循环与编排（ReAct / Plan-and-Execute / Workflow）
- "LLM 和 Agent 有什么区别？""Agent 和 Workflow 有什么区别？" [概念] — [KM](https://notes.kamacoder.com/interview/llm/agent_interview.html)；Agent 与 Workflow 的关系（字节、百度、腾讯广告、得物）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；"Workflow，Agent，Tools 这三个的概念和区别介绍一下？" [XL](https://xiaolinnote.com/ai/)；阿里云 2026-04-10 问 Workflow 与 Agent [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "你如何定义一个基于 LLM 的智能体（Agent）？它通常由哪些核心组件构成？" — 字节 [NC-BDFW](https://www.nowcoder.com/discuss/878709844730003456)；腾讯二面 2026-04-23 问"agent vs 传统 LLM、核心组件" [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)
- "请详细解释 ReAct 框架。" — 字节 [NC-BDFW](https://www.nowcoder.com/discuss/878709844730003456)；"ReAct Agent由哪几部分组成？" — 腾讯二面 2026 [JJ](https://juejin.cn/post/7674870309703319567)；ReAct 流程与优缺点（携程、腾讯、快手）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- "ReAct、Plan-and-Execute、Reflection 三种范式有什么核心区别？实际项目中该如何选型？" — [XL](https://xiaolinnote.com/ai/)；ReAct 与 Plan-Execute-Replan 的区别（美团、小红书）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；ReAct 与 Plan 模式区别 — 字节实习一面 [NC-BD2](https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea)；Plan-and-Execute vs ReAct — 美团 2026-08-25 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "在 Agent 的设计中，有哪些主流方法可以赋予 LLM 规划能力？"（CoT/ReAct/ToT/GoT/Plan-and-Execute/Reflexion）— 字节 [NC-BDFW](https://www.nowcoder.com/discuss/878709844730003456)；"如何赋予 LLM 规划能力？""讲讲 Agent 的反思机制？" [XL](https://xiaolinnote.com/ai/)
- "单次提示词、Workflow、Agent 三种方案如何选型" / "主 Agent 与子 Agent 是否该用同等规模的模型"（阿里、滴滴）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- "Agent Loop、长任务 Early Stop"（瓴羊/阿里云 Agent 算法一面 2026-08-27）；"Agent vs Harness"（美团 2026-08-25）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "Agent Harness 是什么？""如何设计一个完整的 Agent Harness？" [设计][应用] — [XL](https://xiaolinnote.com/ai/)；Harness 概念为何近期走红（字节大模型算法三面）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；ML155 称 Harness 与运行时、评估与安全兜底、多智能体编排是 2026 年下半年新增的三个重点方向 [ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- "Claude Code 的上下文管理思路"（快手、蔚来）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；百度 2026-08-21 二面问"Claude Code 数据流、防重复 Tool Call"；腾讯 2026-04-04 问"为什么已有 Claude Code 还要自研 Coding Agent"；蚂蚁 2026-05-20 问"Claude Code/Harness 源码"；字节 2026 社招问 OpenClaw/Hermes 源码 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "在工程实践中，为什么有时候选择「手搓」Agent，而不是直接用成熟框架？" — [XL](https://xiaolinnote.com/ai/)；"LangGraph vs 自研"（小红书 2026-08-06）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 框架题："你用过哪些 Agent 框架？选型是如何选的？""请比较 LangChain 和 LlamaIndex。" — 字节 [NC-BDFW](https://www.nowcoder.com/discuss/878709844730003456)；"LangChain 和 LangGraph 的核心区别是什么？""LangGraph 相比于 LangChain 有哪些核心优势？""LangChain4j 主要解决了哪些问题？" [XL](https://xiaolinnote.com/ai/)；LangChain 与 LangGraph 区别、LangGraph 状态快照（字节、快手、阿里）；Java 生态 Spring AI vs LangChain4j（京东、字节、快手）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)

#### 1.6 多 Agent
- "多 Agent 架构下，主 Agent 和子 Agent 的通信链路应该如何设计？如何处理异常？" [设计] — 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)
- "什么是多智能体系统？优势和复杂性？" — 字节 [NC-BDFW](https://www.nowcoder.com/discuss/878709844730003456)；多 Agent 相比单 Agent 的优势（阿里、美团）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- Multi-Agent 职责划分、子 Agent 通信、多个 Agent 同时修改同一文件时如何避免冲突 — 字节实习一面 [NC-BD2](https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea)
- "Multi-Agent 系统如何处理子 Agent 超时、失联和并发修改冲突？" — [XL](https://xiaolinnote.com/ai/)；主 Agent 如何判断子 Agent 完成并回收结果；异步 JSON 消息通信与失败兜底（蔚来）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- 防止任务拆分阶段错误逐级传播（字节）；Agent 之间上下文/权限/存储隔离（字节、蚂蚁）；多 Agent 是否真的提升效率、如何评估（字节）；子任务中产生新任务时的流转与审批（腾讯广告）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- "Subagent 幻觉、A2A 死循环、共享状态"（字节 2026 社招）；"Subagent 通信与防循环"（腾讯 2026-04-04）；"多 Agent 状态传递与冲突"（MiniMax AI 应用开发一面）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 一手帖：腾讯 AI 应用开发后端实习 2026-04-24 问"多个 agent 怎么编排、agent 失败/中断怎么处理、重试是否安全"；腾讯一面 2026-04-22 问"设计多 agent 系统并写 agent 的 prompt" [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)
- "混合路由和限流器在多 Agent 系统中为什么重要？" — 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)

#### 1.7 记忆与上下文管理
- "如何为 Agent 设计短期记忆和长期记忆系统？" — 字节 [NC-BDFW](https://www.nowcoder.com/discuss/878709844730003456)；"Agent 的长短期记忆系统怎么做的？记忆是怎么存的？粒度是多少？怎么用的？" [XL](https://xiaolinnote.com/ai/)；短期与长期记忆的实现（京东、携程、美团）、记忆模块数据库选型（京东、滴滴）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- "当前对话上下文与长期记忆的区别"、记忆的提取/存储/检索、"为何记忆管理、上下文压缩可交给小模型" — 字节实习一面 [NC-BD2](https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea)；"Context vs Memory"（快手 2026 Data Agent、拼多多 2026-08）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "长期记忆与 RAG 的区别"（阿里千问、通义）；"RAG 是 Agent 记忆的一部分吗？" [ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；[KM](https://notes.kamacoder.com/interview/llm/agent_interview.html)
- "如何在有限的上下文窗口内放入关键内容？如何做短期和长期记忆压缩？" — 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)
- 压缩类：上下文超限怎么处理、压缩时保留什么、压缩后如何避免遗忘早先需求 — 字节实习一面 [NC-BD2](https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea)；"十五、上下文超窗口，滑动窗口和动态摘要各有什么坑？" 美团 [KM-MT](https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html)；"Agent 的上下文压缩阈值应该如何确定？""Agent 记忆压缩通常有哪些方法？" [XL](https://xiaolinnote.com/ai/)；上下文压缩放在链路哪一步（字节）、滑动窗口加摘要落地（字节、腾讯）、压缩时防止关键信息丢失（小红书）、工具结果卸载与压缩（字节、蔚来）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- "三级压缩"（字节 2026-06-04 一面）、"三层记忆 / 分层 Context"（字节 2026-07-01、腾讯 2026-04-04、字节番茄小说 2026-09-19）、"分层记忆"（百度 2026-10-20）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)；[KMI](https://notes.kamacoder.com/interview/llm/)
- "多用户×多角色记忆"（拼多多 2026-08）；"跨周 Memory"（淘宝闪购）；"Markdown Memory/RAG"（蚂蚁财富）；"Rules vs OpenClaw Memory"（MiniMax）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "长对话摘要压缩怎么实现？""对话历史感知改写怎么做？" — 阿里千问一面 [KM-QW](https://notes.kamacoder.com/interview/llm/company/20261015alibaba_qianwen_rag_interview.html)
- "Agent 的多轮对话状态如何管理？如何防止跑偏并支持中断恢复？" — [XL](https://xiaolinnote.com/ai/)
- 一手帖：腾讯二面 2026-04-23 问"上下文大小受什么限制、溢出怎么处理、影响上下文容量的因素" [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)

#### 1.8 RAG
- 基础："什么是 RAG？详细描述一个完整 RAG 系统的详细工作流程？""相比直接微调 LLM，RAG 解决了什么问题？微调和 RAG 各自的优劣势是什么？" [概念] — [XL](https://xiaolinnote.com/ai/)；RAG 基本流程、与微调取舍 — ML265 称"几乎必问"（字节、京东、美团、腾讯）[ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)；"微调（Fine-tuning）和 RAG 的使用场景有什么区别？""Function Calling 与 RAG 的区别和联系是什么？" 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)；RAG vs Fine-tuning — 蚂蚁国际 2025 [2025] [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- RAG vs 长上下文 vs grep（2026 新高频）："四、为什么用 RAG？上下文窗口这么大，为什么不直接把原始文档全丢给 Agent？""五、十篇以上文档或大代码仓库，不考虑 Token 消耗，RAG 和 Agent 自己 grep 各有什么优劣？" — 美团 [KM-MT](https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html)；"上下文窗口很大时为什么还需要 RAG？""RAG 与 Agent 自主文件检索应该如何选型？" [XL](https://xiaolinnote.com/ai/)；蚂蚁 2026-05-20 问 grep vs RAG [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 切块："RAG 中的文档是怎么存的？粒度是多大？详细说说文档切割（Chunking）策略？""怎么规避语义被切割掉的问题？" [XL](https://xiaolinnote.com/ai/)；"七、Markdown 没有标题，怎么切？""十一、父子索引解决什么问题？父块、子块多大，为什么这么定？" 美团 [KM-MT](https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html)；"怎么对文档切片的？""项目里的 CSV 是怎么解析的？" 阿里千问 [KM-QW](https://notes.kamacoder.com/interview/llm/company/20261015alibaba_qianwen_rag_interview.html)；父子索引（快手 2026 Data Agent）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- Embedding / 向量库："在 RAG 中 Embedding 究竟是什么？如何选择和评估一个 Embedding 模型？""什么是向量数据库？有没有做过向量数据库的对比选型？""讲讲你用的向量数据库？数据量级是多大？性能如何？遇到过性能瓶颈吗？" [XL](https://xiaolinnote.com/ai/)；"Embedding / 向量数据库在 RAG 中的作用是什么？如何设计检索策略？" 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)；"你用的 embedding 模型结构是什么？输出向量维度是多少？"（标小米/阿里/百度）[ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)；Milvus（美团 2026-08-25）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 混合检索 / Rerank："向量 + BM25 双路检索，好处是什么？权重怎么分配？" 阿里千问 [KM-QW](https://notes.kamacoder.com/interview/llm/company/20261015alibaba_qianwen_rag_interview.html)；"八、混合检索里，关键词检索有什么优缺点？向量检索有什么缺点？""九、一两百字的 Chunk 和三五字的 Query 怎么比较？" 美团 [KM-MT](https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html)；"什么是多路召回？具体怎么做？""请你介绍一下向量检索和关键词检索的区别？" [XL](https://xiaolinnote.com/ai/)；RRF 融合、Rerank（京东、美团）[ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)；"Rerank 变差的诊断"（腾讯 2026-04-10）、"Rerank/NDCG"（蚂蚁国际 2025）、BM25+双路召回（美团 2025 后端）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- Query 侧："如何润色用户的 Query（Query Rewrite）？目的是什么？" [XL](https://xiaolinnote.com/ai/)；"怎么改写 Query？好处是什么？""Agent 支持多跳检索，具体怎么实现？" 阿里千问 [KM-QW](https://notes.kamacoder.com/interview/llm/company/20261015alibaba_qianwen_rag_interview.html)；"十、除了压缩，长 Query 还怎么处理？" 美团 [KM-MT](https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html)
- 更新与冲突："RAG 知识库如何实现动态与持续更新？""不同来源的文档发生知识冲突时，RAG 应该信谁？" [XL](https://xiaolinnote.com/ai/)；"十二、知识库持续更新，旧向量怎么失效？""十三、召回结果互相矛盾时，谁说了算？" 美团 [KM-MT](https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html)；"RAG 知识库热更新（不停机）" 腾讯 2026-04-24 [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)；"RAG 知识库上线后文档更新了怎么办？不重建全量就搞不定？"（标小米/字节）[ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)
- 权限 / 安全："文档访问控制怎么实现？""一个私人 RAG 系统，为什么要做访问控制？""什么是 PII 脱敏？" 阿里千问 [KM-QW](https://notes.kamacoder.com/interview/llm/company/20261015alibaba_qianwen_rag_interview.html)；ACL 先过滤还是后过滤（字节）[ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)
- 评测与幻觉："怎么量化你的 RAG 效果？""如何规避 RAG 系统中大模型的幻觉？" [XL](https://xiaolinnote.com/ai/)；"RAG召回不准怎么优化？幻觉怎么治理？" 阿里淘天一面 2026 [JJ](https://juejin.cn/post/7674870309703319567)；"如何发现剩余 1%–2% 幻觉"（美团 2026-08-25）、"RAG 消融"（美团 2025）、MRR（阿里云 2025）、"Precision/Recall 怎么算"（字节 2025 四面）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)；"RAG 为什么要加 Rerank？Recall@K 和 Precision@K 怎么取舍？"（标字节）[ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)
- 进阶范式："了解哪些更复杂的 RAG 范式？""在什么场景下，你会选择使用图数据库来增强传统的向量检索？" [XL](https://xiaolinnote.com/ai/)；Graph RAG、Agentic Search（快手、小红书）[ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)；"Agentic RAG 和传统 RAG 有什么区别？" [ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)
- 一手帖：百度大模型生态实习 2026-04-23 问"检索策略和向量相似度度量" [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)
- [2025] CSDN100 的 RAG 段（45–72）覆盖"文档分块策略""检索结果质量评估指标""常用向量数据库""高级 RAG 技术""RAG 与微调比较""企业部署 RAG 的安全与扩展" — [CSDN100](https://blog.csdn.net/teddyandwolf/article/details/148159001)

#### 1.9 评测与可观测
- "如何评估一个 Agent 的效果？评测集和指标怎么设计？""线上 Agent 延迟明显升高时，如何通过 Trace 定位和优化？" [设计][应用] — [XL](https://xiaolinnote.com/ai/)
- Agent 评估维度、planning 能力与幻觉率衡量（阿里）；评测体系构建（Shopee、小红书）；幻觉率的定义与计算（字节）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；ML155 称面试官越来越爱追问指标口径，例如幻觉率如何定义、效果提升用哪个测试集计算 [ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- "怎么评估一个 Agent 好不好？评估体系怎么搭？""非确定性的 Agent 系统怎么测试？"（标字节/阿里/快手）[ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)
- "评测集过拟合"（字节 2026-06-04）、"可观测性与评测"（快手 2026-09-28）、"Agent Eval"（蚂蚁国际 2025）、"RAG Eval"（快手 2026）、"task eval"（百度文心一面 2026-07）、"线上 AUC 下降"（淘宝闪购）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)；[KMI](https://notes.kamacoder.com/interview/llm/)
- "如何评估生图/生成模型的多样性和准确性？" — 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)
- "你说准确率提升，基线、样本量、统计窗口和归因是什么？" — KK 给出的追问探针 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- [2025] "如何评估AI Agent的性能？有哪些关键指标？""如何监控和排查大模型服务的性能问题？需要关注哪些关键指标？" — [CSDN100](https://blog.csdn.net/teddyandwolf/article/details/148159001)

#### 1.10 可靠性 / guardrail / 安全
- 死循环（多源高频）："如果 Agent 陷入了死循环怎么办？"（KM 标"面试必考题"）[KM](https://notes.kamacoder.com/interview/llm/agent_interview.html)；"Agent调了三个工具就死循环了，异常处理在哪写的？" 字节 2026 校招 [JJ](https://juejin.cn/post/7674870309703319567)；"Agent 为什么会出现路径震荡、重复调用和死循环？怎么检测和治理？" [XL](https://xiaolinnote.com/ai/)；死循环原因与三层防护（字节、快手）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；路径震荡 — 字节实习一面 [NC-BD2](https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea)；防重复 Tool Call — 百度 2026-08-21 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "如何解决 Agent 的上下文漂移问题？如何防止工具调用出现幻觉？""在长任务或复杂任务中，如何保证模型不会偏离原始目标？" — 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)
- "Agent 的「任务幻觉」是什么？如何避免没有执行工具却声称任务已经完成？" — [XL](https://xiaolinnote.com/ai/)
- Prompt Injection："什么是 Prompt Injection？怎么防御？"（KM 标"面试高频考点"）[KM](https://notes.kamacoder.com/interview/llm/agent_interview.html)；Prompt Injection 与间接注入（钉钉、小红书）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；腾讯 2026-05-21、快手 2026 Data Agent 一面 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)；[2025] "什么是提示注入攻击？如何防范？" [CSDN100](https://blog.csdn.net/teddyandwolf/article/details/148159001)
- 高危操作 / HITL："如果 Agent 要操作数据库，怎么保证它不会误删数据？" [KM](https://notes.kamacoder.com/interview/llm/agent_interview.html)；"大模型或 Agent 连接数据库时，如何防止越权、敏感数据泄漏和查询幻觉？""Agent 应该在什么情况下请求人工确认？" [XL](https://xiaolinnote.com/ai/)；权限控制、资金类高危操作限制（腾讯、京东）；HITL 暂停与恢复（蔚来）；高危操作人工审核边界（蚂蚁、唯品会）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；淘宝闪购 2026-04-30 一面问 HITL、风险分级、熔断、沙箱、审计；小红书 2026-08-06 问"写操作边界" [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)；一手帖：腾讯 2026-04-24 问"跨境汇款流程中 agent 超时/失败如何保证资金安全" [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)
- "如何处理工具调用的安全问题（如 Key 泄露、敏感信息暴露）？" — 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)；"如何确保 Agent 的行为是安全、可控的？" 字节 [NC-BDFW](https://www.nowcoder.com/discuss/878709844730003456)
- 断点恢复 / 幂等："进程在第七步崩溃，如何从 Checkpoint 恢复而不重复副作用？" [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)；断点恢复与整体兜底（字节、京东）；生产环境四类典型故障（字节）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；"LLM API 失败的感知与定位、限流之外的异常"（拼多多 2026-08）、"全自动任务可靠性"（蚂蚁财富）、"非 AI fallback"（京东 2026-02-10）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "你们生产环境的 Agent 踩过什么坑？" — [KM](https://notes.kamacoder.com/interview/llm/agent_interview.html)

#### 1.11 成本与延迟
- "如何降低推理成本？在多任务、多 Agent 系统中如何权衡效率和准确性？" — 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)
- "Agent 的 Token 消耗很大，怎么优化成本？" — [KM](https://notes.kamacoder.com/interview/llm/agent_interview.html)；"一次对话花了10万Token怎么办？怎么控制Agent调用成本？"（标字节/阿里）[ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)
- Token 计费、成本治理（飞猪、百度、腾讯、字节）[ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)；"Token 治理"（字节番茄小说 2026-09-19）、"QPS 压测、KV Cache"（快手 2026-09-28）[KMI](https://notes.kamacoder.com/interview/llm/)
- 为何先用小模型做初步召回（车载场景）— 字节实习一面 [NC-BD2](https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea)；"低延迟、Prefill/Decode、KV Cache"（小米 2025）、"自托管 vs API"（小米 2026）、"十几万请求扩容"（小红书 2026-08-06）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "有没有用过大模型的网关框架？网关层解决了什么问题？" [XL](https://xiaolinnote.com/ai/)；统一大模型服务架构与模型选型（京东科技、腾讯、字节）[ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)
- "Agent 商业化成本的走势"（拼多多）[行为] [ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- [2025] "在构建大模型应用时，如何有效管理成本？有哪些优化策略？""如何有效处理API调用的速率限制问题？" [CSDN100](https://blog.csdn.net/teddyandwolf/article/details/148159001)

#### 1.12 系统设计题（详见 Key Question 2）
- ML155 称系统设计是"高频压轴"：多 Agent 自动 PR 系统（B 站）、AI 客服 Agent 与 RAG 分块（阿里）、视频问答 / 股票分析 Agent（阿里、快手、京东）、AI 代码 Review Agent 只提意见不改代码（字节）、企业知识库 Agent 与权限隔离（Shopee、字节）、内容标注 Agent（抖音）、电商客服 Agent（通义）、运维故障排查 Agent 与高危操作管控（字节、唯品会）、多 Agent 文献调研系统、Deep Research / Manus 产品对比（抖音）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- "设计一个企业级 Agent 系统，需要考虑哪些点？" [KM](https://notes.kamacoder.com/interview/llm/agent_interview.html)
- "只有 LLM API 与 VS Code 时如何造 Agent"（百度 2026-08-21 二面）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- "设计一个 Coding Agent，和 Claude Code / Codex 有什么区别？"（标字节）、"画 Agent 的路由/管理/执行三层架构"（标阿里）[ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)

#### 1.13 手撕代码（详见 Key Question 2）
- Agent 工程类：脱离框架手写最小 ReAct Agent（ML155 称 2026 下半年新出现的高频题）、上下文压缩 / 滑动窗口摘要、完整工具调用链路、多 Agent 消息路由与超时重试、死循环检测器 [ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；[ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)
- 通用算法：反转链表、链表相加、排序链表、二叉树层序遍历、接雨水、无重复字符最长子串、Top-K、字符串解码 [ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)
- 一手帖：判断平衡二叉树（字节实习一面）[NC-BD2](https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea)；最大岛屿面积 DFS、两个栈实现队列并支持删除中间元素（腾讯 2026-04）[NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)；最小覆盖子串（快手）[KMI](https://notes.kamacoder.com/interview/llm/)；三数之和（华为双机位）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)

#### 1.14 项目深挖（详见 Key Question 3）
- ML155："深挖简历中的 Agent 项目"是所有大厂面试的主线；工具调用、记忆与上下文、多智能体三块几乎每题都会追问"你们项目里具体怎么做的" [ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)

#### 1.15 行为面 / 软性问题
- 现场演示自己搭建的 Agent；常用 AI 编程工具及使用方式；Skill 的使用与效果验证（快手）；AI Coding 中避免改坏历史功能（阿里）；Plan 模式与 Agent 模式区别（小红书）；对框架与 Skill 概念快速迭代的看法（字节）；To C 与 To B 的 Agent 哪个先爆发（腾讯）；如何甄别 AI 输出的幻觉（OPPO）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- "平时用什么 AI 技术？""界面类的 AI 产品用过哪些？" — 阿里千问一面 [KM-QW](https://notes.kamacoder.com/interview/llm/company/20261015alibaba_qianwen_rag_interview.html)
- "结合开发经历，谈谈传统 Web 应用和 AI Agent 应用有什么不同？" — 字节 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)
- 一手帖：腾讯 2026-04-21 问"项目是否自发做的、是否自学 AI"；百度产品实习问"喜欢的产品优缺点、C 端 vs B 端差异、对百度的看法、职业规划、信息来源" [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)
- 项目是否上线 / 日均请求："十六、系统上线了吗，日均多少请求？" 美团 [KM-MT](https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html)；"为什么尚未上线"（字节 2025 四面）、"项目有没有真实用户、为什么没上线"（腾讯 2026-04-24）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)；[NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)
- AI 编程题库（XL 单列 14 题）："Vibe Coding 和 Spec 驱动开发有什么区别？""Coding Agent 如何在大型代码仓库中定位相关代码？""如何审查 AI 生成的代码，发现看起来合理但实际错误的实现？" [XL](https://xiaolinnote.com/ai/)；美团 2026-08-25、字节 2026-06-04 也问 Vibe/Spec Coding、SDD [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)

#### 1.16 跨来源复现次数（粗略统计，按本文件引用的独立来源数）
- ReAct 定义 / 组成 / 与 Plan-and-Execute 选型：≥8 来源（KM、ML155、XL、NC-BDFW、NC-BD2、JJ、KK 多公司、ADG）
- MCP vs Function Calling（以及 vs Skills / A2A）：≥7 来源（KM、ML155、XL、KM-BD、NC-BDFW、KK、ADG）
- 死循环 / 路径震荡 / 重复调用：≥7 来源（KM、ML155、XL、JJ、NC-BD2、KK、ADG）
- 上下文压缩 / 滑动窗口 + 摘要 / 压缩丢信息：≥7 来源（KM-BD、NC-BD2、KM-MT、XL、ML155、KK、ADG）
- 长短期记忆设计与存储：≥7 来源（NC-BDFW、XL、ML155、NC-BD2、KM-BD、KK、ADG）
- RAG vs 微调：≥5 来源（XL、ML265、KM-BD、KK、CSDN100）
- RAG 切块 / 父子索引 / 无标题 Markdown：≥5 来源（XL、KM-MT、KM-QW、ML265、KK）
- 混合检索 BM25+向量 / Rerank / RRF：≥6 来源（KM-QW、KM-MT、XL、ML265、KK、ADG）
- Prompt Injection：≥5 来源（KM、ML155、KK、CSDN100、ADG）
- 高危操作 / 数据库误删 / HITL：≥5 来源（KM、XL、ML155、KK、NC-TXBD、ADG）
- Agent 评测体系 / 指标口径：≥5 来源（XL、ML155、KK、ADG、CSDN100）
- Agent vs Workflow：≥4 来源（KM、ML155、XL、KK）
- LangChain vs LangGraph：≥4 来源（XL、ML155、ML265、ADG）
- Harness Engineering / Claude Code 源码：≥4 来源（XL、ML155、KK 多公司、KMI）
- 手写最小 ReAct Agent：≥3 来源（ML155、ML265、ADG）

### Inferences
- 2025 题库（CSDN100、WD）仍以 LangChain 组件名词（LCEL、Output Parser、Text Splitter、Memory 类型）和 RAG 基础为主；2026 一手面经几乎不再问 LangChain 组件，转向 Harness、Claude Code 机制、grep vs RAG、Skill vs Tool、上下文三层压缩等"运行时工程"问题
- 二手题库（ML155 / ML265 / ADG）之间的公司标签高度重合但相互无法核实，频率判断更应依赖一手帖（NC-BD2、NC-TXBD、KM-MT、KM-QW、KM-BD）和 KK 逐条带原帖链接的汇总
- 结构化输出作为独立题目在 2026 一手面经中极少出现，更多内嵌于"工具返回结构设计"和"JSON 解析失败降级"

### Gaps
- 无法打开两篇一手牛客帖正文（"大模型应用开发面经（5年经验）" https://www.nowcoder.com/feed/main/detail/129eaa1c20444651ac3b932e200d3da4；美团 Agent 开发一面 2026-08-25 https://www.nowcoder.com/feed/main/detail/58159306df52463ab75d72daa80d66df），页面为 JS 渲染，只拿到标题；其内容依赖 KK 的二手摘要
- 关于 Kimi/月之暗面、DeepSeek、MiniMax、阶跃、智谱、商汤的面经只有 KK 的二手摘要（Kimi 条目 KK 自称模板化明显，不宜作核心证据）；未找到 DeepSeek 和商汤应用岗的面经
- ADG 的公司标签和"各厂风格"全部无出处，本文件引用时均标注了"标 X 公司"以示区别

---

## Key Question 2：面经里的 loop 结构（几轮、每轮考什么、手撕什么、系统设计题是什么）

### Takeaway
应用岗普遍是 2–4 轮技术面 + HR 面，一面以项目追问 + 一道 LeetCode Medium 算法题为主，二面加深到单个模块并加场景题，三面/主管面问宏观架构与从零设计；Agent 类手撕（手写最小 ReAct 循环、死循环检测器）在 2026 下半年才出现，通用算法题（链表/树/滑窗）仍是主流。

### Cited Findings
- 轮次样本：字节 2026 Agent 实习两轮；字节 2025 大模型四轮；百度文心一面偏后训练、二面偏系统；MiniMax 有一面 + 平台研发一面和二面；华为有双机位编程与主管面 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 字节 Seed 官网：一般为 3–5 轮技术面 + 1 轮 HR 面；快手样本 2 轮技术面；腾讯 WXG 样本一面 2.5h、二面 2.5h、委员会面、HR；华为个人样本：机试 → 测评 → 技术面 → 主管面；KK2 总结的通用模式：笔试或测评 → 1–3+ 轮技术面 → 主管面 → HR [KK2](https://keepkeen.github.io/blog/llm-recruit-interview-roundup/)
- 字节 Agent 开发暑期实习（2026-05-06 面经）：投递 3.25 → 一面 4.5 → 二面 4.8 → 三面 4.15（未通过但转岗）→ 四面 4.18（转岗加面）→ HR 面 4.18 → offer 审批 4.20 → 正式 offer 4.25；全程 22 道题从 Prompt → 输出控制 → Agent 系统设计 → 模型优化 → 记忆 → 工程落地；原文未写手撕 [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)
- 字节 AI Agent 日常实习（一手）：一面 8月13日 20 题，第 1 题介绍 Coding Agent 项目及架构，中间 18 题覆盖 ReAct/Plan、压缩、记忆、MCP、Prompt Cache、多 Agent 冲突，第 20 题算法题"判断二叉树是否为平衡二叉树"；二面 8月16日，作者称"二面会明显问得更深"，预计深入一个模块并加场景题；评论区有人称"实习问得跟社招似的" [NC-BD2](https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea)
- 腾讯 AI 应用开发后端实习一面（2026-04-24）：项目追问（有无真实用户、为何未上线、训练样本来源）→ 多 agent 编排与失败重试 → 场景题（跨境汇款 agent 超时/失败保证资金安全）→ RAG 热更新 → 手撕 DFS 最大岛屿面积 → 设计模式追问 [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)
- 腾讯 AI 应用开发实习一面（2026-04-21）：分库分表、深分页、MySQL 隔离级别、SQL 题、Redis Lua 分布式锁、RocketMQ、线程/进程/协程 → "agent 的 thinking 如何决定调工具还是直接回复" → 手撕两个栈实现队列并支持删除中间元素 [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)
- 腾讯二面（2026-04-23，已挂）：Spring IoC/AOP/循环依赖、volatile、JVM、epoll、TIME_WAIT → agent vs 传统 LLM、核心组件、上下文限制与溢出、Transformer 概述 [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)
- 阿里千问 AI 应用研发一面（2026-10-15）：约 40 分钟、15 题全部围绕候选人私人 RAG 项目，无编程题，结果进二面 [KM-QW](https://notes.kamacoder.com/interview/llm/company/20261015alibaba_qianwen_rag_interview.html)
- 美团 Agent 开发日常实习（2026-10-21）：16 题全部围绕智能运维 Agent 项目，无算法题；面试官沿"为什么检索、怎么检索、证据能否相信"链条每题追问约三层 [KM-MT](https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html)
- 美团 Agent 开发一面（2026-08-25）：RAG vs 长上下文、RAG vs grep、无标题 Markdown 切分、幻觉发现、单/多 Agent、Plan-and-Execute vs ReAct、Agent vs Harness、Prompt Cache、Milvus、协程/线程、Vibe/Spec Coding + 两道算法题 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 百度文心（2026-07-02/03）：一面偏后训练（SFT 何时足够、reward/verifier、reward hacking、PPO/GRPO、task eval）；二面偏系统（Action 设计、长文本、Context/Memory、冲突指令、Harness/Loop、Skill 为何在系统层、Claude Code/Codex/GLM 比较）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 百度 Agent 研发一面（2026-08-18）：Prompt vs Context vs Harness、上下文压缩、Coding Agent、手撕前序 + 层序遍历 [KK2](https://keepkeen.github.io/blog/llm-recruit-interview-roundup/)
- 华为云软件 AI 技术应用（2026-05-14）：双机位编程"三数之和"、项目架构与个人贡献；主管面问 Agent 宏观架构与从零设计 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 阿里云（2026-04-10）：现场用 AI Coding 完成功能后人工 Review [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 蚂蚁 2026-05-20 一面：Skill、Spring AI、Claude Code/Harness 源码、grep vs RAG、混合召回，随后转入网络、MySQL、Spring 和 AI Coding；蚂蚁财富 2026-08-29 同时考 ACID、数据库日志、Redis、MQ、限流 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 笔试：美团 2025-08 个人样本 8 道 ML 选择 + 4 道编程 [2025]；华为 2025 ACM 风格（栈/哈希/堆）[2025]；拼多多 2026-08 四道 ACM 题；京东 2026-09 NumPy 逻辑回归 + AI Coding；美团 2026-09 一候选人账：10 道选择（30 分）+ 1 道算法（20）+ Kaggle 式 ML AI Coding（50）[KK2](https://keepkeen.github.io/blog/llm-recruit-interview-roundup/)；美团 2026 校招企业页：大模型选择题 + 算法 + AI Coding [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 手撕分类：ML265 将手撕分为模型类（MHA 可扩展 cross-attention、MoE）、Agent 类（不依赖框架手写最小 ReAct Agent、上下文压缩与滑动窗口摘要）、通用题（反转链表、链表相加、排序链表、二叉树层序遍历、接雨水、无重复字符最长子串、最大正方形、路径总和 III、整数开方、Top-K、字符串解码、正则匹配连续数字）；称 Agent 工程类"近一年权重明显上升" [ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)
- ML155 Agent 手撕：最小 ReAct Agent、上下文压缩/滑动窗口摘要、完整工具调用链路、多 Agent 消息路由与超时重试、Java 动态代理与 Agent 动态调度类比、死循环检测器（给定调用轨迹判断是否重复调用）；原文未给公司标签 [ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)
- ADG（无出处）：手写 ReAct Agent 核心循环标"字节/阿里/美团高频"；LeetCode Medium 为主（反转链表、层次遍历、编辑距离、括号生成、不同路径变式），偶尔 Hard（地下城游戏）；numpy 手写 MHA / 位置编码；称系统设计题"通常在二面或三面出现"，字节追问可达"5–6 层" [ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)
- 系统设计题清单（ML155 "高频压轴"）：多 Agent 自动 PR 系统（B 站）；AI 客服 Agent 与 RAG 分块、向量模型选择（阿里）；视频问答、股票分析 Agent 全链路（阿里、快手、京东）；AI 代码 Review Agent 只提意见不改代码（字节）；企业知识库 Agent 完整链路与权限隔离（Shopee、字节）；内容标注 Agent（抖音）；电商客服 Agent 需求理解与成功指标（通义）；运维故障排查 Agent 与高危操作管控（字节、唯品会）；多 Agent 文献调研系统；从需求到落地的 Agent 项目全流程（阿里云）；Deep Research 功能对比、Manus 评价与改进（抖音）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；ML265 另加"覆盖训练到服务的一体化 AI 开发平台"（腾讯混元）、"支付接口超时的排查流程"（字节、唯品会）[ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)
- KK 系统设计清单：百度"只有 LLM API 与 VS Code 时如何造 Agent"、C 端 Agent 模块设计；华为主管面 Agent 宏观架构；小红书十几万请求扩容；京东告警 Agent 的 fallback 与发布期准确性；淘宝闪购 HITL/风险分级/熔断/沙箱/审计；蚂蚁全自动任务可靠性；美团 RAG 切分与幻觉发现流程 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- ADG 系统设计（无出处）："设计一个AI爬取字节视频的Agent系统"（字节）、"画 Agent 的路由/管理/执行三层架构"（阿里）、"设计一个 Coding Agent，和 Claude Code / Codex 有什么区别？"（字节）[ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)
- 一手帖系统设计：腾讯 2026-04-21 "2 秒/10 秒/90 秒三级缓存各对应什么"；腾讯 2026-04-22 "管理五台服务器并实现负载均衡"；百度 2026-04-23 "工厂模式以外的设计模式" [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)
- 海外对照：Amex AI engineer 一面与 principal engineer 聊 1 小时，行为题 + AI 系统题（agent 与 LLM、RAG 区别，agent 如何获得能力）[1point3acres](https://www.1point3acres.com/bbs/thread-1171655-1-1.html)；Anthropic 技术电面聚焦用 Tool Use 和 Agent Loop 构建 LLM agent [1point3acres](https://www.1point3acres.com/interview/thread/1178346)；一位面了 20 多家 AI startup 的候选人称 70–80% 公司至少一轮 coding challenge，常见 agentic AI 系统设计、takehome 和算法题 [1point3acres](https://www.1point3acres.com/bbs/thread-1148857-1-1.html)

### Inferences
- 应用岗一面的"模板"相当稳定：自我介绍 → 项目追问（15–20 题）→ 1 道 LeetCode Medium；是否有算法题取决于团队（美团日常实习、阿里千问一面均无，字节/腾讯/百度/快手/华为有）
- 后端背景团队（腾讯、蚂蚁、美团、京东、小红书）会把 Java/MySQL/Redis/MQ 八股与 Agent 题各占一半；算法背景团队（百度文心、快手二面、阶跃、智谱）会把 PPO/GRPO 混进来
- "手写最小 ReAct Agent"目前只见于二手题库，一手帖中尚未见到，推测仍是少数团队的做法

### Gaps
- 没有找到任何一份完整记录"某公司 Agent 应用岗 三面 + HR 面逐轮题目"的一手帖（KM-BD 四面只给题目合集，不分轮）
- 二面/三面的具体内容普遍只有"更深、加场景题"的描述，缺少逐题记录
- 1point3acres 帖子正文返回 403，只能依据搜索摘要

---

## Key Question 3：面试官如何深挖项目（为什么这样设计、怎么评测、怎么处理幻觉、怎么处理失败）

### Takeaway
深挖套路高度一致：先确认项目真实性（用户/上线/日均请求/个人代码边界），再沿失败路径追问（超时、重复调用、压缩丢信息、并发写冲突、崩溃恢复），再逼候选人做比较（为什么不用 Workflow/规则/小模型/长上下文/grep），最后追问指标口径（幻觉率怎么定义、基线和测试集是什么）。

### Cited Findings
- KK 总结的四层推进：1) 确认项目真实性（用户、输入输出、个人代码边界、实际结果）；2) 沿失败路径追问（超时、重复调用、压缩丢信息、并发写冲突、进程崩溃恢复）；3) 要求比较（Agent vs Workflow、RAG vs 长上下文 vs grep、Permission vs Sandbox）；4) 基础与现场题 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- KK 给出的追问探针原文："模型为什么选择这个工具，Schema 谁定义，错误码如何返回？""你说准确率提升，基线、样本量、统计窗口和归因是什么？""哪个线上 bad case 最难，最终确认根因的证据是什么？""为什么不用 Workflow、规则、小模型或一次 API 调用？""工具超时或返回部分结果怎么办？进程在第七步崩溃，如何从 Checkpoint 恢复而不重复副作用？" [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 美团智能运维 Agent 项目（一手改写）：第 2 题"为什么想做这个智能运维 Agent？"→ 第 3 题"整个系统怎么运转，中间件选型依据是什么？"→ 第 4 题"为什么用 RAG？…为什么不直接把原始文档全丢给 Agent？"→ 第 6 题"切块、索引、存储、检索、排序，哪几步对效果影响最大？"→ 第 9 题追问"对比过长 Query 和三字 Query 的向量召回吗？"（候选人承认没做过实验）→ 第 12 题"旧向量怎么失效？"→ 第 13 题"召回结果互相矛盾时，谁说了算？"→ 第 14 题"工具调用返回结构怎么设计？"→ 第 16 题"系统上线了吗，日均多少请求？"；面试官每题追问约三层，沿"为什么检索、怎么检索、证据能否相信"链条 [KM-MT](https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html)
- 阿里千问私人 RAG 项目（一手）：从"解释一下你的项目"→"CSV 是怎么解析的"→"怎么切片"→"双路检索权重怎么分配"→"多跳检索怎么实现"→"怎么改写 Query"→"文档访问控制怎么实现"→"一个私人 RAG 系统，为什么要做访问控制？"（文章称这是最有辨识度的追问：场景为什么需要、不做会出什么问题、做了怎么验证）→"什么是 PII 脱敏"→"长对话摘要压缩"→"项目最难的点在哪，怎么解决"→"用了 Harness，介绍一下数据怎么走" [KM-QW](https://notes.kamacoder.com/interview/llm/company/20261015alibaba_qianwen_rag_interview.html)
- 字节 Agent 实习一面（一手）：开场"介绍做过的 Coding Agent 项目及整体架构"，随后 18 题全部是该项目会碰到的机制问题（压缩保留什么、压缩后如何不遗忘早先需求、路径震荡原因、记忆提取存储检索、工具多时提示词过长、工具返回过大、多 Agent 改同一文件冲突），收尾"若优化一个 Coding Agent，会优先从哪些方向入手" [NC-BD2](https://www.nowcoder.com/feed/main/detail/8a553bb6ea8445d0b0abe11e87614cea)
- 腾讯 2026-04 一手帖：项目是否有真实用户、为什么没上线、训练样本从哪来、模型有没有调过；多 agent 项目的团队角色、特征来源与生成、模型输出如何回流；投资分析项目的模型供应商、自测结果、股票数据来源；"项目是不是自发做的" [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)
- 字节 2025 四面 [2025]："为什么不用闭源 SOTA""为什么尚未上线""Precision/Recall 怎么算"；字节 2026-07-01："候选人项目与 Claude Code、Codex、Hermes 的比较，Harness 取舍"；字节 2026 社招："Memory/RAG 的实际用户收益"；京东 2026-02-10 告警 Agent："节假日/发布期准确性、非 AI fallback、4000 页多模态/GB 级文档、RAG 更新"；小米 2026："代码生成为什么要微调而不能只用 Prompt/Spec Coding""静态 Spec 与动态环境反馈""自托管 vs API"；MiniMax："PRD/design 如何防需求丢失、Task 粒度、用户反馈有效性" [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 评测追问：ML155 称面试官越来越爱追问指标口径，例如幻觉率如何定义、效果提升用哪个测试集计算；"幻觉率的定义与计算"（字节）、"多 Agent 是否真的提升效率，如何评估"（字节）[ML155](https://www.mianlingai.com/topics/ai-agent-interview-questions-2026/)；"评测集过拟合"（字节 2026-06-04）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 幻觉追问："如何发现剩余 1%–2% 幻觉"（美团 2026-08-25）、"Subagent 幻觉"（字节社招）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)；"RAG召回不准怎么优化？幻觉怎么治理？"（阿里淘天一面）[JJ](https://juejin.cn/post/7674870309703319567)；"十三、召回结果互相矛盾时，谁说了算？"参考答案要点：模型解释证据，治理规则决定哪些证据合格 [KM-MT](https://notes.kamacoder.com/interview/llm/company/20261021meituan_agent_intern_interview.html)
- 失败处理追问："Agent调了三个工具就死循环了，异常处理在哪写的？"（字节 2026 校招）[JJ](https://juejin.cn/post/7674870309703319567)；"agent 失败或中断怎么处理，重试是否安全"（腾讯 2026-04-24）[NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)；"LLM API 失败的感知与定位、限流之外的异常"（拼多多）、"工具冲突、超时降级"（淘天/夸克 2025）[KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 各厂追问风格（ADG，无出处）：字节追问层层深入 ReAct 细节与工具调用循环；阿里要求画架构图并考察 RAG 工程化；小米项目深挖加八股原理、追问到代码级、反感背答案；美团后端基础与 Agent 各占一半 [ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)
- 一篇 CSDN 文章称 LLM 应用岗一面常由 leader 直接围绕项目发问，只有一面也不懂大模型时才会考八股，"八股文明显比之前少很多"（二手观点）[aicoding.csdn](https://aicoding.csdn.net/6a68709110ee7a33f2935891.html)

### Inferences
- 对候选人最致命的追问是"真实性 + 指标口径"两类：没上线、没有真实用户、没做过对比实验（如美团第 9 题候选人承认未做）会被直接暴露
- "为什么不用更简单的方案"是统一的反向追问：为什么不用 Workflow / 规则 / 小模型 / 长上下文 / grep / 闭源 SOTA / 一次 API 调用；准备项目时需要为每个组件准备一个"被替代方案"的对比

### Gaps
- 没有找到面试官视角（出题人）公开写的 Agent 岗评分标准或追问清单；KK 的"四层推进"是作者归纳
- 阿里"画架构图"的具体要求只见于 ADG（无出处）

---

## Key Question 4：哪些 Transformer / 注意力 / 采样 / 微调 八股仍出现在应用岗，哪些只在算法岗

### Takeaway
应用岗会被"基础覆盖"式地问到 Transformer/Attention 概述、Temperature/Top-P/Top-K、KV Cache / Prompt Cache、LoRA 与 RAG vs 微调的取舍、SFT vs RLHF 适用场景；深入的 GRPO/PPO loss、reward hacking、MHA/GQA 手撕、分布式训练、显存估算基本只出现在算法 / 后训练 / Infra 轨，但腾讯、快手、百度等"混合团队"会把 GRPO/PPO/QLoRA/量化带进应用岗面试。

### Cited Findings
- KK 结论原文："应用岗的 Transformer 与训练类问题多为基础覆盖，深度题集中在算法或后训练岗"；应用岗出现的底层知识样本：字节 2025 Transformer/Attention、LoRA/SFT、RAG 的 Embedding 与 Prompt 评测；阿里云 2025 Transformer、Embedding、MRR、多路召回；小米 2025 Prefill/Decode、KV Cache、Python GIL；华为 2025 边缘设备模型规模；腾讯应用一面 2026-04-04 GRPO/PPO、QLoRA、量化；快手 2025 Data Agent 模型微调；蚂蚁算法轨 DPO/PPO/GRPO、FlashAttention、蒸馏 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- KK2 八股分布：Transformer/attention（MHA、RoPE、RMSNorm、MoE）在基模和算法轨为 P0，也出现在快手、字节、DeepSeek 面试；LoRA 出现在后训练和应用微调面试（阿里、智谱、快手、腾讯）；RLHF/PPO/DPO/GRPO 在后训练和 RL 岗为 P0（智谱、阿里、腾讯、字节、DeepSeek 样本）；采样（top-k/top-p）归在模型手撕类无岗位映射；RAG/工具/记忆/评测在 Agent/RAG 应用岗为 P0；KV Cache/量化/推理在 AI Infra 为 P0 [KK2](https://keepkeen.github.io/blog/llm-recruit-interview-roundup/)
- 算法岗样本（KK2）：腾讯 WXG 一面手撕 AdamW、两道 SQL、Q-learning vs DQN，二面 GRPO/PPO/RM、bf16/fp16/fp32、LoRA、手撕 PPO；字节 26 届算法二面 GRPO、DeepSeek-R1 reward 设计、手撕 MHA；阿里一面写出 GRPO loss、ε=0.2 来源、reward hacking；快手 26 届大模型应用（2025-09-27）一面 MHA、RMSNorm vs LayerNorm、KV Cache + 手撕字符串全排列与二叉树序列化，二面数据去重、softmax 加速、LoRA vs Prompt Tuning [2025]；智谱 2025-06-08 一面 Tokenizer/RAG 伪代码、NL2SQL、PPO/DPO/GRPO、KL 估计、LoRA/P-tuning v2、MLA [2025] [KK2](https://keepkeen.github.io/blog/llm-recruit-interview-roundup/)
- 应用岗一手帖出现的八股：字节 Agent 开发四面"SFT 和 RLHF 哪个更适合快速迭代？在基模能力越来越强的情况下，这两者的破局点是什么？""微调（Fine-tuning）和 RAG 的使用场景有什么区别？" [KM-BD](https://notes.kamacoder.com/interview/llm/company/20260506bytedance.html)；字节框架帖"有微调过 Agent 能力吗？数据集如何收集？"（涉及 loss masking、LoRA/QLoRA）[NC-BDFW](https://www.nowcoder.com/discuss/878709844730003456)；腾讯二面 2026-04-23 要求"Transformer 概述"；百度多模态算法实习（算法岗）问 LoRA 收敛、极端 alpha、灾难性遗忘、DPO reward 变体 [NC-TXBD](https://www.nowcoder.com/discuss/878600528970735616)；字节番茄小说 Agent 实习一面"QKV 与算法题现场回答"、快手 Agent 开发一面"KV Cache" [KMI](https://notes.kamacoder.com/interview/llm/)
- 题库划分：ML265 九大主题中 1（Transformer）、2（预训练/分布式/推理）、3（SFT/LoRA/RLHF/PPO/DPO/GRPO）归算法/训练；4（RAG）、5（Agent）、6（应用工程）归应用；手撕中 MHA/MoE 归算法、ReAct/上下文压缩归应用；具体题：LoRA 原理与框架选型（美团、字节、快手、腾讯）、PPO/DPO/GRPO 差异（阿里、字节、富途）、vLLM 与 PagedAttention（腾讯、快手、字节）、Qwen3 推理/非推理模式切换（抖音）、MoE vs 稠密（拼多多）[ML265](https://www.mianlingai.com/topics/llm-agent-interview-questions-2026/)
- XL 的"大模型工程"专题（面向应用岗）保留的八股 23 题含：Transformer 基本原理、MHA 局限与 MQA/GQA/Flash Attention、位置编码 sin/cos vs RoPE vs ALiBi、分词器、Scaling Law 与涌现、微调方案、LoRA 优点、SFT 之后的 Post-Training（RLHF/DPO/GRPO/拒绝采样关系）、DPO vs PPO、解码策略（贪心/Beam/采样）、温度/Top-P/Top-K、KV Cache / Prompt Caching、量化 INT8/INT4/AWQ/GPTQ、MoE（DeepSeek V3 / Qwen 为什么用）、部署方案选型、评测指标、Lost in the Middle [XL](https://xiaolinnote.com/ai/)
- ADG（无出处）列的应用岗 LLM 基础题："注意力机制为什么要除以根号 d_k？""LayerNorm 是对哪个维度做归一化？和 BatchNorm 有什么区别？""RoPE 为什么能实现相对位置编码？""LoRA 的原理是什么？""SFT 和 RLHF 的作用有什么区别？PPO、GRPO、DPO 分别是什么？" [ADG](https://adg.csdn.net/6a82f7fa10ee7a33f29c2607.html)
- 模型公司算法轨（KK）：阶跃星辰问工具调用训练的 reward、数据配比、TRL/verl、LoRA 初始化、DPO 数据、采样；智谱 Agent 算法问 DPO loss、负样本、GUI 长链正负样本长度、GRPO、Tool Response 是否进 loss mask、MCP 训练数据、KV Cache、流式 Tool 输出 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- [2025] CSDN100（应用开发题库）中 Transformer 段占 73–96 共 24 题（为何 Decoder only、Self-Attention 公式与根号 dk、MHA、LayerNorm vs BatchNorm、位置编码、Mask、残差、FFN、复杂度、量化、LoRA、学习率），微调段 15–24 共 10 题（训练阶段、指令微调、数据集选择、评估）[CSDN100](https://blog.csdn.net/teddyandwolf/article/details/148159001)
- WD（15.3k star）目录以算法为主：01 基础、02 架构（attention、MHA/MQA/GQA、解码策略、BERT、llama/chatglm、MoE）、04 分布式训练、05 有监督微调（lora、adapter）、06 推理（vllm、量化）、07 强化学习（PPO、RLHF、DPO）、08 检索增强 RAG + Agent（仅两条）、09 评估与幻觉、10 应用（CoT、LangChain）；自述面向"算法（应用）工程师" [WD](https://github.com/wdndev/llm_interview_note)

### Inferences
- 应用岗八股的"安全底线"约等于 XL 大模型工程专题 23 题：能讲清 Attention/位置编码/解码参数/KV Cache/LoRA/SFT vs RLHF vs DPO 的概念和取舍即可，不要求推导 loss 或手撕 MHA
- 2025 的应用题库（CSDN100）Transformer 八股占比 24%，2026 的应用题库（XL）降到 23/124 ≈ 19%，且 XL 的八股题更偏"应用场景下怎么选"（部署方案、量化选型、MoE 为什么），印证八股在应用岗的"基础覆盖化"
- "有没有微调过 Agent / 工具调用能力、数据怎么收集"是算法八股渗入应用岗的主要入口（字节、小米、快手、阶跃、智谱均问）

### Gaps
- 无法量化"八股在应用岗出现的比例"，只有 KK 的定性结论和题库占比
- DeepSeek 面经 KK2 标注含课程广告、可信度 B-/C；未能获取其原文

---

## Key Question 5：2025–2026 JD 要求的技能（LangChain / LangGraph / Dify / Coze / MCP / RAG / 向量库 / 评测）

### Takeaway
中小公司 JD 的共同底线是"熟悉至少一种应用框架（LangChain / LlamaIndex / Dify）+ RAG + 向量库 + Prompt 工程 + Python（或 Java/Go）"，Agent、多智能体、LoRA 微调常作加分项；大厂 2026 Agent 岗 JD（京东、百度、字节 Seed）则已经写成 Skills / Subagents / MCP / A2A / Sandbox / Memory / Context Engineering / Harness Engineering / Eval 回放 / Trace。

### Cited Findings
- 京东 Agent 专家 JD（KK 摘要，原页需登录）：Model Gateway、Agent Runtime、上下文状态、工具/插件注册、Streaming、Semantic Cache、熔断、Eval/回放、Trace/Metric/Log [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 京东大模型智能体岗 JD（KK 摘要）：Skills、Subagents、MCP/A2A/A2UI、Coding Agent、Sandbox、Memory、Context Engineering、Harness Engineering [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 百度 2027 AIDU 智能体算法岗（KK 摘要）：感知—决策—执行、多 Agent、长期记忆、规划/工具/反思/代码、RAG+Agent，以及成功率、延迟、成本和体验的 Eval [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- 字节 Seed Early Career（KK 摘要）：Code Agent RL、Long-Horizon Task、Multi-Agent RL、Memory/Search；小米顶尖人才课题：认知—记忆—问答链路及自动评估、Agent 与操作系统集成、端侧决策 [KK](https://keepkeen.github.io/blog/china-agent-interview-guide-2025-2026/)
- V2EX 远程 AI 应用开发工程师（25K–35K）：要求"熟悉至少一种 AI 应用框架（LangChain、LlamaIndex、Dify 等）"，向量数据库和 embedding 检索原理，职责含搭建向量数据库和知识库检索链路并优化效果、成本与响应延迟 [V2EX](https://www.v2ex.com/t/1240380)
- 智联招聘 中创智维 AI 大模型应用及 Agent 开发工程师（北京，1.8–3 万，3–5 年）：用 LangChain 框架搭建 AI 大模型应用架构，完成模型加载、配置和优化 [智联](https://www.zhaopin.com/jobdetail/CC272222130J40712519014.htm)
- 猎聘 武汉 大模型应用工程师（LLM/RAG/AI 平台）：要求基于 Dify 等平台搭建和维护工作流；通过提示词工程、上下文工程、RAG 等技术手段持续优化模型输出质量 [猎聘](https://www.liepin.com/job/1980131737.shtml)
- 搜索汇总：常见要求 Python（部分接受 Java/Go）、本科以上、1–3 年或 3 年以上；加分项常见 Agent、多智能体、模型微调（LoRA/QLoRA）[搜索汇总，含上述三页](https://www.zhaopin.com/jobdetail/CC272222130J40712519014.htm)
- 海外对照 JD：GetPro AI engineer 列 TypeScript/Python、agent 架构与 function calling、RAG、embeddings、向量数据库、LLM 评测与可观测性 [GetPro](https://www.getpro.co/en/jobs/ai-engineer)；联想 AI Agent Engineer（英文 JD）要求用 Dify、FastGPT 建 Agent 平台，熟悉 AutoGen、LangGraph、DSPy、CrewAI、LlamaIndex 及 MCP client-server 架构 [Lenovo](https://jobs.lenovo.com/en_US/careers/JobDetail/AI-Agent-Engineer/68849)；Dice LangGraph 岗要求 FastMCP 建 MCP server、评测框架覆盖 task completion / groundedness / latency / cost [Dice](https://www.dice.com/job-detail/03ecb238-8601-4d96-9d3d-be1344a62eb3)
- 面试题库对 JD 的映射：XL 为 LangChain 框架单列 12 题（含 LangChain4j、LangGraph、Deep Research 实现），AI 编程 14 题 [XL](https://xiaolinnote.com/ai/)；字节框架帖列出的框架谱系：Claude Agent SDK、OpenAI Agents SDK、LangGraph、Pydantic AI、Mastra、Smolagents、CrewAI、AutoGen、Dify/Coze [NC-BDFW](https://www.nowcoder.com/discuss/878709844730003456)
- 教材对照（Hello-Agents 目录）：第四章 ReAct / Plan-and-Solve / Reflection；第五章 低代码平台 Coze、Dify、n8n；第六章 AutoGen、AgentScope、LangGraph；第七章 从零构建 Agent 框架；第八章 记忆与检索（RAG）；第九章 上下文工程；第十章 MCP、A2A、ANP；第十一章 Agentic-RL（SFT 到 GRPO）；第十二章 性能评估；第十三章 MCP 与多智能体旅行助手；第十四章 DeepResearch Agent 复现；Extra 章含面试题、Agent Skills 与 MCP 对比、Dify 教程；页面注明"2026年5月更新智能体最新学习路线" [HA](https://github.com/datawhalechina/hello-agents)

### Inferences
- 面试题的主题分布与 Hello-Agents 第四至十二章几乎一一对应（范式 → 低代码平台 → 框架 → 手搓框架 → 记忆/RAG → 上下文工程 → 协议 → Agentic RL → 评测），教材可作为备考目录
- Dify/Coze 在 JD 中主要出现在中小公司和平台类岗位；大厂 2026 Agent 岗 JD 和面经中几乎不提 Dify/Coze，而问 LangGraph vs 自研、Claude Code 机制、Harness

### Gaps
- 京东 JD 原页（https://zhaopin.jd.com/web/job-info-detail?requementId=219868）重定向到会话登录页，无法核实原文，只能用 KK 的摘要
- GitHub shuh-ai/agent-dev-guide 的"大模型开发岗位 JD 拆解表"抓取失败，未能拿到其按公司列的技能表
- 未能直接检索 Boss 直聘 / 拉勾 / 脉脉的 JD（搜索结果中无这些站点的页面），中小公司 JD 样本只有智联、猎聘、V2EX 各一条
