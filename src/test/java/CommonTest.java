import org.junit.Test;

public class CommonTest {

    @Test
    public void test() {
        for (int i = 1; i < 25; i++) {
            System.out.println(factorial(i));
        }
    }

    private long factorial(int n) {
        long res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

}
