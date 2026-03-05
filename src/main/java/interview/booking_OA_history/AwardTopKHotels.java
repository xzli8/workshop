package interview.booking_OA_history;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class AwardTopKHotels {

    /**
     * 题目描述：根据评论给酒店打分，positive: +3, negative: -1，返回得分最高的前K个酒店。
     */

    public static class Solution1 {

        @Test
        public void test() {
            String positiveKeywords = "breakfast beach citycenter location metro view staff price", negativeKeywords = "not";
            int numberOfHotels = 5, numberOfReviews = 5, k = 2;
            int[] hotelIds = new int[] {1, 2, 1, 1, 2};
            String[] reviews = new String[] {
                    "This hotel has a nice view of the citycenter. The location is perfect.",
                    "The breakfast is ok. Regarding location, it is quite far from citycenter but price is cheap so it is worth.",
                    "Location is excellent, 5 minutes from citycenter. There is also a metro station very close to the hotel.",
                    "They said I couldn't take my dog and there were other guests with dogs! That is not fair.",
                    "Very friendly staff and good cost-benefit ratio. Its location is a bit far from citycenter."
            };

            // should be "[2, 1]"
            int[] res = awardTopKHotels(positiveKeywords, negativeKeywords, numberOfHotels, hotelIds, numberOfReviews, reviews, k);
            System.out.println(Arrays.toString(Arrays.stream(res).toArray()));

        }

        public int[] awardTopKHotels(String positiveKeywords, String negativeKeywords, int numberOfHotels,
                                     int[] hotelIds, int numberOfReviews, String[] reviews, int k) {
            // 将positiveKeywords和negativeKeywords转换成HashSet
            Set<String> pos = Arrays.stream(positiveKeywords.split("\\s+")).collect(Collectors.toSet()),
                    neg = Arrays.stream(negativeKeywords.split("\\s+")).collect(Collectors.toSet());

            // 遍历review，计算分数
            Map<Integer, Integer> hotelId2Score = new HashMap<>();
            for (int i = 0; i < numberOfReviews; i++) {
                String review = reviews[i].replaceAll("[^a-zA-Z]", " ").toLowerCase();
                int score = hotelId2Score.getOrDefault(hotelIds[i], 0);
                for (String word : review.split("\\s+")) {
                    if (pos.contains(word)) score += 3;
                    if (neg.contains(word)) score -= 1;
                }
                hotelId2Score.put(hotelIds[i], score);
            }

            // 将map转换为list，并按照优先分数，分数相等时id降序排序
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(hotelId2Score.entrySet());
            list.sort((a, b) -> {
                if (a.getValue().equals(b.getValue())) {
                    return a.getKey() - b.getKey();
                }
                return b.getValue() - a.getValue();
            });

            // 计算topK返回
            int[] res = new int[k];
            for (int i = 0; i < Math.min(k, list.size()); i++) {
                res[i] = list.get(i).getKey();
            }
            return res;
        }

    }

}
