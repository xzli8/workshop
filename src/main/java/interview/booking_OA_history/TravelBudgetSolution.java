package interview.booking_OA_history;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TravelBudgetSolution {

    /**
     * 题目描述：有一位旅客计划访问多个城市，对于每个城市他必须连续停留固定天数(每个城市的停留天数相同)，每天的酒店价格不同。
     * 现在旅客有个总预算，希望找出缩合合法的旅行总花费，条件如下：
     * 1.总花费不能超过预算
     * 2.每个城市必须连续停留指定天数
     * 3.必须只访问每个城市一次，且是连续出行(不能返回或者跳过城市)
     *
     * 输入：
     * numberOfDaysPerCity：整数 - 每个城市需要连续停留的天数
     * guestBudget: 整数 - 旅客的最大预算
     * dailyPricePerCity: 字典 {城市名: [价格列表]} - 每个城市每天酒店价格数组
     *
     * 输出：
     * 所有满足上述条件的旅行总花费列表(整数)，按升序排列。
     *
     * 示例：
     * input:
     * numberOfDaysPerCity = 2
     * guestBudget = 309
     * dailyPricePerCity = {
     *     "Paris": [10, 40, 5, 80, 10, 50],
     *     "London": [60, 30, 20, 70, 50, 70],
     *     "Amsterdam": [20, 80, 20, 50, 80, 100]
     * }
     *
     * output:
     * [220, 240, 250, 305]
     */

    public static class Solution1 {

        @Test
        public void test() {
            int numberOfDaysPerCity = 2;
            int guestBudget = 309;
            Map<String, int[]> dailyPricePerCity = new HashMap<>();
            dailyPricePerCity.put("Paris", new int[] {10, 40, 5, 80, 10, 50});
            dailyPricePerCity.put("London", new int[] {60, 30, 20, 70, 50, 70});
            dailyPricePerCity.put("Amsterdam", new int[] {20, 80, 20, 50, 80, 100});

            List<Integer> result = getValidTravelCosts(numberOfDaysPerCity, guestBudget, dailyPricePerCity);
            System.out.println(result);
        }

        public List<Integer> getValidTravelCosts(
                int numberOfDaysPerCity,
                int guestBudget,
                Map<String, int[]> dailyPricePerCity
        ) {
            budget = guestBudget;
            this.numberOfDaysPerCity = numberOfDaysPerCity;
            totalDay = dailyPricePerCity.size() * numberOfDaysPerCity;
            String[] cities = dailyPricePerCity.keySet().toArray(new String[0]);

            // 前缀和
            for (String city : dailyPricePerCity.keySet()) {
                int[] prices = dailyPricePerCity.get(city);
                int n = prices.length;
                int[] preSumPrices = new int[n + 1];
                for (int i = 1; i <= n; i++) {
                    preSumPrices[i] = preSumPrices[i - 1] + prices[i - 1];
                }
                preSumPricesPerCity.put(city, preSumPrices);
            }

            // 回溯全排列 + 剪枝
            dfs(cities, new boolean[cities.length], 0, 0);

            return validCosts.stream().sorted().collect(Collectors.toList());
        }

        private int budget, numberOfDaysPerCity, totalDay;
        private Map<String, int[]> preSumPricesPerCity = new HashMap<>();
        private  Set<Integer> validCosts = new HashSet<>();

        // 求所有城市的全排列，同时计算budget
        private  void dfs(
                String[] cities,
                boolean[] visited,
                int currentDay,
                int currentBudget
        ) {
            if (currentBudget > budget) return; // 剪枝

            if (currentDay == totalDay) {
                validCosts.add(currentBudget);
                return;
            }

            for (int i = 0; i < cities.length; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                int[] preSumPrices = preSumPricesPerCity.get(cities[i]);
                int cost = preSumPrices[currentDay + numberOfDaysPerCity] - preSumPrices[currentDay];
                dfs(cities, visited, currentDay + numberOfDaysPerCity, currentBudget + cost);
                visited[i] = false;
            }
        }

    }


}
