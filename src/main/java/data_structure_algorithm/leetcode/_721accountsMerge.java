package data_structure_algorithm.leetcode;

import java.util.*;

public class _721accountsMerge {

    public static class Solution1 {

        /**
         UnionFind
         */
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            // 构建映射
            int emailCount = 0;
            Map<String, Integer> emailToIndex = new HashMap<>();
            Map<String, String> emailToName = new HashMap<>();
            for (List<String> account : accounts) {
                String name = account.get(0);
                for (int i = 1; i < account.size(); i++) {
                    String email = account.get(i);
                    if (!emailToIndex.containsKey(email)) {
                        emailToIndex.put(email, emailCount++);
                        emailToName.put(email, name);
                    }
                }
            }

            // 用并查集合并
            UnionFind uf = new UnionFind(emailCount);
            for (List<String> account : accounts) {
                String firstEmail = account.get(1);
                int firstIndex = emailToIndex.get(firstEmail);
                for (int i = 2; i < account.size(); i++) {
                    String nextEmail = account.get(i);
                    int nextIndex = emailToIndex.get(nextEmail);
                    uf.union(firstIndex, nextIndex);
                }
            }
            Map<Integer, List<String>> index2Emails = new HashMap<>();
            for (String email : emailToIndex.keySet()) {
                int rootIndex = uf.find(emailToIndex.get(email));
                List<String> emails = index2Emails.getOrDefault(rootIndex, new ArrayList<>());
                emails.add(email);
                index2Emails.put(rootIndex, emails);
            }

            // emails排序后返回
            List<List<String>> res = new ArrayList<>();
            for (List<String> emails : index2Emails.values()) {
                Collections.sort(emails);
                String name = emailToName.get(emails.get(0));
                List<String> account = new ArrayList<>();
                account.add(name);
                account.addAll(emails);
                res.add(account);
            }
            return res;
        }

        class UnionFind {
            private int count;
            private int[] parents;

            public UnionFind(int n) {
                this.count = n;
                parents = new int[n];
                for (int i = 0; i < n; i++) {
                    parents[i] = i;
                }
            }

            // 返回某节点x的根节点(核心方法)
            public int find(int x) {
                if (parents[x] != x) {
                    parents[x] = find(parents[x]);
                }
                return parents[x];
            }

            public void union(int p, int q) {
                int rootP = find(p), rootQ = find(q);
                if (rootP == rootQ) return;
                parents[rootP] = rootQ;
                count--;
            }

            public int count() {
                return count;
            }
        }

    }

}
