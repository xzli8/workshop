import org.junit.Test;

import java.util.Arrays;

public class CommonTest {

    @Test
    public void test() {
        System.out.println( Arrays.stream(new int[] {1, 2}).sum());
    }

}
