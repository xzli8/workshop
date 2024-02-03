package load_balance;

import java.util.HashMap;
import java.util.Map;

public class FullRound {

    private int serverNum;

    private volatile int index;

    public FullRound(int serverNum) {
        this.index = 0;
        this.serverNum = serverNum;
    }

    public void reset(int serverNum) {
        this.index = 0;
        this.serverNum = serverNum;
    }

    public int route() {
        if (index == serverNum) index = 0;
        return index++;
    }



    public static void main(String[] args) {
        FullRound fullRound = new FullRound(3);
        Map<Integer, Integer> server2Num = new HashMap<>();
        for (int i = 0; i < 600; i++) {
            int server = fullRound.route();
            server2Num.put(server, server2Num.getOrDefault(server, 0) + 1);
        }
        System.out.println(server2Num);
    }

}
