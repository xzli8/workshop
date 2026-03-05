package interview.tesla_oa_history;

import org.junit.Test;

import java.util.Arrays;

public class Encrypt {

    /**
     * Given a string s, returns the string encrypted using Caesar cipher with a rotation of 4.
     */

    public String encrypt(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            cs[i] = (char) ((cs[i] - 'A' + 4) % 26 + 'A');
        }
        return Arrays.toString(cs);
    }

    @Test
    public void test() {
        System.out.println(encrypt("PINEAPPLE"));   // TMRIETTPI
        System.out.println(encrypt("ZEBRA"));   // DIFVE
        System.out.println(encrypt("NETWORK"));  // RIXASVO
    }

}
