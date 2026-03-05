package interview.traveloka_2026_oa;

public class CalcSteps {

    /**
     * 题目二：01 字符串逐轮交换「01→10」求轮数
     * 一、题干
     * 仅由 0、1 组成的二进制字符串；规则：
     * 每一轮同时把所有不重叠的 "01" 替换为 "10"；
     * 一轮替换完成算 1 步；
     * 直到字符串无 "01" 为止；
     * 求总共需要多少轮。
     * 二、示例
     * 输入：001011手动流程：原始：001011step1：010101step2：101010step3：110100step4：111000无 01，停止 → 答案：4
     * 三、解法 1：模拟逐轮替换（直观，超长字符串超时）
     * 思路
     * 循环：每轮先标记所有「01」下标（防止边改边乱）；
     * 一轮结束统一交换所有标记位置；
     * 有交换则步数 + 1，无交换则退出。
     * 配合示例讲解（001011）
     * 第 1 轮：找出所有 01，一次性全换 → 变成 010101，步数 = 1
     * 第 2 轮：再找所有 01，全换 → 101010，步数 = 2
     * 第 3 轮：同上 → 110100，步数 = 3
     * 第 4 轮：同上 → 111000，步数 = 4
     * 无 01，结束。
     * 特点
     * 优点：完全贴合手动推演，好理解；
     * 缺点：字符串很长时，循环轮次多，时间复杂高，容易超时。
     */

    public static class Solution1 {

        public static int calcSteps(String s) {
            char[] curr = s.toCharArray();
            int steps = 0;

            while (true) {
                boolean hasZeroOne = false;
                // 用来记录这一轮要交换的位置，不能边遍历边改，会错乱
                boolean[] toSwap = new boolean[curr.length];

                // 第一步：先扫描一遍，标记所有 "01" 的位置（0的下标）
                for (int i = 0; i < curr.length - 1; i++) {
                    if (curr[i] == '0' && curr[i + 1] == '1') {
                        toSwap[i] = true;
                        hasZeroOne = true;
                    }
                }

                // 没有 01 了，结束
                if (!hasZeroOne) break;

                // 第二步：统一交换所有标记的位置
                for (int i = 0; i < curr.length - 1; i++) {
                    if (toSwap[i]) {
                        char temp = curr[i];
                        curr[i] = curr[i + 1];
                        curr[i + 1] = temp;
                    }
                }

                steps++; // 一轮完成，步数+1
            }
            return steps;
        }

        public static void main(String[] args) {
            System.out.println(calcSteps("001011"));
            System.out.println(calcSteps("000"));
            System.out.println(calcSteps("01"));
            System.out.println(calcSteps("001"));
        }

    }


    // error
    public static class Solution2 {

        public static int countSteps(String s) {
            int res = 0;    // 最终答案
            int cnt1 = 0;   // 左侧 1 的数量
            int prev = 0;   // 前一个 0 的耗时

            for (char c : s.toCharArray()) {
                if (c == '1') {
                    cnt1++;
                } else {
                    // 当前 0 至少需要的轮次
                    int curr = cnt1;
                    // 连续 0 必须排队，后一个比前一个晚 1 轮
                    if (curr <= prev) {
                        curr = prev + 1;
                    }
                    res = Math.max(res, curr);
                    prev = curr;
                }
            }
            return res;
        }

        public static void main(String[] args) {
            // 测试所有经典案例
            System.out.println(countSteps("001011"));  // ✅ 4
            System.out.println(countSteps("000"));     // ✅ 0
            System.out.println(countSteps("01"));      // ✅ 1
            System.out.println(countSteps("001"));     // ✅ 2
            System.out.println(countSteps("0101"));    // ✅ 2
            System.out.println(countSteps("000111"));  // ✅ 3
        }

    }

}
