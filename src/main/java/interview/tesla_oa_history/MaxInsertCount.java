package interview.tesla_oa_history;

import org.junit.Test;

public class MaxInsertCount {

    /**
     * Given a string s consists of N lowercase letters, returns the maximum number of letter 'a' that can be inserted
     * into s (including at the front and end of s) so that the resulting string doesn't contain three consecutive 'a'.
     * If s already contains the substring 'aaa', return -1.
     */

    // 双指针
    public int getMaxInsertCount(String s) {
        int res = 0, n = s.length(), l = -1, r = 0;
        while (r < n) {
            while (r < n && s.charAt(r) == 'a') {
                r++;
            }
            int gap = r - l;
            if (gap > 3) return -1;
            res += (3 - gap);
            l = r;
            r++;
        }
        if (n > 0 && s.charAt(n - 1) != 'a') res += 2;
        return res;
    }

    @Test
    public void test() {
        System.out.println(getMaxInsertCount("b")); // 4
        System.out.println(getMaxInsertCount("aabab")); // 3
        System.out.println(getMaxInsertCount("dog"));   // 8
        System.out.println(getMaxInsertCount("aa"));    // 0
        System.out.println(getMaxInsertCount("baaaa")); // -1
    }

}
