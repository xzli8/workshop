package load_balance;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeightedRandom {

    private final Random random = new Random();

    private int[] weights, preSums;

    public WeightedRandom(int[] weights) {
        this.weights = weights;
        initPreSums();
    }

    public void reset(int[] weights) {
        this.weights = weights;
        initPreSums();
    }

    private void initPreSums() {
        int n = weights.length;
        this.preSums = new int[n + 1];
        preSums[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSums[i] = preSums[i - 1] + weights[i - 1];
        }
    }

    public int route() {
        int n = weights.length;
        int target = random.nextInt(preSums[n]);
        int left = 0, right = n;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (preSums[mid] <= target) {
                if (mid == n || preSums[mid + 1] > target) return mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        WeightedRandom weightedRandom = new WeightedRandom(new int[] {1, 2, 3});
        Map<Integer, Integer> server2Num = new HashMap<>();
        for (int i = 0; i < 600; i++) {
            int server = weightedRandom.route();
            server2Num.put(server, server2Num.getOrDefault(server, 0) + 1);
        }
        System.out.println(server2Num);
    }

}
