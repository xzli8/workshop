package interview.tesla_oa_history;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class PairShoes {

    /**
     * Given a string s consisting of 'L' and 'R' representing the left shoes and right shoes,
     * Now you need to assign workers to pack the shoes, each worker should get a distinct interval of adjacent shoes,
     * such that the number of left shoes is equal to the number of right shoes. Each shoe must be assigned to exactly one worker.
     *
     * N is an integer within the range[2, 100000]
     * string s is made only of the character 'R' and 'L'
     * the number of letters 'L' and 'R' in string s is the same
     */

    public int getMaximumWorkers(String s) {
        // stack
        int res = 0;
        Deque<Character> stk = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (stk.isEmpty()) {
                stk.push(c);
            } else {
                if (stk.peek() == (c == 'L' ? 'R' : 'L' )) {
                    stk.pop();
                } else {
                    stk.push(c);
                }
            }
            if (stk.isEmpty()) res++;
        }
        return res;

        // greedy
//        int count = 0, balance = 0;
//        for (char c : s.toCharArray()) {
//            // L +1，R -1
//            balance += (c == 'L') ? 1 : -1;
//
//            // 平衡为0 → 可以分成一段！贪心切分
//            if (balance == 0) {
//                count++;
//            }
//        }
//        return count;
    }

    @Test
    public void test() {
        System.out.println(getMaximumWorkers("RLRRLLRLRRLLL"));    // 4
        System.out.println(getMaximumWorkers("RLLLRRRLLR"));   // 4
        System.out.println(getMaximumWorkers("LLRLRLRLRLRLRLRR")); // 1
        System.out.println(getMaximumWorkers("RRLRLL"));    // 1
    }

}
