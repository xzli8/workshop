# English-language sources: interview questions for LLM agent / AI engineer roles (2025–2026)

Scope note. All material below is from English-language web sources retrieved 2026-10-27. Sources fall into four tiers of reliability, flagged throughout:
- Tier A: primary job descriptions (Harvey, Anthropic, Decagon, Agent Engineer postings) and first-person interviewer essays (PromptLayer, Tian Pan, Hamel Husain course page).
- Tier B: company-specific prep guides that attribute questions to named companies (Aced/ex-Exponent, techinterview.org) — third-party, unverifiable but specific.
- Tier C: question banks / cheat sheets (amitshekhariitbhu GitHub repo, Let's Data Science, Analytics Vidhya, PracHub) — show what the prep ecosystem thinks is asked; no company attribution.
- Tier D: candidate forum posts (Reddit, Blind snippets) — thin, mostly topic-level, few verbatim questions.

Source key (short handles used in the tables):
- [GH-Shekhar] amitshekhariitbhu/ai-engineering-interview-questions, GitHub, ~2.2k stars, last commit 2026-07-11 — https://github.com/amitshekhariitbhu/ai-engineering-interview-questions
- [TI-Applied] techinterview.org, "What applied-AI engineer interviews test in 2026", updated 2026-07-23 — https://www.techinterview.org/post/3233476824/ai-engineer-interview-rag-agents-evals/
- [TI-Agents] techinterview.org, "AI/ML Interview: AI Agents — Tool Use, CoT, Planning, Function Calling…", updated 2026-04-21 — https://www.techinterview.org/post/3233474444/ai-ml-interview-ai-agents-tool-use-chain-of-thought-reasoning-planning-function-calling-rag-agents-autonomous-systems/
- [TI-MLE] techinterview.org, "ML Engineer Interview in the Post-LLM Era", updated 2026-07-13 — https://www.techinterview.org/post/3233474925/ml-engineer-interview-post-llm-era/
- [TI-Cursor] techinterview.org Cursor company guide, updated 2026-10-16 — https://www.techinterview.org/companies/cursor/
- [Aced-45] Aced (formerly Exponent), "45+ AI Engineer Interview Questions & Answers (2026 Guide)" — https://www.aced.io/blog/ai-engineer-interview-questions (old URL https://www.tryexponent.com/blog/ai-engineer-interview-questions redirects here)
- [Aced-Sierra] Aced, "Sierra Agent Engineer Interview" guide, "updated a month ago", © 2026 — https://www.aced.io/guides/sierra-agent-engineer-interview
- [PracHub] PracHub, "AI Engineer Interview Questions 2026: RAG, Agents, Evals, and Production Systems" — https://prachub.com/resources/ai-engineer-interview-questions-2026-rag-agents-evals-and-production-systems
- [PromptLayer] Jonathan Pedoeem (PromptLayer), "The Agentic System Design Interview: How to evaluate AI Engineers", 2025-07-11, questions re-reviewed July 2026 — https://blog.promptlayer.com/the-agentic-system-design-interview-how-to-evaluate-ai-engineers/
- [Perspective] Perspective AI, "Anthropic Applied AI Engineer Interview Process… 2026", 2026-05-29 — https://getperspective.ai/blog/anthropic-applied-ai-engineer-interview-process-frontier-lab-2026/markdown
- [TianPan] Tian Pan, "The AI Engineer Interview Is Broken: Stop Testing Implementation, Start Probing Eval-Design", 2026-04-27 — https://tianpan.co/blog/2026/04/27/ai-engineer-interview-eval-design
- [Hamel] Hamel Husain, Maven lesson "Cracking the AI Evals Interview", 2026-10-06 — https://maven.com/p/07e7c7/cracking-the-ai-evals-interview
- [LDS-50] Let's Data Science, "50 LLM and AI Engineer Interview Questions for 2026", 2026-03-18 — https://letsdatascience.com/blog/50-llm-and-ai-engineer-interview-questions-for-2026
- [AV-30] Analytics Vidhya, "Top 30 AI Agent Interview Questions", 2025-05-05 — https://www.analyticsvidhya.com/blog/2025/05/ai-agent-interview-questions/
- [Reddit-LLMDevs] r/LLMDevs post by u/iamsausi, April 2026 (snapshot) — https://reddit.sentinel-team.org/posts/1sn7hc8/snapshots/2026-04-17T05%3A37%3A44.74927Z
- [Reddit-LangChain] r/LangChain thread by u/Responsible_Basket32, May 2026 (snapshot) — https://reddit.sentinel-team.org/posts/1t1ywp7/snapshots/2026-05-09T00%3A32%3A05.66647Z
- [Harvey-JD] Harvey, "Senior Software Engineer, Agents" — https://www.harvey.ai/company/careers/04eb457b-e985-4e3b-9635-0a2b867ada97
- [Anthropic-JD] Anthropic, "Applied AI Engineer, Enterprise" (Greenhouse) — https://job-boards.greenhouse.io/anthropic/jobs/5057647008
- [Decagon-JD] Decagon, "Software Engineer, Agents" (closed posting mirrored on Soma Capital board) — https://jobs.somacap.com/companies/decagon-2/jobs/51636466-agent-software-engineer
- [AgentJD-Lever] "Senior AI Agent Engineer" via Jobgether/Lever — https://jobs.lever.co/jobgether/85f64172-e513-47c2-bdfe-51e2a2827335
- [AgentJD-Staff] "Staff Engineer — Agentic AI" via 100hires — https://100hires.com/j/TYaFyGz
- [IQ-Anthropic] Interview Query, Anthropic AI Engineer guide (snippet only) — https://www.interviewquery.com/guides/anthropic-ai-engineer
- [Gauntlet] Gauntlet AI, "What Hiring Managers Actually Look for in AI Engineers" (snippet only) — https://gauntletai.com/night-school/what-hiring-managers-actually-look-for-in-ai-engineers
- [KDn] KDnuggets, "How to Answer AI System Design Interview Questions" (snippet only) — https://www.kdnuggets.com/how-to-answer-ai-system-design-interview-questions
- [Dataford] Dataford "LLM Agents questions" list (page returned HTTP 429 on three attempts; only search snippet available) — https://dataford.io/questions/lists/agents-questions

Category tags used: [C] conceptual, [SD] system design, [K] coding / take-home, [B] behavioral / product sense, [Dbg] debugging-scenario ("your agent does X, fix it").

---

## Key Question 1: Which specific questions recur across multiple sources, by topic?

### Takeaway
Across ~15 sources the same ~40 question stems recur. The highest-frequency clusters (4+ independent sources each) are: (a) "explain RAG / walk the pipeline end-to-end / RAG vs fine-tuning vs prompting", (b) "how do you evaluate an LLM feature with no single right answer / can you trust LLM-as-judge", (c) "agent vs deterministic workflow — when and how does an agent decide to call a tool", (d) agent failure modes ("infinite loop", "wrong tool", "wrong parameters", "irreversible action"), (e) "MCP vs function calling" and "structured output vs function calling", (f) cost/latency trade-off scenarios, and (g) "design a customer-support agent / assistant over a knowledge base" as the canonical system-design prompt. Classic ML theory is explicitly described as de-emphasised but not gone.

### Cited Findings — taxonomy

Frequency column = number of distinct sources in this note that list the question (or a near-paraphrase).

#### 1. LLM fundamentals (conceptual; "assumed known", probed quickly)
| Question (verbatim / near-verbatim) | Tag | Freq | Sources |
|---|---|---|---|
| "What is a tokenizer, and why does token count drive both cost and context limits?" | C | 2 | [Aced-45]; BPE "failure modes" in [LDS-50] Q3 |
| "What is an embedding, and what does semantic similarity mean in a retrieval system?" | C | 2 | [Aced-45]; [PromptLayer] "Explain how embeddings enable semantic search" |
| "What is a context window, and what breaks when you exceed it?" / "How do you handle context window limits in a production application?" | C | 3 | [Aced-45]; [LDS-50] Q44; [GH-Shekhar] "context compaction" |
| "What does the temperature parameter control, and when would you set it near zero?" (answer tip: low temperature does not guarantee correctness) | C | 1 | [Aced-45] |
| "What is quantization, and what do you trade away when you quantize a model?" | C | 2 | [Aced-45]; [TI-Cursor] inference deep-dive |
| "What is the KV cache and what are its memory implications at scale?" / "What is prompt caching and when should you use it?" | C | 2 | [LDS-50] Q2, Q13; [TI-Cursor] "KV-cache reuse across keystrokes" |
| "Walk me through how RLHF works end to end" / "What is RLHF?" / DPO vs PPO | C | 2 | [LDS-50] Q7–Q8; [PromptLayer] |
| "Explain scaled dot-product attention and why the scaling factor matters" (plus MQA/GQA, Flash Attention, MoE, MLA) | C | 1 | [LDS-50] Q1, Q9–Q12 — labelled "All Levels" |
| Interviewers "assume you know transformers cold" then move to RAG/agents/evals/production | C | 1 | [Reddit-LLMDevs] |
| Classic ML (logistic regression, trees, GBMs, basic NNs) "still come up but are less central"; "deep derivations are rarely asked" | C | 1 | [TI-MLE] |

#### 2. Prompt engineering / prompt-as-architecture
| Question | Tag | Freq | Sources |
|---|---|---|---|
| "Prompting vs. RAG vs. fine-tuning: how do you choose?" / "Compare RAG vs fine-tuning. When would you use each?" / "When does fine-tuning actually help? What are the trade-offs?" / "When does fine-tuning outperform RAG…?" | C | 6 | [Aced-45]; [GH-Shekhar]; [PromptLayer]; [LDS-50] Q28; [TI-MLE]; [Reddit-LLMDevs] (handbook topic) |
| "What is chain-of-thought (CoT) prompting, and when should you use it?" / "Explain self-consistency prompting" | C | 2 | [GH-Shekhar]; [TI-Agents] |
| "What is a prompt template, and how do you design one for production use?" / "How do you manage prompt versioning in a production LLM application?" / prompt versioning & A/B testing as architecture concern | C | 3 | [GH-Shekhar]; [LDS-50] Q40; [TI-MLE] |
| "How do you optimize prompts for cost and latency?" | C | 1 | [GH-Shekhar] |
| "Your LLM keeps ignoring your instructions. How do you make it follow structured output formats?" | Dbg | 1 | [GH-Shekhar] |
| Whiteboard: "optionally draft a prompt" for each agent step; interviewers listen for "prompt structure for each component" | SD | 1 | [PromptLayer] |

#### 3. Structured output
| Question | Tag | Freq | Sources |
|---|---|---|---|
| "What is the difference between structured output and function calling?" / "…and which should you use?" | C | 2 | [GH-Shekhar]; [LDS-50] Q34 |
| "How do you implement structured output from LLMs reliably in production?" / "How do you structure prompts for consistent structured output (JSON, XML)?" / "What are output parsers, and why are they needed…?" | C | 1 | [GH-Shekhar] |
| "How would you integrate an LLM behind a reliable application contract?" — expected: define schema, validate every response, handle refusals/missing fields, timeouts + bounded retries, version prompt+model+schema+params together, verify calculations/permissions before side effects | C/SD | 1 | [PracHub] |
| Anti-pattern flagged by an interviewer: candidate "jumps to prompting, e.g. 'I'll have the model output JSON'" before designing an eval = "cannot tell when their system is wrong" | — | 1 | [TianPan] |

#### 4. Tool calling / function calling
| Question | Tag | Freq | Sources |
|---|---|---|---|
| "What is tool use (function calling) in LLMs, and how does it enable agents?" / "How do function calling and tool use work?" (expected: developer supplies schemas; model emits structured call; app executes; model writes final answer) | C | 3 | [GH-Shekhar]; [TI-Agents]; [Reddit-LangChain] (topic) |
| "How does an agent decide when to call a tool versus answering from its own knowledge?" | C | 2 | [GH-Shekhar]; [LDS-50] Q36 |
| "How do you design and define tools for an AI agent?" (expected: "too many tools confuse the model, too few limit capability; group related tools"; descriptions act as the selection prompt — "Search the product catalog by name, returning price and availability" beats vague wording) | C | 2 | [GH-Shekhar]; [TI-Agents] |
| "How do you handle tool call errors and retries in an agentic system?" (expected: retry, fall back to another tool, or tell the user; confirmation before high-stakes actions such as email, purchases, data changes) | C | 2 | [LDS-50] Q31; [TI-Agents] |
| "What is Model Context Protocol (MCP), and how does it standardize tool integration?" / "How does MCP differ from traditional function calling?" | C | 2 | [GH-Shekhar]; [LDS-50] Q32 (also "MCP" listed in [Anthropic-JD] requirements) |
| "Your AI agent has many tools, but keeps picking the wrong one. How do you improve tool selection?" | Dbg | 2 | [GH-Shekhar]; [TI-Agents] "tool selection errors" |
| "Your LLM selects the right tool but extracts the wrong parameters. How do you fix parameter extraction?" | Dbg | 2 | [GH-Shekhar]; [TI-Agents] |
| "Your AI agent hallucinates tool capabilities and passes wrong inputs. How do you fix it?" (expected: validate every call against the registered tool list) | Dbg | 2 | [GH-Shekhar]; [TI-Agents] "hallucinated tool calls" |
| "Your AI agent gets conflicting answers from different tools. How does it reconcile them?" | Dbg | 1 | [GH-Shekhar] |
| "Write a function calling (tool use) handler for an LLM API." | K | 1 | [GH-Shekhar] |
| Phone-screen coding example: "a tool-use orchestrator" | K | 1 | [Perspective] (Anthropic, unverified) |
| "Tool-calling for file operations" and "multi-file edit coordination" listed as applied-coding focus | K | 1 | [TI-Cursor] |

#### 5. Agent loops, planning, orchestration (single agent)
| Question | Tag | Freq | Sources |
|---|---|---|---|
| "What is an AI agent, and how does it differ from a simple LLM call?" | C | 3 | [GH-Shekhar]; [TI-Agents]; [AV-30] Q1/Q12 |
| "Explain the ReAct (Reasoning + Acting) agent architecture" / "Explain the ReAct framework and what problems it solves over chain-of-thought alone" | C | 4 | [GH-Shekhar]; [LDS-50] Q29; [TI-Agents]; [Reddit-LangChain] (ReAct implied under "agent workflow") |
| "What is the Plan-and-Execute agent pattern?" (expected contrast: ReAct is greedy/sequential; plan-then-execute better for long workflows but "plans break when early steps fail, so re-plan"; tree-of-thought costs multiple calls per step; reflection) | C | 2 | [GH-Shekhar]; [TI-Agents] |
| "When would you use an agent instead of a deterministic workflow?" / "When should AI agents be considered…?" / "when you should not build an agent at all" (expected: prefer workflow when steps are known; agent only when flexible planning/tool selection justifies cost and uncertainty; define tool schemas, least-privilege credentials, stop conditions, budgets, approval points) | C | 3 | [PracHub]; [AV-30] Q3; LinkedIn post by Devadharshani A (search snippet, https://discuss.pytorch.kr/t/ai-engineering-interview-questions-ai/11074 search result context) |
| "What is an agent loop, and how does it decide when to stop?" / "stopping conditions and control flow" | C | 2 | [GH-Shekhar]; [TI-Agents] (step caps) |
| "Implement a minimal agent loop with tool dispatch, error handling, and a step budget." / "Build a simple AI agent with tool use (e.g., calculator, web search)." | K | 2 | [GH-Shekhar]; [Reddit-LangChain] ("small build exercise … agent workflow") |
| "Your AI agent is stuck in an infinite loop. How do you detect and break the cycle?" / "This agent loops forever on some inputs. Find out why and fix it." / "when a retry turns into an infinite loop" (expected: cap iterations (~10), detect repeated actions, timeouts, cap per-request spend, trace multi-step runs, replay failures against fixed case set) | Dbg | 4 | [GH-Shekhar]; [TI-Applied]; [TI-Agents]; LinkedIn snippet; also [Dataford] snippet ("stopping infinite loops") |
| "Your long-running agent drifts after hours and confidently works on the wrong thing. How do you diagnose and fix it?" / "How do you design a long-running agent that needs to maintain state across days or weeks?" / "whether an agent can resume after a mid-run crash" | Dbg/SD | 3 | [GH-Shekhar]; [LDS-50] Q35; LinkedIn snippet |
| "What is agent reflection, and how does it improve agent performance?" | C | 2 | [GH-Shekhar]; [TI-Agents] |
| "What is task decomposition in agentic AI?" / "Define the agent-environment loop" | C | 1 | [AV-30] Q26, Q19 |
| "Design an agentic service for a given use case, such as a subscription-cancellation flow." | SD | 1 | [Aced-Sierra] (Sierra technical screen) |
| "Inspect traces when an agent loops or misuses a tool; verify outcomes in the system of record rather than trusting the agent's claim of success" | C | 1 | [PracHub] |

#### 6. Multi-agent systems
| Question | Tag | Freq | Sources |
|---|---|---|---|
| "What is the difference between single-agent and multi-agent systems?" / "When do multi-agent systems break down, and when is a single agent the better choice?" / "How does multi-agent orchestration work and when does it break down?" | C | 3 | [GH-Shekhar]; [LDS-50] Q33; [AV-30] Q30 |
| "What are AI SubAgents?" / "subagent patterns" | C | 2 | [GH-Shekhar]; [Harvey-JD] |
| "What is agent orchestration, and how do you implement it?" / "How is agent routing implemented in multi-agent orchestration systems?" | C | 3 | [GH-Shekhar]; [AV-30] Q17, Q29; [Reddit-LLMDevs] handbook |
| "Build a multi-agent system where agents have different roles and collaborate on a task." / "Create a system where agents collaborate to produce a research report with citations" | K/SD | 2 | [GH-Shekhar]; [PromptLayer] |
| "Your multi-LLM pipeline fails when one model in the chain breaks. How do you handle orchestration failure?" | Dbg | 1 | [GH-Shekhar] |
| Whiteboard grading: "clear sub-task decomposition, logical flow between steps… interviewers want to hear why agent boundaries were chosen" | SD | 1 | [PromptLayer] |
| Not covered at all in [TI-Agents] (explicitly noted). | — | — | [TI-Agents] |

#### 7. Memory and context management
| Question | Tag | Freq | Sources |
|---|---|---|---|
| "What are the different types of agent memory (short-term, long-term, episodic)?" / "What are the four types of memory in AI agents and how are they stored?" / "Why is memory critical for the performance of AI agents?" | C | 3 | [GH-Shekhar]; [LDS-50] Q30; [AV-30] Q5, Q28 |
| "What different strategies could you use for long-term memory?" (filed under "context engineering") | C | 1 | [PromptLayer] |
| "Implement a conversation memory system for a chatbot (sliding window, summary, buffer)." / "Design Memory for a Personal AI Assistant" | K/SD | 1 | [GH-Shekhar] |
| "Your chatbot loses context after 10 turns in a conversation. How do you maintain a long conversation context?" / "How does context compaction work?" (expected per [TI-Agents]: summarize old steps, keep latest N, or memory system) | Dbg | 2 | [GH-Shekhar]; [TI-Agents] "context overflow" |
| "What is the lost-in-the-middle problem and how do you address it?" | C | 1 | [LDS-50] Q18 |
| Cursor: "Given a cursor position in a codebase, select the most relevant snippets for an LLM prompt"; compare embedding retrieval vs dependency-graph traversal vs recently-edited heuristics; "argue how much context is worth its added latency" | K/C | 1 | [TI-Cursor] |
| "Context window management" listed as a core job responsibility | — | 1 | [Harvey-JD] |

#### 8. RAG
| Question | Tag | Freq | Sources |
|---|---|---|---|
| "Explain how RAG works" (attributed to Sierra) / "What is RAG, and why is it important?" / "a 200-page PDF and a user question — walk through what happens from submit to answer" (expected: chunking, embedding, vector store, top-k, prompt assembly; second layer: chunk size and why, what breaks as k grows; when retrieval is the wrong tool — single doc fits in context; stale index gives confident wrong answers) | C | 5 | [Aced-45]; [GH-Shekhar]; [TI-Applied]; [AV-30] Q7; [TI-MLE] |
| "What chunking strategy would you use for a large technical documentation corpus, and what are the trade-offs?" | C | 2 | [LDS-50] Q14; [TI-Applied] |
| "How do you choose between a sparse retriever (BM25) and a dense retriever?" / "What is a reranker and when should you use one?" / HyDE | C | 1 | [LDS-50] Q15, Q16, Q20 |
| "How would you evaluate the quality of a RAG pipeline?" (expected: recall@k, precision@k, MRR/NDCG when order matters; evaluate retrieval and generation separately) | C | 4 | [LDS-50] Q17; [TI-Applied]; [Aced-45] (Sierra answer); [Reddit-LLMDevs] ("fumbled a question on retrieval eval") |
| "A RAG answer is wrong even though the expected document exists. How do you debug it?" (expected: split pipeline — ingestion, access filters, chunking, retrieval, reranking, context assembly, generation; capture retrieved doc IDs and scores; classify failure: not indexed / filtered out / ranked too low / truncated / misused; add to regression set; change one component at a time) / "Your RAG system is hallucinating despite having the right context. How do you fix it?" | Dbg | 2 | [PracHub]; [GH-Shekhar] |
| "Long context windows keep getting cheaper. When should you use retrieval (RAG) vs putting everything in the context window?" | C | 2 | [GH-Shekhar]; [TI-Applied] |
| "How does agentic RAG differ from standard RAG?" (expected: agent decides when/what to retrieve, reformulates, judges sufficiency, multi-source) | C | 2 | [LDS-50] Q19; [TI-Agents] |
| "What is the role of metadata filtering in RAG?" / "How do you implement citation and source attribution in RAG?" | C | 1 | [GH-Shekhar] |
| "Compare different vector database options. When would you use each?" | C | 1 | [PromptLayer] |
| "Design an insurance-claims agent that ingests claims and outputs an approval decision using RAG" while controlling token cost (attributed to Scale AI) | SD | 1 | [Aced-45] |

#### 9. Evaluation and observability
| Question | Tag | Freq | Sources |
|---|---|---|---|
| "How would you evaluate an LLM feature when there's no single correct answer?" / "How do you evaluate LLM outputs? What metrics do you use?" | C | 3 | [Aced-45]; [GH-Shekhar]; [TI-MLE] |
| "What does LLM-as-a-judge do, and what are its failure modes?" / "Can an LLM-as-judge be trusted?" / "What is LLM-as-a-judge evaluation, and what are its limitations?" (expected: no, not before calibrating; judges favor longer answers, the first response in pairwise comparison, and their own model family; measure agreement with human labels, tighten rubric; [TianPan] suggests 75–90% agreement before the judge gates releases) | C | 5 | [Aced-45]; [PracHub]; [GH-Shekhar]; [TI-Applied]; [TianPan] |
| "How do you catch regressions when you change a prompt or swap a model?" / "How do you build a regression test suite for AI applications?" / "How would you build an eval set and decide whether a change can ship?" (expected: tasks from requirements, production failures, high-impact harms; deterministic graders for verifiable outcomes, rubrics for subjective, human calibration; repeated trials; failure slices; regression gate with latency/cost/safety constraints) | C | 4 | [Aced-45]; [GH-Shekhar]; [PracHub]; [TI-Applied] |
| "How do you detect and measure hallucinations in LLM outputs?" / "…in a production RAG system?" / Anthropic ML-loop: "Investigate and mitigate a model that gives confident but factually wrong answers in high-risk contexts" (expected: build eval set, measure confident-wrong rate; diagnose retrieval gap vs calibration vs prompt; ground with citations, teach abstention, add verification, route to humans) | C | 3 | [GH-Shekhar]; [LDS-50] Q41; [Aced-45] |
| "How do you evaluate the quality of AI agents?" / "How do you evaluate agents?" (expected: task completion rate, step efficiency, tool accuracy, user satisfaction; benchmarks GAIA, AgentBench, ToolBench; [AgentJD-Staff] names SWE-bench, GAIA, τ-bench) | C | 4 | [GH-Shekhar]; [TI-Agents]; [AV-30] Q15–16; [AgentJD-Staff] |
| "How would you evaluate an autonomous coding agent? Why can SWE-bench pass rates be misleading?" | C | 1 | [GH-Shekhar] |
| "How do you evaluate multi-turn conversation quality?" / "How do you implement continuous evaluation for production AI systems?" | C | 1 | [GH-Shekhar] |
| Cursor ML deep-dive: "Describe how you would evaluate whether a completion was helpful" — look for methods beyond accept/reject, e.g., whether a suggestion survives in the file minutes later; offline eval sets from real sessions | C | 1 | [TI-Cursor] |
| "What observability infrastructure does a production LLM system need?" / "What metrics and observability would you track if this agent went to production?" (Sierra take-home follow-up) | C | 2 | [LDS-50] Q45; [Aced-Sierra] |
| Eval-design round (proposed): given spec "build the part of customer support triage that decides which queue a ticket goes to, including a confidence score", spend ~40 min designing the eval without touching a prompt; scored on unnamed failure modes (adversarial, multilingual, needs-human), rubric precision ("two graders would agree", e.g. "the output should name the correct queue from this list of seven"), live-distribution coverage with refresh cadence, judge validated vs human labels | SD | 1 | [TianPan] |
| Debugging-replay round: given a production trace, reason about what went wrong and "which eval extension would have caught it" | Dbg | 1 | [TianPan] |
| Evals take-home: "a prompt plus about 30 input/output pairs — build an automated eval for faithfulness and relevance, then calibrate it against provided human labels" | K | 1 | [TI-Applied] |
| Evals interview traps named by Hamel Husain: "generic metrics", "composite scores", "hill-climbing"; take-home report structure: dig into data for traps/inconsistencies, identify noisy data, experiments with baselines, results and trade-offs, learnings/limitations and diplomatic push-back, next steps, "show judgment rather than hill-climbing on a metric" | K | 1 | [Hamel] |
| "evals can often be the 'weed out' part of interviews" | — | 1 | [Hamel] |

#### 10. Reliability, guardrails, safety, security
| Question | Tag | Freq | Sources |
|---|---|---|---|
| "What are guardrails for LLMs, and how do you implement them?" / "How do you implement input and output guardrails?" / "Implement an LLM output guardrails system that checks for off-topic responses and PII leakage." | C/K | 2 | [GH-Shekhar]; [Dataford] snippet (guardrail items: limiting harmful actions, resisting prompt injection, stopping infinite loops) |
| "How do you implement guardrails for AI agents to prevent harmful actions?" / "What are the security risks of agentic systems, and how do you mitigate them?" | C | 2 | [GH-Shekhar]; [PracHub] |
| "How do you secure a RAG or tool-using agent?" (expected: map trust boundaries; retrieved content and tool outputs are untrusted; authz at retrieval and action time; minimize tool permissions; validate arguments; approval for high-impact actions — "prompt instructions alone are not authorization"; test indirect prompt injection, cross-tenant retrieval, exfiltration, unsafe retries) | C/SD | 1 | [PracHub] |
| "What is prompt injection and how do you defend against it in an agent?" / jailbreaking vs adversarial prompting, "what defenses actually work in production?" | C | 2 | [LDS-50] Q37, Q47; [PracHub] |
| "What is the human-in-the-loop pattern for agents, and when is it needed?" / confirmation before high-stakes actions | C | 3 | [GH-Shekhar]; [TI-Agents]; [PracHub] ("approval points") |
| "Your AI agent deleted a production database. How do you prevent irreversible actions?" | Dbg | 1 | [GH-Shekhar] |
| "How do you handle agent failures and implement error recovery?" / "Handle requests for disallowed actions" | C | 2 | [GH-Shekhar]; [TI-Applied] |
| "How would you respond to a production incident after a model or prompt change?" (expected: roll back / disable / human review; preserve traces and version IDs; reproduce against exact config; add failing slice to regression suite; canary with abort thresholds) | SD/B | 1 | [PracHub] |
| "How do you approach GenAI safety in consumer products?" (attributed to OpenAI, Anthropic, Google) | B | 1 | [Aced-45] |
| Anthropic recruiter screen "includes safety questions about Anthropic's Responsible Scaling Policy. Generic 'responsible AI' answers reportedly do not pass"; "most common reason candidates fail is their culture fit round, based on AI ethics and safety" | B | 2 | [Perspective] (unverified); [IQ-Anthropic] snippet |
| Lab-specific safety concepts: Constitutional AI vs RLHF, reward hacking, RLAIF, mechanistic interpretability, "How do you evaluate LLM safety and helpfulness without relying on benchmarks that can be gamed?" — "All Levels at AI Labs, Senior at Startups" | C | 1 | [LDS-50] Q46–Q51 |

#### 11. Cost and latency
| Question | Tag | Freq | Sources |
|---|---|---|---|
| "Your LLM costs are too high in production. How do you reduce costs without degrading quality?" / "What are the main cost levers for reducing LLM API spend at scale?" / "How do you optimize LLM inference costs in production?" | Dbg/C | 3 | [GH-Shekhar]; [LDS-50] Q42; [TI-MLE] ("token economics, caching, model routing") |
| "Quality improved, but latency and cost became unacceptable. What do you do?" (expected: define latency objective and cost unit e.g. cost per resolved task; break trace by component, tail latency; test smallest interventions — routing, shorter context, caching, parallelism, fewer agent turns — on same eval set; state acceptable quality loss) | Dbg | 1 | [PracHub] |
| "How do you manage token consumption and cost in long-running agent workflows?" / "Your AI agent keeps exceeding its budget per task. How do you enforce budget limits?" / phone-screen coding "a token-budget allocator" | C/K | 3 | [GH-Shekhar]; [TI-Agents] ("complex tasks may need 20–50 steps"); [Perspective] |
| "Your AI agent takes too long to complete a task. How do you speed it up?" / "Your LLM API has latency spikes during peak hours. How do you stabilize it?" / "How do you design for latency vs quality trade-offs?" | Dbg | 2 | [GH-Shekhar]; [TI-Applied] (800 ms end-to-end target; smaller model for routing; semantic caching; parallel tool calls) |
| Trade-off scenario: "premium model wins by 3 points at 6× cost — What do you ask before deciding, and what's the smallest experiment that decides it?"; "a tool call fixes 40% of failures but adds 800 ms to a voice product — Walk me through your decision." | B/C | 1 | [TianPan] |
| "How do you estimate the cost of running an AI-powered feature in production?" / "How do you design rate limiting and cost management for AI APIs?" | C | 1 | [GH-Shekhar] |
| Inference-serving: "Explain continuous batching and why it replaced static batching"; speculative decoding; vLLM PagedAttention | C | 2 | [LDS-50] Q38–Q39, Q43; [TI-Cursor] |
| Staff-level JD: "setting per-task token budgets and tracking cost per completed workflow" | — | 1 | [AgentJD-Staff] |

#### 12. System design prompts (LLM / agent flavored)
| Prompt | Company (if attributed) | Freq | Sources |
|---|---|---|---|
| "Design a customer-support assistant powered by an LLM with access to the company's knowledge base" — "the most common question type in 2026, the modern equivalent of 'build a recommendation system'" / "Design a support assistant. How do you know it's helping and not making things worse?" / "Design a Multi-Agent Customer Support System" / "Design an agent that analyzes customer support tickets and automatically drafts responses, escalating complex issues" | generic; Sierra variants | 5 | [TI-MLE]; [TI-Applied]; [GH-Shekhar]; [PromptLayer]; [KDn] snippet |
| "Design an agentic AI system to power customer support for Spotify." / "Design an AI agent for a streaming service." / "Design an AI system that integrates with a third-party company's data and workflows." / "Build your own customer service AI agent for a hypothetical outdoors company" | Sierra | 1 | [Aced-45] |
| "Design an AI agent that can take actions across multiple tools." | generic | 2 | [TI-MLE]; [PracHub] ("Design an Enterprise Tool-Using Agent": tool contracts, state, permissions, long-running work) |
| "Design an AI Coding Agent." / "Build an agent that can read code, understand its purpose, and suggest improvements" / "Design a code-review tool that uses an LLM" / "Design the Cursor agent that edits multiple files based on a task description" (variant: "multi-file edits with verification and rollback") | Cursor | 4 | [GH-Shekhar]; [PromptLayer]; [TI-MLE]; [TI-Cursor] |
| "Design a Deep Research Agent" / "Create a system where agents collaborate to produce a research report with citations" | generic | 2 | [GH-Shekhar]; [PromptLayer] |
| "Design a RAG System (Chat with Your Documents)" / "Design a production RAG system" / "Design an enterprise search system using LLM + retrieval" / "Design a document processing pipeline that ingests and indexes large volumes of heterogeneous documents for LLM use" | Databricks (last) | 4 | [GH-Shekhar]; [PracHub]; [TI-MLE]; [Aced-45] |
| "Design an Agent Harness and Evaluation System" (repeatable trials, traces, graders, diagnosis) / "Design a Scalable and Safe Agentic System" (concurrency, trust boundaries, loops, production controls) | generic | 1 | [PracHub] |
| "Design an insurance-claims agent that ingests claims and outputs an approval decision using RAG" with token-cost control (expected: approve/deny/escalate; index policies; human review above thresholds; cache, tight top-k, routing, per-claim token cap; evaluate retrieval and decisions separately; log with citations) | Scale AI | 1 | [Aced-45] |
| "Design an inference batching system for a single GPU handling up to 100 inputs per batch while users wait synchronously" / "Design an API that lets users sample from large language models efficiently, with good batching and request orchestration." / "Design an LLM Inference Platform (vLLM-as-a-Service)" | Anthropic | 2 | [Aced-45]; [GH-Shekhar] |
| "Design an AI gateway/proxy for managing LLM access across an organization." / "Multi-tenant Claude deployment system design, with increasing emphasis on evaluation harnesses over RAG architecture" | Anthropic (latter, unverified) | 2 | [GH-Shekhar]; [Perspective] |
| "Design the OpenAI Playground." / "Implement a GPU credit management system" | OpenAI | 1 | [Aced-45] |
| "Design a file uploader for an AI chat app that handles large multimodal uploads and feeds them into a model's context" | xAI | 1 | [Aced-45] |
| "Design the tab-prediction system with sub-100ms latency end-to-end for millions of users"; "privacy-preserving inference architecture letting enterprise customers keep code confidential"; "custom-model training pipeline producing coding-specialized variants" | Cursor | 1 | [TI-Cursor] |
| "Design a real-time voice AI application." | generic | 1 | [Aced-45] |
| Grading rubric: standard ML-SD rubric (requirements, capacity, data flow, evaluation) + LLM-specific choices; [KDn]: separate LLM reasoning from orchestrator, policy engine, sandbox; pair offline evals (judge calibrated vs ground truth) with online metrics | — | 2 | search snippets ([KDn]; DesignGurus) |

#### 13. Coding exercises (see also KQ5)
| Exercise | Company | Sources |
|---|---|---|
| "Implement a minimal agent loop with tool dispatch, error handling, and a step budget." | generic | [GH-Shekhar] |
| "Implement a function to batch API calls to an LLM efficiently"; "Debug this code that processes embeddings"; "Write a script to clean and prepare text data for fine-tuning" | generic | [PromptLayer] |
| "a retrieval scorer, a token-budget allocator, and a tool-use orchestrator" (phone screen) | Anthropic (unverified) | [Perspective] |
| "Debug and extend a Python codebase to add an async exponential retry mechanism with timeouts and backoff."; "Traverse cell dependencies in an Excel-like spreadsheet and detect circular references." | Sierra | [Aced-45]; [Aced-Sierra] |
| "Find and fix the bugs in this React application" (debugging round; bugs "often related to incorrect strings or loops") | Sierra | [Aced-Sierra]; Aced Sierra snippet |
| "Design and implement an in-memory key-value store with set, transactional begin, commit, and abort."; "Given credit issuance and usage events with expiry, compute the user's remaining credit pool."; "Implement encode and decode for a list of strings."; "Infection-spread simulation." | OpenAI (KV store also xAI) | [Aced-45] |
| "Scan a filesystem to identify and report duplicate files."; "Convert profiler stack samples into a trace"; "Code an image-processing pipeline that applies ordered transformations from per-image instruction files"; "Extract and clean a usable dataset from a company demo database using only SQL and Python" | Anthropic | [Aced-45] |
| "Handle incoming LLM tokens and apply them as real-time edits to a document"; "implement a syntax-aware edit operation"; "model a file-tree diff"; "Track changes across many files with consistency" | Cursor | [TI-Cursor] |
| "Build a web crawler from scratch"; LRU cache; task management system (OA) | Anthropic infra (Blind, 2024) | Blind search snippet, https://www.teamblind.com/post/anthropic-interview-szdhw35i |

#### 14. Behavioral / product-sense / "passion" questions
| Question | Company | Sources |
|---|---|---|
| "Walk me through an AI system you shipped" (expected: user problem, your responsibility, baseline, architecture decision, how evaluated, launch guardrail, one change after real feedback; separate team outcomes from your contribution) | generic | [PracHub] |
| "Have you built any end-to-end agentic systems?" | Glean | [Aced-45] |
| "A customer says the bot 'feels dumb.' What do you change first?" (expected: scope into a measurable problem before changing anything) | generic | [TI-Applied] |
| "A stakeholder wants to ship, but your evidence is weak. What do you say?" | generic | [PracHub] |
| "How would you define success metrics for an AI-oriented feature or product?" | Perplexity | [Aced-45] |
| "Which model provider do you lean on for creative writing? Claude, OpenAI, Gemini?"; "What AI coding assistants do you use? Can you compare Cursor, Windsurf, Zed, and Claude Code?"; "How do Open Source LLMs stack up?"; "Will AGI kill us all?"; "Tell me about a recent paper or development in AI that caught your attention"; "What side projects have you built with AI recently?" ("boring textbook answers are a red flag") | generic (interviewer-authored) | [PromptLayer] |
| "What are some new advancements in AI you find interesting?"; "What GenAI skills do you think will be critical in the next year?"; "Why do you want to work at Anthropic?"; "Tell me about a time you were confident in a solution and later realized it was wrong."; "Tell me about a time you had to build something that conflicted with your personal values."; "How would you explain a technical concept to a non-technical person?" | Anthropic | [Aced-45] |
| "Why do you want to work at Sierra?"; "How do you keep up with developments in AI and customer support?"; "Present a technical topic of your choice to a non-technical audience."; "How have you worked directly with customers to solve a challenge?" | Sierra | [Aced-Sierra] |
| "Compare Cursor to Copilot, Windsurf, and Claude Code, and say where each wins"; "What is broken about Cursor today, and what would you fix?" | Cursor | [TI-Cursor] |
| "How should you use an AI assistant during a coding round when it's allowed?" (narrate plan, read all generated code, handle edge cases/tests); "walk through your Claude Code workflow out loud" | generic | [Aced-45]; [Gauntlet] snippet |

### Inferences
- The "scenario-debug" phrasing ("Your agent X — how do you fix it?") is the dominant modern format for agent questions; [GH-Shekhar] has >15 of these, and [TI-Applied]/[PracHub] use the same shape. Candidates should prepare answers as diagnose → instrument → fix → add regression case.
- Evals is the topic with the most interviewer-authored (Tier A) material ([TianPan], [Hamel], [PromptLayer], [TI-Applied]) and the only topic a practitioner explicitly calls a "weed out" stage; it therefore likely carries more weight than its raw question count suggests.
- Multi-agent and memory questions are abundant in Tier C question banks but nearly absent from company-attributed (Tier B) questions; attributed questions skew toward single-agent customer-support design, RAG, batching/inference, and generic coding.
- Classic algorithm coding has not disappeared: Sierra, OpenAI, Anthropic, Cursor all retain at least one DS&A-style round per [Aced-45], [Aced-Sierra], [TI-Cursor], though forum commenters say it is "lighter than hardcore LeetCode" ([Reddit-LangChain]).

### Gaps
- No source gave quantitative frequency data across real interviews; the Freq column counts sources, not interviews. [Dataford] claims to rank "most frequently asked LLM Agents questions across all roles and companies" but the page returned HTTP 429 on every attempt, so its ranking could not be captured.
- Verbatim questions on structured output and on MCP exist only in Tier C question banks; no company-attributed instance found.
- No YouTube mock-interview sources were found for agent roles (search returned only text question banks).
- Hello Interview, ByteByteGo and interviewing.io returned no indexable agent-specific content; Hello Interview has a community thread titled "agentic AI system design" (https://www.hellointerview.com/community/discussion/agentic-ai-system-design) but its body was not retrievable.

---

## Key Question 2: What do real candidates report being asked at specific companies?

### Takeaway
Company-specific detail exists for Anthropic, Sierra, Cursor, OpenAI, Scale, Databricks, Glean, Perplexity, xAI — almost entirely via third-party prep guides (Aced, techinterview.org, Perspective, Interview Query) that cite Glassdoor/Blind without links; first-person forum reports are sparse and mostly 2024. The consistent pattern at agent-building companies (Sierra, Anthropic Applied AI, Cursor) is: DS&A screen → substantial take-home (build an agent) → onsite with debugging + take-home defense + system design + customer/product round + behavioral.

### Cited Findings
**Anthropic (Applied AI Engineer / AI Engineer)**
- Five stages over ~4–6 weeks: (1) recruiter screen incl. RSP safety questions; (2) 60-min technical phone screen with "LLM-adjacent" Python ("a retrieval scorer, a token-budget allocator, and a tool-use orchestrator"), bar is "building something that works and reasoning about failure modes, not LeetCode-hard"; (3) 3–4 h take-home or extended live build: "a small Claude-powered application against a fictional customer brief", graded on "shipped behavior, API hygiene, and how the candidate handles ambiguous requirements"; (4) 60–90 min customer-conversation simulation where interviewer plays e.g. "VP of Engineering at a 5,000-person fintech evaluating Claude for an internal copilot", with challenge "Honestly, I'm not sure Claude does anything ChatGPT can't already do — convince me"; strong candidates ask about evaluation criteria, past failed AI deployments, compliance/latency/data-residency constraints; (5) virtual onsite: multi-tenant Claude deployment system design "with increasing emphasis on evaluation harnesses over RAG architecture", values interview, technical deep dive, bar-raiser. Article claims the customer round filters ~60% of candidates who passed coding, but cites no primary source — [Perspective](https://getperspective.ai/blog/anthropic-applied-ai-engineer-interview-process-frontier-lab-2026/markdown)
- techinterview.org repeats the same Anthropic loop as the reference loop (recruiter screen, technical phone screen, take-home or live coding, customer-conversation simulation, onsite weighted to system design) and says "the customer-conversation round quietly rejects many candidates who passed the coding stages" — [TI-Applied](https://www.techinterview.org/post/3233476824/ai-engineer-interview-rag-agents-evals/)
- Interview Query: "Anthropic AI Engineer interview typically runs 8-9 rounds" incl. take-home, CodeSignal, coding, system design, bug-fixing, behavioral; one candidate: "the take-home was the biggest ask by far", no feedback given; most common failure reason is the culture-fit round "based on AI ethics and safety" — [IQ-Anthropic](https://www.interviewquery.com/guides/anthropic-ai-engineer) (search snippet)
- Attributed Anthropic questions: GPU batching system design; "Investigate and mitigate a model that gives confident but factually wrong answers in high-risk contexts"; image-processing pipeline coding; duplicate-file scan; profiler-samples-to-trace; behavioral "confident then wrong", "conflicted with your personal values", "What GenAI skills do you think will be critical in the next year?" — [Aced-45](https://www.aced.io/blog/ai-engineer-interview-questions)
- Blind (Sept 2024): "Anthropic is code signal first, and then technical assessment" with concurrency questions; infra OA: LRU cache + task management system; later rounds: web crawler from scratch, LLM inference API system design, profiler output → trace events — Blind search snippets (https://www.teamblind.com/post/anthropic-interview-szdhw35i; fetch of thread returned 403)
- Anthropic's stated candidate guidance: AI tools can help prepare but "assessments and live interviews are meant to evaluate the candidate's own thinking" — search snippet via [Perspective]/Interview Query (not independently verified)

**Sierra (Agent Engineer)**
- Process 2–5 weeks: 30-min recruiter screen → 60-min coding on one DS problem (e.g. "Detect circular references in a spreadsheet where cells link to other cells") or "Design an agentic service for a given use case, such as a subscription-cancellation flow" → take-home "Build a customer-support AI agent for a fictional client, then choose which features to implement" (Sierra provides an OpenAI key; guide recommends a direct model-and-tools loop without a framework) → onsite 3×60 min: debugging ("Find and fix the bugs in this React application"), agent presentation ("Walk us through the agent you built and the design choices behind it"; "Why did you select the features you shipped?"; "What metrics and observability would you track if this agent went to production?"), behavioral. Sierra "has announced changes, including a system design round replacing the coding screen and an AI-assisted debugging pilot" — [Aced-Sierra](https://www.aced.io/guides/sierra-agent-engineer-interview)
- Attributed Sierra questions: "Explain how RAG works"; "Design an agentic AI system to power customer support for Spotify"; async exponential retry with timeouts and backoff — [Aced-45](https://www.aced.io/blog/ai-engineer-interview-questions)

**Cursor (Anysphere)**
- Recruiter screen → 60-min phone screen, one medium-hard problem "tilted toward applied AI-systems or editor primitives" ("implement a syntax-aware edit operation, handle streaming LLM output, model a file-tree diff") → take-home 4–8 h "on a realistic engineering problem… known to be substantial", AI tools allowed but "you must defend every line" → onsite 3–5 rounds: algorithms + applied coding ("multi-file edit coordination, context-retrieval for LLM prompts, streaming edit application"), system design ("Design the Cursor agent that edits multiple files based on a task description", "multi-file edits with verification and rollback"), ML/AI deep dive (evaluate whether a completion was helpful; context-retrieval trade-offs; speculative decoding/KV reuse), product/craft, behavioral — [TI-Cursor](https://www.techinterview.org/companies/cursor/)

**OpenAI**
- Attributed questions: "Implement a GPU credit management system"; "Design the OpenAI Playground"; "Develop a strategy for OpenAI's fine-tuning capabilities"; KV store with transactions; credit-expiry computation; encode/decode list of strings; infection-spread simulation; "How do you approach GenAI safety in consumer products?" — [Aced-45](https://www.aced.io/blog/ai-engineer-interview-questions)
- Blind 2024: one loop "two rounds — one each of coding (one of the leetcode tagged questions) and system design"; research engineer: two technical assessments (general coding, ML-specific coding) before onsite; platform engineer: take-home "build a webhook delivery system within 48 hours" then line-by-line code review — Blind search snippets (https://www.teamblind.com/post/openai-interview-yo6ac8sh; https://www.teamblind.com/post/openai-research-engineer-interview-u0w5roaw)
- Interview Query guide: 4–8 weeks, recruiter screen, technical assessments, take-home, onsite 4–6 rounds (snippet; page returned 429) — https://www.interviewquery.com/prep-guides/openai-ai-engineer

**Scale AI**: "Design an insurance-claims agent… using RAG" with token-cost control; "Design a system that supports ML models." — [Aced-45]
**Databricks**: "Design a document processing pipeline that ingests and indexes large volumes of heterogeneous documents for LLM use" — [Aced-45]
**Glean**: "Have you built any end-to-end agentic systems?" — [Aced-45]
**Perplexity**: "How would you define success metrics for an AI-oriented feature or product?" — [Aced-45]; no Blind/Glassdoor reports found.
**xAI**: multimodal file uploader into model context; KV store — [Aced-45]
**Google**: "Design an ML experiment tracking and analysis platform"; GenAI safety in consumer products — [Aced-45]

**Forum-level (no company named)**
- Candidate who "bombed two rounds" for LLM/AI engineer roles: "interviewers assume you know transformers cold" then move to RAG tradeoffs, agent design, eval strategy, production issues; "fumbled a question on retrieval eval that I should have known" — [Reddit-LLMDevs](https://reddit.sentinel-team.org/posts/1sn7hc8/snapshots/2026-04-17T05%3A37%3A44.74927Z)
- r/LangChain thread: coding "usually lighter than hardcore LeetCode"; expect "a small build exercise" (RAG, tool calling, eval harness, or agent workflow); agentic-tool rounds judge "how you scope and delegate to the AI"; "knowing what makes agents fail… matters more than knowing any framework's API"; system design "becoming important" (latency/cost, caching, observability, safety, fallbacks) — [Reddit-LangChain](https://reddit.sentinel-team.org/posts/1t1ywp7/snapshots/2026-05-09T00%3A32%3A05.66647Z)

### Inferences
- The customer-facing simulation round (Anthropic Applied AI; Sierra "present to a non-technical audience"; Decagon/Anthropic JDs demanding customer work) is a distinctive feature of agent-company loops versus generic SWE loops, and two independent prep guides single it out as the biggest rejection point.
- Attributed Anthropic/OpenAI questions in [Aced-45] mix roles (ML engineer, infra, product engineer); only a subset is agent-specific. Treat company attributions as role-ambiguous.

### Gaps
- No first-person 2025–2026 Glassdoor/Blind reports found for Sierra, Decagon, Harvey, Perplexity, Scale agent roles; Glassdoor "Sierra" results were for an unrelated retail/customer-service company (Glassdoor E31868).
- Blind threads could not be fetched (403); only 2023–2024 snippets available, pre-dating current agent-role loops.
- [Perspective]'s stage-by-stage Anthropic account cites "public Glassdoor reports" and Levels.fyi without links; its 60% figure is untraceable.

---

## Key Question 3: What do job descriptions for these roles list as required knowledge?

### Takeaway
Primary JDs converge on: Python (plus TypeScript at customer-facing startups), LLM APIs and agent frameworks, prompt engineering, tool/skill design, context-window management, evaluation frameworks/harnesses (datasets, regression, replay, benchmarks like SWE-bench/GAIA/τ-bench at staff level), observability/instrumentation, latency work (caching, parallel tool calls, subagents), cost discipline (per-task token budgets), MCP, and direct customer engagement.

### Cited Findings
- Harvey "Senior Software Engineer, Agents": "model selection decisions, manage context windows, create optimal tools"; "design practical evaluations that capture what 'excellent' means"; "Optimize agent performance through prompt engineering, model selection, tool design, skill writing, context window management, and eval harness development"; low-latency execution via "caching strategies, parallel tool calls, or subagent patterns"; "profile agent behavior, identify bottlenecks"; requires "Proficiency in Python and experience working with LLM APIs and agent frameworks", comfort "with when and how to use evaluations to drive quality", 6+ yrs; hiring at Mid/Senior/Staff; SF range $161K–$242K, NY staff $231K–$340K (aggregator figures) — [Harvey-JD](https://www.harvey.ai/company/careers/04eb457b-e985-4e3b-9635-0a2b867ada97); Monster mirrors e.g. https://www.monster.com/job-openings/staff-software-engineer-agents-new-york-ny--e3cd869e-256d-4670-917c-6a16b8054d67
- Anthropic "Applied AI Engineer, Enterprise": "Production experience with LLMs, including advanced prompt engineering, agent development and frameworks" plus evaluation frameworks, transcript analysis, MCP, deployment at scale; "Strong Python or TypeScript"; "Create scalable public and internal assets documenting the latest LLM prompting, eval, agentic, and architecture techniques"; 4+ yrs in FDE/Product Engineer/SWE/TPM roles; "Exceptional communication skills"; application requires "Why Anthropic?" answer and AI-policy confirmation; $200K–$320K; 3 office days/week — [Anthropic-JD](https://job-boards.greenhouse.io/anthropic/jobs/5057647008)
- Anthropic "Product Engineer, Applied AI" (Startups variant): "advising on implementation, agent design, and working alongside them to build at the frontier of AI"; builds "evaluation suites and architecture diagrams"; "Why Anthropic?" answers "often 200-400 words" — Greenhouse listings https://job-boards.greenhouse.io/anthropic/jobs/5055488008, https://job-boards.greenhouse.io/anthropic/jobs/5039498008 (search snippets)
- Decagon "Software Engineer, Agents": "work directly with enterprise customers to design, deploy, and optimize AI agents"; 2+ yrs production; preferred React/TypeScript/Python, founder/early-stage experience; posting does not mention prompts, evals or tools — [Decagon-JD](https://jobs.somacap.com/companies/decagon-2/jobs/51636466-agent-software-engineer); Decagon "Engineering Manager, Agents" requires Python+TypeScript, async programming, LLMs and agentic workflows — https://www.choppingblock.ai/jobs/engineering-manager-agents-at-decagon (snippet)
- Generic "AI Agent Engineer" postings: Python; LangGraph/CrewAI/AutoGen; RAG, vector DBs, OpenAI-style APIs; "agent workflows that use tool-calling, routing, memory, guardrails, and retries"; "function/tool calling, AI evaluation frameworks, agent orchestration" — [AgentJD-Lever](https://jobs.lever.co/jobgether/85f64172-e513-47c2-bdfe-51e2a2827335), https://jobs.lever.co/jobgether/60e2fcfa-1d70-4951-8a98-9be0d0cc346e, https://djinni.co/jobs/825253-ai-agent-engineer/ (snippets)
- Senior: "evaluation frameworks including datasets, automated testing, replay systems, and performance metrics"; Staff: "define the eval framework, establish baselines, and systematically improve completion metrics", familiarity with "SWE-bench, GAIA, or τ-bench", "setting per-task token budgets and tracking cost per completed workflow"; Junior: "create simple evaluations and tests to measure agent reliability and accuracy" — [AgentJD-Staff](https://100hires.com/j/TYaFyGz) and Lever postings (snippets)
- Hiring-side: "eval literacy is the closest thing to a hard differentiator in AI engineering right now"; "can a candidate design a test that tells them their own system is getting worse"; candidates who "talk unprompted about what happens when the model returns nonsense, when the API is down" are a stronger signal than a happy-path demo; "walk through your Claude Code workflow out loud" — [Gauntlet](https://gauntletai.com/night-school/what-hiring-managers-actually-look-for-in-ai-engineers) and https://www.callmissed.com/blog/hiring-ai-engineers-2026 (snippets)

### Inferences
- JD vocabulary ("eval harness", "context window management", "skill writing", "subagent patterns", "parallel tool calls", "transcript analysis", "MCP") maps one-to-one onto the question clusters in KQ1 sections 4, 5, 7, 9, 11 — these are the probe areas.
- Customer-facing competence is a hard requirement at Anthropic Applied AI, Decagon, Harvey (partner with customers/PMs) — explaining the customer-simulation and presentation rounds.

### Gaps
- Sierra's own careers page JD for Agent Engineer was not retrieved (search returned only prep guides).
- OpenAI/Google/Meta/Microsoft/Amazon/Databricks/Scale/Perplexity agent-engineer JDs were not retrieved in this pass.

---

## Key Question 4: What do interviewers say they look for?

### Takeaway
Interviewer-authored sources agree the signal is judgment about stochastic systems — can the candidate design an eval, name failure modes, trade quality against cost/latency, and explain why agent boundaries were drawn — rather than framework API knowledge; "passion/tinkerer" signals and strong opinions on current models are also explicitly graded.

### Cited Findings
- PromptLayer (Jonathan Pedoeem, 2025-07-11): interviewers look for "Passion for LLMs… strong opinions on models and trade-offs… boring textbook answers are a red flag"; "Tinkerer mindset… willingness to discuss failed experiments and workarounds"; whiteboard graded on "clear sub-task decomposition, logical flow between steps, handling of errors and edge cases, and prompt structure for each component… why agent boundaries were chosen"; take-home graded on "whether the system works, whether it has meaningful evaluation metrics, and whether the candidate can explain design choices. Thoughtful architecture matters more than perfect accuracy." Recommended structure: whiteboard multi-step agent design (flow chart, model per step, optional prompt) + take-home multi-step agent with evaluation component — [PromptLayer](https://blog.promptlayer.com/the-agentic-system-design-interview-how-to-evaluate-ai-engineers/)
- Tian Pan (2026-04-27): current loops "screen for implementation skill… but miss judgment about whether a stochastic system is working"; proposes 40-min eval-design round where the candidate "does not touch a prompt"; scoring: unnamed failure modes, rubric precision, live-distribution coverage, judge validated vs human labels (75–90% agreement); companion trade-off round ("premium model wins by 3 points at 6× cost…"; "tool call fixes 40% of failures but adds 800ms to a voice product"); debugging-replay round on a production trace; advice: "Replace the leetcode screen with a take-home eval-design exercise, and replace the algorithmic part of system design with a trade-off and architecture session" — [TianPan](https://tianpan.co/blog/2026/04/27/ai-engineer-interview-eval-design)
- Hamel Husain (Maven, 2026-10-06; co-teaches with Shreya Shankar, "Applied Evals at OpenAI"): evals "can often be the 'weed out' part of interviews"; traps: "generic metrics", "composite scores", "hill-climbing"; interviewers look for "recognizing traps… product judgment: weighing quality against cost and latency… clear, structured reasoning, with close examination of the data" — [Hamel](https://maven.com/p/07e7c7/cracking-the-ai-evals-interview)
- techinterview.org (applied-AI): each round maps to a capability — phone screen "working knowledge of retrieval and prompting"; take-home "building and measuring an LLM system end to end"; live debugging "fixing an integration by hand"; system design "designing eval and serving paths around a model you don't own"; customer round "scoping a fuzzy problem" — [TI-Applied](https://www.techinterview.org/post/3233476824/ai-engineer-interview-rag-agents-evals/)
- Sierra rubric (per Aced): take-home judged on "product judgment, realistic prioritization, agent fundamentals, production metrics and observability, and design rationale"; presentation on "defending design choices… explaining complex topics to non-technical audiences" — [Aced-Sierra](https://www.aced.io/guides/sierra-agent-engineer-interview)
- Cursor: AI tools allowed on take-home "but you must defend every line of code you submit" — [TI-Cursor](https://www.techinterview.org/companies/cursor/)
- Anthropic (per Perspective): customer-simulation strong candidates "ask about evaluation criteria, past failed AI deployments, fixed constraints (compliance, latency, data residency), and the workflow being replaced. They don't pitch or open a code editor." — [Perspective](https://getperspective.ai/blog/anthropic-applied-ai-engineer-interview-process-frontier-lab-2026/markdown)

### Inferences
- Three independent interviewer voices (PromptLayer, Tian Pan, Hamel) converge on "eval + trade-off judgment" as the primary signal; this is stronger evidence than any single question bank.

### Gaps
- No hiring-side writing from Chip Huyen, Eugene Yan, Jason Liu, swyx/Latent Space on interview questions was found in this pass (searches returned only profiles or unrelated content).
- No podcast transcripts with hiring managers were retrieved.

---

## Key Question 5: Are there coding / take-home formats specific to agent roles?

### Takeaway
Yes. Four recurring formats: (1) "build an agent for a fictional client/brief" take-home (Sierra, Anthropic, PromptLayer-style) with later presentation/defense; (2) "build a Q&A/RAG system plus an eval set and report metrics" take-home; (3) "build an automated eval and calibrate against human labels" take-home; (4) live rounds: implement a minimal agent/tool loop with step budget, or debug an agent that loops/misuses tools. AI assistants are often allowed but candidates must defend every line.

### Cited Findings
- Sierra take-home: "Build a customer-support AI agent for a fictional client, then choose which features to implement"; OpenAI key provided; follow-ups "Which two features would you prioritize for this client, and why?", "What metrics and observability would you track…?", "Why did you choose your agent's architecture?"; onsite includes "at least one deep discussion of the take-home assignment and a debugging interview" — [Aced-Sierra](https://www.aced.io/guides/sierra-agent-engineer-interview)
- Anthropic (unverified): 3–4 h "small Claude-powered application against a fictional customer brief", window ~1 week, most submit in 48–72 h, Python easiest; graded on "shipped behavior, API hygiene, and how the candidate handles ambiguous requirements without asking the interviewer" — [Perspective](https://getperspective.ai/blog/anthropic-applied-ai-engineer-interview-process-frontier-lab-2026/markdown)
- RAG+eval take-home: "about 50 markdown docs, build a Q&A system, write an eval set of 20 questions, and report recall@5 and faithfulness"; eval take-home: "a prompt plus about 30 input/output pairs… build an automated eval for faithfulness and relevance, then calibrate it against provided human labels" (expected: deterministic checks — exact match, regex, verifying cited sources exist — plus LLM-as-judge for the rest); live debugging: "This agent loops forever on some inputs. Find out why and fix it." — [TI-Applied](https://www.techinterview.org/post/3233476824/ai-engineer-interview-rag-agents-evals/)
- PromptLayer take-homes: "Build an agent that reads a CSV of customer data and generates personalized email campaigns. Include evaluation metrics for quality."; "Create a document Q&A system with citation tracking. The agent should handle multi-hop questions."; "Implement a code review agent that can analyze Python files and provide actionable feedback. Include accuracy evaluation." — [PromptLayer](https://blog.promptlayer.com/the-agentic-system-design-interview-how-to-evaluate-ai-engineers/)
- GitHub question bank coding items: "Implement a minimal agent loop with tool dispatch, error handling, and a step budget."; "Write a function calling (tool use) handler for an LLM API."; "Build a simple AI agent with tool use (e.g., calculator, web search)."; "Implement a conversation memory system… (sliding window, summary, buffer)"; "Implement an LLM output guardrails system that checks for off-topic responses and PII leakage."; "Build a multi-agent system where agents have different roles…" — [GH-Shekhar](https://github.com/amitshekhariitbhu/ai-engineering-interview-questions)
- PracHub practice formats: "Build a Small Agent or RAG Tool with the Mistral API" (hands-on integration, failure handling, streaming); "Design an Agent Harness and Evaluation System" — [PracHub](https://prachub.com/resources/ai-engineer-interview-questions-2026-rag-agents-evals-and-production-systems)
- Cursor: take-home 4–8 h on the real Cursor codebase; applied coding "handle streaming LLM output", "context-retrieval for LLM prompts", "multi-file edit coordination" — [TI-Cursor](https://www.techinterview.org/companies/cursor/)
- Hamel Husain evals take-home: "TreeBuddy" data challenge; report should structure experiments with baselines, present trade-offs, limitations, next steps; Q&A covers "expected time for take-home assignments" — [Hamel](https://maven.com/p/07e7c7/cracking-the-ai-evals-interview)
- Tian Pan: proposed take-home eval-design exercise replacing LeetCode screen — [TianPan](https://tianpan.co/blog/2026/04/27/ai-engineer-interview-eval-design)
- Forum: "a small build exercise" (RAG, tool calling, eval harness, or agent workflow); "you might get take homes" — [Reddit-LangChain](https://reddit.sentinel-team.org/posts/1t1ywp7/snapshots/2026-05-09T00%3A32%3A05.66647Z)
- Aced coding-round tip when AI assistant allowed: "narrate the plan, read all generated code, and handle edge cases and tests" — [Aced-45](https://www.aced.io/blog/ai-engineer-interview-questions)

### Inferences
- The take-home + defend-it-live pairing is the canonical agent-role format; the defense questions are predictable (feature prioritization, architecture rationale, production metrics/observability, what you'd do next) and are listed verbatim for Sierra.
- Framework-free implementation is advised ([Aced-Sierra]: "direct model-and-tools loop without a framework"), consistent with forum advice that failure-mode knowledge beats framework API knowledge.

### Gaps
- No public grading rubric from a company itself; all rubrics are third-party or interviewer-opinion.
- No verified ReAct-from-scratch live-coding prompt attributed to a named company (closest: [GH-Shekhar] generic item and [Perspective]'s unverified "tool-use orchestrator").
