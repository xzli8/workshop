package interview.amazon_JST_2024.design;

public class CountHotSearchWords {

    /**
     * 系统设计：某app的搜索框，用户会输入搜索词进行搜索，这个搜索接口的qps非常高，搜索词的数量是千万级别，要求以天为维度统计每个搜索词出现的次数，可以按天查询每个搜索词出现的次数，比如前7天，前30天等。
     *
     * ### 1. 需求澄清（这一步最关键）
     * - 查询的是什么：给定一个词，查它最近 N 天的总次数（按词查询，point query），不是排行榜
     *   - 类名叫 HotSearchWords，要问清楚需不需要"热词 top K"（题面没有要求，放到扩展部分）
     * - N 的范围：任意 N（7、30 只是例子），上限是多少（比如 365 天）
     * - 包不包括今天：今天的计数要实时（分钟级），还是 T+1 就可以
     * - 要不要精确：词只有千万级，精确计数不贵，默认精确
     * - 词怎么归一化：大小写、首尾空格、全角半角（决定了 key 的定义）
     *
     * ### 2. 估算
     * - 写入：QPS 很高，假设 100k/s（要和面试官确认）
     * - 词的数量：每天约 10M 个不同的词
     * - 存储：每天 10M 行 × 约 40B（词 + 日期 + 计数）≈ 400MB，一年约 3.65B 行、约 150GB
     * - 结论：难点是高写入 + 热词；基数只有千万级，精确计数完全可行
     *
     * ### 3. API
     * - GET /search-words/{word}/count?days=N -> { word, count, from, to }
     *
     * ### 4. 高层设计
     * Search Service -> Kafka(search-events，按 word 分区)
     *   -> Flink 预聚合（1min tumbling，按归一化后的 word 求和）
     *   -> DB：word_daily += count（今天这一行每分钟更新一次）
     * Query Service -> 按 word 做范围扫描，读 N 行求和 -> 缓存
     *
     * - 存储的 key 设计（这道题的核心）
     *   word_daily
     *     partition key: word   （同一个词的所有日期放在一起）
     *     sort key:      day    （按日期排序，最近 N 天就是一次范围扫描）
     *     count
     *   - 选型：Cassandra / DynamoDB / HBase（rowkey = word#yyyyMMdd），或者按 word 分片的 Postgres
     *   - 不要用"日期做行键、词做列"：查一个词 30 天要读 30 行，每天一行有千万列，当天那一行还会成为写入热点
     *   - 不要用 InfluxDB 这类时序库：千万级的 tag 正是它们的弱点（高基数）
     * - 写入：count = count + :minute_count，写到今天那一行
     *   - 今天的计数自然就是实时的，不需要另外做实时和离线的合并
     * - 查询：WHERE word = ? AND day BETWEEN today-N+1 AND today
     *   - 最多读 N 行，在同一个分区里完成范围扫描
     *
     * ### 5. Deep dives
     * - 热词（写热点，比如 "iphone"）
     *   - 按 word 分区，只对热词加盐：word:0-N
     *   - Flink 按加盐后的 key 聚合，输出时去掉后缀，在 DB 里用 += 合并（加法满足交换律，写入顺序无所谓）
     *   - 热词名单：静态配置，或者动态检测（超过阈值后下发配置）
     * - N 很大时查询变慢
     *   - 每天保存前缀和 cum(word, day)，任意区间 = cum(today) - cum(today-N)，只读 2 行，O(1)
     *   - 历史日期的前缀和不会再变化，只有今天那一行每分钟更新
     *   - 或者保存月度汇总：365 天 = 12 个月 + 若干天
     * - 读缓存
     *   - 历史部分不会再变化，可以长期缓存
     *   - 只有今天的计数每分钟变化，查询时用"历史缓存 + 今天的值"拼出结果
     *   - 热门的词再加进程内缓存
     * - 幂等和容错
     *   - Flink checkpoint（state + Kafka offset）+ 重放
     *   - += 不是幂等的：在同一个事务里写"已处理到哪一分钟"的标记，重放时跳过
     * - 数据保留：超过上限 N 的日数据设 TTL 过期，或者压缩成月度汇总
     * - 扩展：热词 top K
     *   - 基数只有千万级，每天一个 Redis ZSET 就能放下（约 1GB，ZINCRBY）
     *   - 最近 N 天的 top K：ZUNIONSTORE 合并 N 个日 ZSET，或者每天预计算 7d / 30d 的结果
     *   - 不需要 Top-K YouTube Views 里的分片堆、Merger、CMS
     *
     * ### 6. 和 Top-K YouTube Views 对比（docs/system-design/topk/topk.html）
     * - 相同：写入侧和可靠性
     *   - 高写入：Kafka + Flink 分钟级预聚合，DB 只接收聚合后的结果
     *   - 写热点：按 key 分区，只对热 key 加盐，在 DB 里用 += 合并
     *   - 幂等和容错：checkpoint + 重放，+= 配合去重标记
     * - 不同：读取侧决定了设计的重心
     *   - 查询类型：Top-K 是全局排序取前 K；本题是按 key 查一段日期的合计
     *   - 跨分片：Top-K 要由 Merger 合并各分片的 top K；本题不需要（一个词只在一个分片）
     *   - 窗口：Top-K 是固定的 1h / 1d / 1month / all-time；本题是任意 N 天（用日表 + 前缀和）
     *   - 基数：Top-K 是 3.6B，精确计数代价高，所以要考虑 CMS；本题是千万级，精确计数很便宜
     *   - 存储设计的重点：Top-K 是不在 count 上建索引，排序交给堆；本题是 (word, day) 的主键设计
     *
     * 一句话：写入侧（Kafka -> Flink 预聚合 -> 加盐 -> +=）可以直接复用 Top-K 的方案；
     * 本题的重点在读取侧：(word, day) 的主键设计、任意 N 天的范围求和（前缀和），以及今天的实时计数。
     */
}
