package load_balance;

import java.util.HashMap;
import java.util.Map;

public class WeightedRound {

    private volatile int index;

    private int prev;

    private int[] weights, preSums;

    public WeightedRound(int[] weights) {
        this.index = 0;
        this.prev = 1;
        this.weights = weights;
        initPreSums();
    }

    private void initPreSums() {
        int n = weights.length;
        this.preSums = new int[n + 1];
        for (int i = 1; i <= n; i++) preSums[i] = preSums[i - 1] + weights[i - 1];
    }

    public int route() {
        int n = weights.length;
        if (++index == preSums[n]) {
            index = 0;
            prev = 1;
        }
        if (index >= preSums[prev]) prev++;
        return prev - 1;
    }



    public static void main(String[] args) {
        WeightedRound weightedRound = new WeightedRound(new int[] {1, 2, 3});
        Map<Integer, Integer> server2Num = new HashMap<>();
        for (int i = 0; i < 600; i++) {
            int server = weightedRound.route();
            server2Num.put(server, server2Num.getOrDefault(server, 0) + 1);
        }
        System.out.println(server2Num);
    }

}
