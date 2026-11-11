package data_structure_algorithm.leetcode;

import java.util.*;

public class _210findOrder {

    public static class Solution1 {

        /**
         BFS拓扑排序: O(N), O(N)
         Note: 邻接表g用List<List<Integer>>的形式
         */
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // 构建邻接表和入度
            int[] indeg = new int[numCourses];
            List<List<Integer>> g = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) g.add(new ArrayList<>());
            for (int i = 0; i < prerequisites.length; i++) {
                g.get(prerequisites[i][1]).add(prerequisites[i][0]);
                indeg[prerequisites[i][0]]++;
            }

            // 入度为0的点全部加入队列
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (indeg[i] == 0) {
                    q.offer(i);
                }
            }

            // 将入度为0的点加入结果集，并删除该点连接的边
            int[] res = new int[numCourses];
            int visited = 0;
            while (!q.isEmpty()) {
                int u = q.poll();
                res[visited++] = u;
                for (int v : g.get(u)) {
                    indeg[v]--;
                    if (indeg[v] == 0) {
                        q.offer(v);
                    }
                }
            }
            return visited == numCourses ? res : new int[0];
        }

    }



    public static class Solution2 {

        /**
         BFS拓扑排序: O(N), O(N)
         Note: 邻接表g用List<Integer>[]的形式
         */
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // 构建邻接表和入度
            int[] indeg = new int[numCourses];
            List<Integer>[] g = new List[numCourses];
            Arrays.setAll(g, e -> new ArrayList<>());   // for (int i = 0; i < numCourses; i++) g[i] = new ArrayList<>();    // Arrays.fill(g, new ArrayList<>();  // (这样填充的是同一个ArrayList对象，有问题)
            for (int i = 0; i < prerequisites.length; i++) {
                g[prerequisites[i][1]].add(prerequisites[i][0]);
                indeg[prerequisites[i][0]]++;
            }

            // 入度为0的点全部加入队列
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (indeg[i] == 0) {
                    q.offer(i);
                }
            }

            // 将入度为0的点加入结果集，并删除该点连接的边
            int[] res = new int[numCourses];
            int visited = 0;
            while (!q.isEmpty()) {
                int u = q.poll();
                res[visited++] = u;
                for (int v : g[u]) {
                    indeg[v]--;
                    if (indeg[v] == 0) {
                        q.offer(v);
                    }
                }
            }
            return visited == numCourses ? res : new int[0];
        }

    }


    /**
     追问1：有环怎么处理
     - 判断有环
         - BFS(Kahn)：出队数量 < 节点数就有环；剩下入度 > 0 的节点就是环上或依赖环的节点
         - DFS 三色：遍历中遇到"正在访问"(灰色)的节点，就是回边，说明有环
     - 工程上怎么处理（比如任务调度、DAG 编排引擎）
         - 加载配置时就做环检测，有环直接拒绝，并报出环上的节点（方便定位哪条依赖配错了）
         - 不要在运行期才发现：运行期有环会导致任务永远等待
     */
    public static class Solution3 {

        /**
         DFS 三色标记: O(V + E), O(V + E)
         - 0 白(未访问)、1 灰(在当前递归栈上)、2 黑(子树已全部完成)
         - 后序（节点所有后继都完成后）把节点压栈，最后反转即拓扑序
         - 遇到灰色节点 = 有环；沿 parent 回溯可以还原出环上的节点
         */
        private List<Integer>[] g;
        private int[] color, parent;
        private Deque<Integer> stack;
        private List<Integer> cycle;

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            g = new List[numCourses];
            Arrays.setAll(g, e -> new ArrayList<>());
            for (int[] p : prerequisites) g[p[1]].add(p[0]);

            color = new int[numCourses];
            parent = new int[numCourses];
            Arrays.fill(parent, -1);
            stack = new ArrayDeque<>();
            cycle = null;

            for (int i = 0; i < numCourses && cycle == null; i++) {
                if (color[i] == 0) dfs(i);
            }
            if (cycle != null) return new int[0];

            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++) res[i] = stack.pop();   // 栈顶是最后完成的，即拓扑序第一个
            return res;
        }

        /** 有环时返回环上的节点（按依赖方向），无环返回空列表 */
        public List<Integer> findCycle(int numCourses, int[][] prerequisites) {
            findOrder(numCourses, prerequisites);
            return cycle == null ? Collections.emptyList() : cycle;
        }

        private void dfs(int u) {
            color[u] = 1;
            for (int v : g[u]) {
                if (cycle != null) return;
                if (color[v] == 0) {
                    parent[v] = u;
                    dfs(v);
                } else if (color[v] == 1) {
                    // 回边 u -> v：从 u 沿 parent 回溯到 v，就是环
                    cycle = new ArrayList<>();
                    for (int x = u; x != v; x = parent[x]) cycle.add(x);
                    cycle.add(v);
                    Collections.reverse(cycle);
                    return;
                }
            }
            color[u] = 2;
            stack.push(u);
        }

    }


    /**
     追问2：输出所有可能的拓扑序
     - 回溯：每一步从当前所有入度为 0 的节点中任选一个，选完把它的后继入度减 1，递归后再恢复
     - 复杂度：最坏 O(V! * (V + E))（比如没有任何边时，所有排列都合法），只适合小规模
     */
    public static class Solution4 {

        private List<Integer>[] g;
        private int[] indeg;
        private boolean[] used;
        private List<List<Integer>> res;

        public List<List<Integer>> allOrders(int numCourses, int[][] prerequisites) {
            g = new List[numCourses];
            Arrays.setAll(g, e -> new ArrayList<>());
            indeg = new int[numCourses];
            for (int[] p : prerequisites) {
                g[p[1]].add(p[0]);
                indeg[p[0]]++;
            }
            used = new boolean[numCourses];
            res = new ArrayList<>();
            backtrack(new ArrayList<>(), numCourses);
            return res;   // 有环时为空
        }

        private void backtrack(List<Integer> path, int n) {
            if (path.size() == n) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int u = 0; u < n; u++) {
                if (used[u] || indeg[u] != 0) continue;
                used[u] = true;
                path.add(u);
                for (int v : g[u]) indeg[v]--;

                backtrack(path, n);

                for (int v : g[u]) indeg[v]++;
                path.remove(path.size() - 1);
                used[u] = false;
            }
        }

    }


    public static void main(String[] args) {
        int[][] dag = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(new Solution1().findOrder(4, dag)));        // [0, 1, 2, 3]
        System.out.println(Arrays.toString(new Solution3().findOrder(4, dag)));        // 合法拓扑序之一
        System.out.println(new Solution4().allOrders(4, dag));                         // [[0, 1, 2, 3], [0, 2, 1, 3]]

        int[][] cyclic = {{1, 0}, {2, 1}, {0, 2}, {3, 2}};
        System.out.println(Arrays.toString(new Solution1().findOrder(4, cyclic)));     // []
        System.out.println(new Solution3().findCycle(4, cyclic));                      // [0, 1, 2]
        System.out.println(new Solution4().allOrders(4, cyclic));                      // []
    }

}
