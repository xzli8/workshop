package data_structure_algorithm.hacker_rank;

import java.util.*;

public class FindSmallestSubstringWindow {

    public static List<Integer> findSmallestSubstringWindow(List<String> patterns, String S) {
        // 1. 处理边界：空串 / 空模式 → 直接返回无解
        if (S == null || S.isEmpty() || patterns == null || patterns.isEmpty()) {
            return Arrays.asList(-1, -1);
        }

        // 2. 模式去重，避免重复计算
        Set<String> uniquePatterns = new HashSet<>(patterns);
        List<List<int[]>> allOccurrences = new ArrayList<>();

        // 3. 查找每个模式在 S 中的所有出现位置 [start, end]（闭区间）
        for (String pat : uniquePatterns) {
            List<int[]> occurrences = getPatternPositions(S, pat);
            // 任意模式没有出现 → 无解
            if (occurrences.isEmpty()) {
                return Arrays.asList(-1, -1);
            }
            allOccurrences.add(occurrences);
        }

        int patternNum = allOccurrences.size();
        int[] pointers = new int[patternNum]; // 每个模式的指针
        int minLen = Integer.MAX_VALUE;
        int left = -1, right = -1;

        // 4. 多指针贪心查找最小覆盖窗口
        while (true) {
            // 拿到当前每个模式选中的区间
            List<int[]> currentWindows = new ArrayList<>();
            for (int i = 0; i < patternNum; i++) {
                currentWindows.add(allOccurrences.get(i).get(pointers[i]));
            }

            // 计算当前窗口：最小start ~ 最大end
            int curL = Integer.MAX_VALUE;
            int curR = Integer.MIN_VALUE;
            for (int[] win : currentWindows) {
                curL = Math.min(curL, win[0]);
                curR = Math.max(curR, win[1]);
            }

            // 更新最小窗口
            int curLen = curR - curL + 1;
            if (curLen < minLen) {
                minLen = curLen;
                left = curL;
                right = curR;
            }

            // 贪心：移动【结束最早】的那个模式的指针，尝试缩小窗口。类似于最小滑动窗口中右边界固定不动，移动左边界寻找最小窗口。
            int minEndIdx = 0;
            for (int i = 1; i < patternNum; i++) {
                if (currentWindows.get(i)[1] < currentWindows.get(minEndIdx)[1]) {
                    minEndIdx = i;
                }
            }

            // 指针越界 → 退出循环
            pointers[minEndIdx]++;
            if (pointers[minEndIdx] >= allOccurrences.get(minEndIdx).size()) {
                break;
            }
        }

        // 返回结果
        return left == -1 ? Arrays.asList(-1, -1) : Arrays.asList(left, right);
    }

    /**
     * 辅助方法：查找一个模式在主串 S 中所有的 [start, end] 位置
     */
    private static List<int[]> getPatternPositions(String S, String pattern) {
        List<int[]> res = new ArrayList<>();
        int patLen = pattern.length();
        int strLen = S.length();

        for (int i = 0; i <= strLen - patLen; i++) {
            if (S.substring(i, i + patLen).equals(pattern)) {
                res.add(new int[]{i, i + patLen - 1});
            }
        }
        return res;
    }

    // 测试示例
    public static void main(String[] args) {
        List<String> patterns = Arrays.asList("abc", "zyx");
        String S = "xyzabcabczyx";

        List<Integer> result = findSmallestSubstringWindow(patterns, S);
        System.out.println(result); // 输出 [6, 11]
    }

}
