# Canonical — 全球远程 Python / Go 后端（多个通用通道）

来源：Canonical 官方 Greenhouse 招聘板（https://job-boards.greenhouse.io/canonical ，在招 314 个，2026-11-04 抓取），JD 更新日期均为 2026-08-05（常年开放的通用通道）。
薪资：JD 不标（按地区、经验、绩效定，另有年度绩效奖金），Glassdoor 薪资页需登录，未查到。
注意：在中国是中国法人直签还是 EOR（第三方代雇）代签，HR 电话时问清；北京办公室的坐班岗都是内核 / 硬件适配 / 测试，不对口，只看 Worldwide / APAC 远程岗。

## 公司
- Ubuntu 母公司，2004 年 Mark Shuttleworth 在伦敦创立（创始人领导、已盈利、未上市）
- 1200+ 人分布在 75+ 国家，几乎全员远程（团队每年线下聚 2–4 次，每次最长两周）
- 尽量让你和经理、多数同事在相近时区（亚太团队 -> 不用配合欧美开夜会）
- 技术栈：Web 服务和工具以 Python、Go 为主，前端 React，大量 K8s / LXD / Linux
- 福利：学习预算每年 2000 美元，年度调薪，长途出差升舱 + Priority Pass

## 岗位
| 匹配 | 岗位 | 地点 | 链接 |
|---|---|---|---|
| 72 | Microservices Engineer | 每个时区 | https://job-boards.greenhouse.io/canonical/jobs/5191982 |
| 72 | Software Engineer - App Stores | Worldwide | https://job-boards.greenhouse.io/canonical/jobs/3159992 |
| 60 | Python Engineer | 每个时区 | https://job-boards.greenhouse.io/canonical/jobs/5143074 |
| 65 | Senior/Staff/Principal Engineer | 每个时区 | https://job-boards.greenhouse.io/canonical/jobs/5142887 |
| 60 | Python and Kubernetes Software Engineer - Data, Workflows, AI/ML & Analytics | Worldwide | https://job-boards.greenhouse.io/canonical/jobs/5703396 |

- Microservices Engineer：Go / Python 的 REST 微服务通用通道，各级别都招（服务百万级 Ubuntu 客户端）
  - 关注点：API 设计与治理、数据库性能、多区域部署、模糊测试（fuzzing）、分布式追踪与性能剖析、DevOps
  - 要求：Python 和/或 Go，关系型 + 非关系型数据库，CI/CD，系统编程或可扩展 Web 服务，Linux（Debian/Ubuntu 优先）
  - 加分：LXD / Docker / K8s，公有云或私有云，设计和运维分布式系统，性能与安全
- Software Engineer - App Stores：Snap Store、Charmhub 的后端服务（主要 Python，少量 Go）
  - 职责：给 CLI 和 Web 前端写 API，从需求到上线再到运维，评审代码和设计，每年出差 2–4 周
  - 要求：用 Python 开发对外 API 和 Web 应用，偏后端和基础设施，会关系型 / 非关系型数据库建模和查询调优，习惯在 Ubuntu 上开发部署
- Python Engineer：所有 Python 团队共用的通道，进来后再分团队（保底，和上面两个同时投）
  - 加分：Go / Rust，REST / gRPC，SQL / NoSQL，Web 前端，Debian 打包，公有云 / OpenStack
- Senior/Staff/Principal Engineer：高级别通用通道，第一轮后再匹配团队
  - 要求：技术领导力和带团队节奏，Go / Rust / Python / C 至少一门深入，注重性能和细节
- Python and K8s - AI/ML：MLOps 基础设施（Kubeflow、MLflow、Airflow、Trino、ClickHouse），不是 LLM Agent 应用开发，要求 K8s
- 不投：Web Frontend Engineer（纯前端，降级）、Distributed Systems Engineer（列表标 Worldwide，正文写只招 EMEA）、Senior Software Engineer (Backend) 和 Commercial Systems 系列（只招 EMEA）

## 共同要求
- 高中和大学成绩都要优秀（会问高中成绩、高中英语成绩）
- 计算机或理工科本科，英语书面和口语达到工作水平
- 每年能出差两次，每次最长两周

## 面试流程
- 书面面试：几个小时、30 多道问答题（教育经历、成绩、动机都要写，刷人最多的一轮）
- 编程测验 -> 编程作业 -> 心理测评 / 逻辑推理测试（Thomas International GIA）-> 多轮团队面试（至少 6 个环节）
- 周期平均 40 天左右，也有拖几个月的（Glassdoor 口碑偏负面，主要嫌流程长、冷冰冰）

## 差距
- 优势：中科大硕士（看重学历），Python 后端（FastAPI、asyncio），美团 / 亚马逊的大规模分布式系统，带 5 人项目
- 缺：Go 没写在简历上，Linux / Ubuntu 深度和 K8s 不突出，没有开源贡献
- 准备：简历突出 Python、API 设计、AWS 部署，补 Go 基础，写清 Linux / Docker / K8s 经验，给 Canonical 开源项目提一两个 PR
