package interview.traveloka_2026_oa;

public class CountArrangement {

    /**
     * 题目一：美丽排列（1~n 排列整除问题）
     * 一、题干
     * 给定正整数 n，生成 [1,n] 所有全排列；若排列中 ** 每个位置 i（从 1 开始）** 满足以下任一条件，称为「美丽排列」：
     * nums[i] % i == 0
     * i % nums[i] == 0
     * 求满足条件的美丽排列总个数。
     * 二、示例
     * n=1：只有 [1] → 答案 1
     * n=2：[1,2]、[2,1] → 答案 2
     * n=3：[1,2,3]、[2,1,3]、[3,2,1] → 答案 3
     * 三、解法 1：暴力全排列（低效，理解用）
     * 思路
     * 递归生成所有全排列；
     * 每生成一个完整排列，逐位校验整除条件；
     * 全部符合则计数 + 1。
     * 缺点
     * 纯暴力，n 稍大就爆炸，重复生成无效排列，无剪枝。
     * 四、解法 2：回溯 + 剪枝（推荐，面试常用）
     * 核心思路
     * 按位置逐个填数字，不等到排列生成完再校验；
     * 用 used[] 标记数字是否已使用；
     * 当前位置放数字时，当场校验整除规则，不满足直接放弃这条分支（剪枝）；
     * 填满所有位置，计数 + 1。
     * 配合示例讲解（n=3）
     * 位置 1：能放 1、2、3（都满足整除）
     * 选 1 → 位置 2：只能选 2（1 已用，3 不满足 2%3/3%2）
     * 选 2 → 位置 3：选 3 ✅ 有效排列
     * 选 2 → 位置 2：只能选 1
     * 选 1 → 位置 3：选 3 ✅ 有效排列
     * 选 3 → 位置 2：只能选 2
     * 选 2 → 位置 3：选 1 ✅ 有效排列
     * 总共有 3 个，和示例一致。
     */

    public static class Solution1 {

        public int countArrangement(int n) {
            backtrack(n, 1, new boolean[n+1]);
            return count;
        }

        int count = 0;
        void backtrack(int n, int pos, boolean[] used){
            if(pos > n) {count++;return;}
            for(int num=1;num<=n;num++){
                if(!used[num] && (num%pos==0 || pos%num==0)){
                    used[num]=true;
                    backtrack(n,pos+1,used);
                    used[num]=false;
                }
            }
        }

    }

}
