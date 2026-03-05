package interview.binance_2026;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class DeleteBetweenBrace {

    /**
     * input: a string
     * output: a string that delete all the content between brace "()"
     */

    @Test
    public void testCase() {
        Assert.assertEquals("", delete(null));
        Assert.assertEquals("", delete(""));
        Assert.assertEquals("(", delete("("));
        Assert.assertEquals("", delete("(())"));
        Assert.assertEquals(")", delete("())"));
        Assert.assertEquals("(((", delete("(())((("));
        Assert.assertEquals("", delete("((123))(rt)"));
        Assert.assertEquals(")", delete("(123))(rt)"));
        Assert.assertEquals("123))", delete("123))(rt)"));
        Assert.assertEquals("123", delete("123"));
        Assert.assertEquals("123", delete("123()"));
    }

    public String delete(String s) {
        if (s == null) return "";
        Deque<Character> stk = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            stk.push(c);
            if (c == ')') {
                StringBuilder sb = new StringBuilder();
                while (!stk.isEmpty() && stk.peek() != '(') {
                    sb.append(stk.pop());
                }
                if (stk.isEmpty()) {
                    for (char cs : sb.reverse().toString().toCharArray()) {
                        stk.push(cs);
                    }
                } else {
                    stk.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        return sb.reverse().toString();
    }

}
