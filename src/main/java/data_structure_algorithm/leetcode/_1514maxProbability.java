package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _1514maxProbability {

    public static class Solution1 {

        /**
         dijkstra：有向正权图的最短路径
         但这里给的是无向图，无向图等价于双向图
         这里求最大概率，因为每增加一条边，概率都要减小，所以可以(标准dijkstra是每增加一条边路径长度增加求最小)
         */
        public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
            // 构建图(邻接表)
            List<double[]>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < edges.length; i++) {
                int from = edges[i][0], to = edges[i][1];
                double prob = succProb[i];
                graph[from].add(new double[] {(double) to, prob});
                graph[to].add(new double[] {(double) from, prob});
            }

            // 初始化概率数组
            double[] probs = new double[n];
            Arrays.fill(probs, 0);
            probs[start_node] = 1;

            // 初始化优先队列，概率大的排在前面
            PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
            pq.offer(new State(start_node, 1));

            // 开始遍历
            while (!pq.isEmpty()) {
                // 处理当前节点
                State cur = pq.poll();

                // 已有概率更大的路径到达当前节点
                if (cur.prob < probs[cur.id]) {
                    continue;
                }

                // 访问相邻节点
                for (double[] adj : graph[cur.id]) {
                    int id = (int) adj[0];
                    double prob = cur.prob * adj[1];
                    if (prob > probs[id]) {
                        probs[id] = prob;
                        pq.offer(new State(id, prob));
                    }
                }
            }
            return probs[end_node];
        }

        class State {
            // 节点id
            int id;

            // 从起点到该点的概率
            double prob;

            public State (int id, double prob) {
                this.id = id;
                this.prob = prob;
            }
        }

    }

}
