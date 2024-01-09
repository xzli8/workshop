package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _126findLadders {

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
