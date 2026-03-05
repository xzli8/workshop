
Scopely 面试题整理（17 条 → 10 类 + 1 TBD）

一、缓存 / 系统设计

1. LRU 缓存（原题 1、5）

▎ 实现 LRU 缓存，容量满时淘汰最久未使用的数据，get/put 都要 O(1)（哈希表 + 双向链表）。
▎ LeetCode 146. LRU Cache（相关 460. LFU Cache）

二、随机 / 概率

2. 按品质决定掉落率（原题 2）

▎ 物品按权重(品质)随机掉落，权重越高概率越大。前缀和 + 二分。
▎ LeetCode 528. Random Pick with Weight（相关 497）

三、字符串匹配（KMP / RK）

3. 图片拼接（原题 3）

▎ 两张按行组织的图 int[][]，求 image1 后缀 == image2 前缀 的最大重叠行数 k，再拼成 image1 + image2[k:]。把"一行"当成"一个字符"。
▎ LeetCode 1392. Longest Happy Prefix；28. Find First Occurrence (KMP)

四、编码解码（往返安全）

4. token 编解码（原题 4）

▎ 解码语法 token = 数字 + 'x' + 字符（3xa→aaa）。设计 Encode，保证简单串原样保留，且 Decode(Encode(x))==x——会被误读成 token 的字面量要转义（1xc）。
▎ LeetCode 271. Encode and Decode Strings（思想同源：结构 vs 内容分离）

五、压缩编码

5. 数字压缩（原题 6）

▎ 把一串数字压缩：游程(连续相同→值+个数) 或 差分(存相邻差值)。
▎ LeetCode 443. String Compression；1313. Decompress RLE List；900. RLE Iterator；差分线 370. Range Addition

六、BFS 网格 / 图最短路

6a. 最近的宝箱（单源）（原题 12）

▎ 二维地图(X墙 / S空地 / P玩家 / T宝箱)，四向移动不可穿墙，求离 P 最近的宝箱坐标。从 P 单源 BFS，第一个碰到的 T 即最近。
▎ LeetCode 1091. Shortest Path in Binary Matrix；1926. Nearest Exit from Maze

6b. 所有节点到 source 的距离（多源）（原题 7、15）

▎ source / 墙 / 普通点的图，求每个普通点到最近 source 的距离（即 Walls and Gates）。多源 BFS：所有 source 一次性入队。
▎ LeetCode 286. Walls and Gates；542. 01 Matrix；994. Rotting Oranges
▎ 要点：多源 = 单源只多塞几个种子，骨架不变。

七、回溯

7. 字母大小写全排列（原题 11、17）

▎ 字符串里字母可大写或小写、数字不变，输出所有组合（a1b4 → a1b4, a1B4, A1b4, A1B4）。决策树 / DFS。
▎ LeetCode 784. Letter Case Permutation（相关 78 / 46 / 17）

八、自定义字典序 + 拓扑排序

8. 外星字典序三连（原题 10、14）

▎ (1) 基础：按给定字母顺序比较两个字符串大小（逐字符比 + 前缀规则）；
▎ (2) 验证：给定顺序，判断单词数组是否已排好序；
▎ (3) 反推：给排好序的单词，倒推一种合法字母顺序（相邻词第一个差异 → 建边 → 拓扑排序，注意环 / 非法前缀）。
▎ LeetCode 953. Verifying an Alien Dictionary；269. Alien Dictionary；前置 210. Course Schedule II

九、树

9a. BST + 前缀推荐（原题 9）

▎ (1) 二叉搜索树基本操作（查/插/校验）；(2) 输入前缀返回推荐词，用 Trie 实现。
▎ LeetCode 98. Validate BST / 700. Search in BST；1268. Search Suggestions System（前缀推荐，⭐ 完全对口）；208. Implement Trie

9b. 不可变树更新（原题 13）

▎ 点分隔路径 user.address.city 定位叶子，对其 value 应用 transform，返回新树且不可变原树（路径复制 + 结构共享）；缺段 / 非叶 / transform 返回 null 则原样返回 root。
▎ LeetCode 无直接对口（持久化数据结构 / 函数式更新概念，原型在 Redux·Immer·Git·Clojure update-in）；导航部分类似 588. In-Memory File System / 1166. Design File System

十、编辑距离

10. edit distance = 1（原题 16）

▎ 判断两个字符串是否恰好相差一次编辑(增/删/改)。长度差 + 一次扫描的 O(n) 贪心，不用 DP。
▎ LeetCode 161. One Edit Distance（通用版 72. Edit Distance）

---
TBD（描述太模糊，待补充细节）

原题 8：「用到字符串分析，也涉及一些基础的数据结构，树，栈」

▎ 没有具体题面，无法定位。从"字符串 + 栈"推测，可能是表达式求值 / 括号匹配 / 字符串解码一类。
▎ 候选方向：20. Valid Parentheses、394. Decode String、150. 逆波兰表达式、树的遍历 —— 需要你回忆更多细节再归类
