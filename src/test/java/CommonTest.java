import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonTest {

    @Test
    public void test() {
        int MOD = (int) 1e9 + 7;
        System.out.println(MOD);

        List<String> path = new ArrayList<>();
        path.add(1 + "");
        path.add(2 + "");
        path.add(3 + "");
        String res = String.join("->", path);
        System.out.println(res);
    }

}
