package interview.tesla_oa_history;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SameSquareEdge {

    /**
     * 输入两个数组x, y，都包含N个integer，x[i], y[i]表示二维平面上某个点的坐标，
     * 要求实现一个solution来判断输入的x, y是否都在同一个正方形的边上（正方形的边必须平行于x和y坐标轴)
     */
    public boolean solution(int[] x, int[] y) {
        int n = x.length;
        // 点数量必须 >=2 才能构成正方形的边
        if (n < 2) return false;

        // 第一步：求边界
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            minX = Math.min(minX, x[i]);
            maxX = Math.max(maxX, x[i]);
            minY = Math.min(minY, y[i]);
            maxY = Math.max(maxY, y[i]);
        }

//        // 第二步：必须是正方形（边长相等且>0）
//        int side = maxX - minX;
//        if (side <= 0 || side != maxY - minY) {
//            return false;
//        }

        // 第三步：所有点必须在正方形四条边上
        for (int i = 0; i < n; i++) {
            int px = x[i];
            int py = y[i];
            boolean onEdge = (px == minX || px == maxX || py == minY || py == maxY);
            if (!onEdge) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println(solution(new int[] {-1, 1, -2}, new int[] {2, 1, 0}));   // true
        System.out.println(solution(new int[] {-10, -10, -9}, new int[] {-10, 10, 5}));   // true
        System.out.println(solution(new int[] {-1, 1, 1, -1, 0}, new int[] {1, 1, -1, -1, 0}));   // false
        System.out.println(solution(new int[] {-1, 1, -2, 2, -1, 2}, new int[] {1, -1, -1, 1, 1, -1}));   // false
    }

}
