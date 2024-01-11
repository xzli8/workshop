package load_balance;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FullRandom {

    private final Random random = new Random();

    private int serverNum;


    public FullRandom(int serverNum) {
        this.serverNum = serverNum;
    }

    public void reset(int n) {
        this.serverNum = n;
    }

    public int route() {
        return random.nextInt(serverNum);
    }

    public static void main(String[] args) {
        FullRandom fullRandom = new FullRandom(3);
        Map<Integer, Integer> server2Num = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int server = fullRandom.route();
            server2Num.put(server, server2Num.getOrDefault(server, 0) + 1);
        }
        System.out.println(server2Num);
    }

}
