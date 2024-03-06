package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _126findLadders {


    public static class Solution0 {

        /**
         BFS + DFS：官方题解
         分析：在BFS的过程中同时构建了两个Map，后续DFS的时候作为缓存查询，加速DFS过程
         */
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            List<List<String>> res = new ArrayList<>();
            // 因为需要快速判断扩展出的单词是否在 wordList 里，因此需要将 wordList 存入哈希表，这里命名为「字典」
            Set<String> dict = new HashSet<>(wordList);
            // 特殊用例判断
            if (!dict.contains(endWord)) {
                return res;
            }

            dict.remove(beginWord);

            // 第 1 步：广度优先搜索建图
            // 记录扩展出的单词是在第几次扩展的时候得到的，key：单词，value：在广度优先搜索的第几层
            Map<String, Integer> steps = new HashMap<String, Integer>();
            steps.put(beginWord, 0);
            // 记录了单词是从哪些单词扩展而来，key：单词，value：单词列表，这些单词可以变换到 key ，它们是一对多关系
            Map<String, List<String>> from = new HashMap<String, List<String>>();
            int step = 1;
            boolean found = false;
            int wordLen = beginWord.length();
            Queue<String> queue = new ArrayDeque<String>();
            queue.offer(beginWord);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String currWord = queue.poll();
                    char[] charArray = currWord.toCharArray();
                    // 将每一位替换成 26 个小写英文字母
                    for (int j = 0; j < wordLen; j++) {
                        char origin = charArray[j];
                        for (char c = 'a'; c <= 'z'; c++) {
                            charArray[j] = c;
                            String nextWord = String.valueOf(charArray);
                            if (steps.containsKey(nextWord) && step == steps.get(nextWord)) {
                                from.get(nextWord).add(currWord);
                            }
                            if (!dict.contains(nextWord)) {
                                continue;
                            }
                            // 如果从一个单词扩展出来的单词以前遍历过，距离一定更远，为了避免搜索到已经遍历到，且距离更远的单词，需要将它从 dict 中删除
                            dict.remove(nextWord);
                            // 这一层扩展出的单词进入队列
                            queue.offer(nextWord);

                            // 记录 nextWord 从 currWord 而来
                            from.putIfAbsent(nextWord, new ArrayList<>());
                            from.get(nextWord).add(currWord);
                            // 记录 nextWord 的 step
                            steps.put(nextWord, step);
                            if (nextWord.equals(endWord)) {
                                found = true;
                            }
                        }
                        charArray[j] = origin;
                    }
                }
                step++;
                if (found) {
                    break;
                }
            }

            // 第 2 步：回溯找到所有解，从 endWord 恢复到 beginWord ，所以每次尝试操作 path 列表的头部
            if (found) {
                Deque<String> path = new ArrayDeque<>();
                path.add(endWord);
                backtrack(from, path, beginWord, endWord, res);
            }
            return res;
        }

        public void backtrack(Map<String, List<String>> from, Deque<String> path, String beginWord, String cur, List<List<String>> res) {
            if (cur.equals(beginWord)) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (String precursor : from.get(cur)) {
                path.addFirst(precursor);
                backtrack(from, path, beginWord, precursor, res);
                path.removeFirst();
            }
        }

    }



    public static class Solution1 {

        /**
         BFS + DFS(回溯)：先用BFS找最短步数，然后用DFS(回溯)找最短步数对应的所有路径。这样做的思路没问题，但超时了~
         时间复杂度：O(N^M)，N为单词长度，M为最短步数
         空间复杂度：O(N+M)
         */
         public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
             dfs(beginWord, endWord, new HashSet<>(wordList), new HashSet<>(), ladderLength(beginWord, endWord, wordList), Stream.of(beginWord).collect(Collectors.toList()));
             return res;
         }

         private final List<List<String>> res = new ArrayList<>();

         // DFS(回溯)找最短步数对应的所有路径
         private void dfs(String cur, String target, Set<String> dict, Set<String> visited, int len, List<String> path) {
             if (len == 0) {
                 if (cur.equals(target)) {
                     res.add(new ArrayList<>(path));
                 }
                 return;
             }
             char[] cs = cur.toCharArray();
             for (int i = 0; i < cs.length; i++) {
                 char originChar = cs[i];
                 for (char c = 'a'; c <= 'z'; c++) {
                     if (c == originChar) continue;
                     cs[i] = c;
                     String next = String.valueOf(cs);
                     if (dict.contains(next) && !visited.contains(next)) {
                         visited.add(next);
                         path.add(next);
                         dfs(next, target, dict, visited, len - 1, path);
                         path.remove(path.size() - 1);
                         visited.remove(next);
                     }
                 }
                 cs[i] = originChar;
             }
         }

         // BFS找最短步数("127.单词接龙")
         public int ladderLength(String beginWord, String endWord, List<String> wordList) {
             // 转换成set，方便后续判断
             Set<String> dict = new HashSet<>(wordList);
             if (!dict.contains(endWord)) return 0;
             // 初始化
             int n = beginWord.length();
             Set<String> visited = new HashSet<>();
             visited.add(beginWord);
             Queue<String> q = new LinkedList<>();
             q.offer(beginWord);
             int step = 0;
             // 遍历
             while (!q.isEmpty()) {
                 int size = q.size();
                 for (int i = 0; i < size; i++) {
                     String cur = q.poll();
                     if (cur.equals(endWord)) return step;
                     // 遍历每一位，尝试进行替换
                     char[] cs = cur.toCharArray();
                     for (int j = 0; j < n; j++) {
                         char originChar = cs[j];
                         for (char c = 'a'; c <= 'z'; c++) {
                             if (c == originChar) continue;
                             cs[j] = c;
                             String next = String.valueOf(cs);
                             if (dict.contains(next) && !visited.contains(next)) {
                                 visited.add(next);
                                 q.offer(next);
                             }
                         }
                         cs[j] = originChar;
                     }
                 }
                 step++;
             }
             return 0;
         }


    }



    public static class Solution2 {

        @Test
        public void test() {
            System.out.println(findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
        }

        /**
         BFS：尝试在BFS的过程中计算路径，但这样做不对，why?
         */
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            List<List<String>> res = new ArrayList<>();

            // 转换成set方便后续判断
            Set<String> dict = new HashSet<>(wordList);
            if (!wordList.contains(endWord)) return res;

            // 初始化
            int n = beginWord.length();
            Set<String> visited = new HashSet<>();
            visited.add(beginWord);
            Queue<String> q = new ArrayDeque<>();
            q.offer(beginWord);
            Queue<List<String>> paths = new ArrayDeque<>();
            paths.offer(Arrays.asList(beginWord));
            int step = 1;

            // 遍历
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    String cur = q.poll();
                    if (cur.equals(endWord)) {
                        for (List<String> path : paths) {
                            if (path.size() == step && path.get(path.size() - 1).equals(endWord)) res.add(path);
                        }
                        return res;
                    }

                    // 遍历所有位置，尝试进行替换
                    List<String> path = new ArrayList<>(paths.poll());
                    char[] cs = cur.toCharArray();
                    for (int j = 0; j < n; j++) {
                        char originChar = cs[j];
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == originChar) continue;
                            cs[j] = c;
                            String next = String.valueOf(cs);
                            if (dict.contains(next) && !visited.contains(next)) {
                                visited.add(next);
                                q.offer(next);
                                path.add(next);
                                paths.offer(new ArrayList<>(path));
                                path.remove(path.size() - 1);
                                if (next.equals(endWord)) visited.remove(next);
                            }
                        }
                        cs[j] = originChar;
                    }
                }
                step++;
            }
            return res;
        }


    }

}
