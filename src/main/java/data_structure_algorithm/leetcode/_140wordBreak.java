package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _140wordBreak {

    public static class Solution1 {

        /**
         DFS
         */
        public List<String> wordBreak(String s, List<String> wordDict) {
            backtrace(s, new HashSet<>(wordDict), 0, new ArrayList<>());
            return res;
        }

        private List<String> res = new ArrayList<>();
        private void backtrace(String s, Set<String> wordDict, int startIndex, List<String> path) {
            if (startIndex == s.length()) {
                res.add(String.join(" ", path));
                return;
            }

            for (int i = startIndex; i < s.length(); i++) {
                String str = s.substring(startIndex, i + 1);
                if (wordDict.contains(str)) {
                    path.add(str);
                    backtrace(s, wordDict, i + 1, path);
                    path.remove(path.size() - 1);
                }
            }
        }

    }

}
