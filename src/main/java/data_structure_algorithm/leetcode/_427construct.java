package data_structure_algorithm.leetcode;

import org.junit.Test;

public class _427construct {

    public static class Solution1 {

        // Definition for a QuadTree node.
        class Node {
            public boolean val;
            public boolean isLeaf;
            public Node topLeft;
            public Node topRight;
            public Node bottomLeft;
            public Node bottomRight;


            public Node() {
                this.val = false;
                this.isLeaf = false;
                this.topLeft = null;
                this.topRight = null;
                this.bottomLeft = null;
                this.bottomRight = null;
            }

            public Node(boolean val, boolean isLeaf) {
                this.val = val;
                this.isLeaf = isLeaf;
                this.topLeft = null;
                this.topRight = null;
                this.bottomLeft = null;
                this.bottomRight = null;
            }

            public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
                this.val = val;
                this.isLeaf = isLeaf;
                this.topLeft = topLeft;
                this.topRight = topRight;
                this.bottomLeft = bottomLeft;
                this.bottomRight = bottomRight;
            }
        };



        /**
         DFS
         时间复杂度：O(N^2)
         空间复杂度：O(N^2)
         */
        public Node construct(int[][] grid) {
            this.n = grid.length;
            this.grid = grid;
            return build(0, 0, n);
        }

        private int n;
        private int[][] grid;
        private Node build(int x, int y, int d) {
            if (isLeaf(x, y, d)) return new Node(grid[x][y] == 1, true);
            Node node = new Node(grid[x][y] == 1, false);
            node.topLeft = build(x, y, d / 2);
            node.topRight = build(x, y + d / 2, d / 2);
            node.bottomLeft = build(x + d / 2, y, d / 2);
            node.bottomRight = build(x + d / 2, y + d / 2, d / 2);
            return node;
        }

        private boolean isLeaf(int x, int y, int d) {
            int val = grid[x][y];
            for (int i = x; i < x + d; i++) {
                for (int j = y; j < y + d; j++) {
                    if (grid[i][j] != val) return false;
                }
            }
            return true;
        }

    }

}
