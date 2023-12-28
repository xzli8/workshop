package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _36isValidSudoku {

    public static class Solution1 {

        /**
         哈希表
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean isValidSudoku(char[][] board) {
            // 验证每一行是否有效
            for (int row = 0; row < 9; row++) {
                Set<Character> nums = new HashSet<>();
                for (int col = 0; col < 9; col++) {
                    if (board[row][col] != '.') {
                        if (nums.contains(board[row][col])) return false;
                        nums.add(board[row][col]);
                    }
                }
            }

            // 验证每一列是否有效
            for (int col = 0; col < 9; col++) {
                Set<Character> nums = new HashSet<>();
                for (int row = 0; row < 9; row++) {
                    if (board[row][col] != '.') {
                        if (nums.contains(board[row][col])) return false;
                        nums.add(board[row][col]);
                    }
                }
            }

            // 验证九宫格内是否有效
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Set<Character> nums = new HashSet<>();
                    for (int row = i * 3; row < (i + 1) * 3; row++) {
                        for (int col = j * 3; col < (j + 1) * 3; col++) {
                            if (board[row][col] != '.') {
                                if (nums.contains(board[row][col])) return false;
                                nums.add(board[row][col]);
                            }
                        }
                    }
                }
            }
            return true;
        }

    }

}
