package dsa.supplyment;

import java.util.HashMap;
import java.util.Map;

public class ArabicChineseConvert {

    /**
     * Notes:
     *  1. units and digits
     *  2. continuous zeros
     *  3. first is "一" and second is "十"
     *
     * https://www.nowcoder.com/practice/6eec992558164276a51d86d71678b300
     *
     * @param arabic
     * @return
     */
    private String arabicToChinese(String arabic) {
        String[] digits = new String[] {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] units = new String[] {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};

        StringBuilder chinese = new StringBuilder();
        for (int i = 0; i < arabic.length(); i++) {
            int digitIndex = arabic.charAt(i) - '0';
            int unitIndex = arabic.length() - 1 - i;

            // 跳过除个位以外的零
            if (i > 0 && digitIndex == 0) {
                // 当有“万”、“亿”时，加上单位
                if ("亿".equals(units[unitIndex]) || ("万".equals(units[unitIndex]) && chinese.charAt(chinese.length() - 1) != '亿') ) {
                    chinese.append(units[unitIndex]);
                }
            } else {
                // 连续零合并成一个(上面的if把连续零都跳过了，这里要补上一个零)
                if (i > 0 && (arabic.charAt(i-1) - '0') == 0) {
                    chinese.append("零");
                }
                // 核心逻辑
                chinese.append(digits[digitIndex]).append(units[unitIndex]);
            }
        }

        // 十开头的去掉前面的一
        if (chinese.length() > 1 && chinese.charAt(0) == '一' && chinese.charAt(1) == '十') {
            return chinese.substring(1);
        }
        return chinese.toString();
    }

    private String chineseToArabic(String chinese) {
        // 特殊处理以“十”开头的数字：前面加上"一"
        if (chinese.length() > 0 && chinese.charAt(0) == '十') {
            chinese = "一" + chinese;
        }
        // 特殊处理以“亿”结尾的数字：后面加上“万”
        if (chinese.length() > 0 && chinese.charAt(chinese.length() - 1) == '亿') {
            chinese += "万";
        }

        // 以“亿”、“万”为分割线，将数字分为三段
        int yiIndex = -1, wanIndex = -1;
        for (int i = 0; i < chinese.length(); i++) {
            if (chinese.charAt(i) == '亿') {
                yiIndex = i;
            }
            if (chinese.charAt(i) == '万') {
                wanIndex = i;
            }
        }
        String yiNum = yiIndex == -1 ? null : chinese.substring(0, yiIndex);
        String wanNum = wanIndex == -1 ? null : yiIndex == -1 ? chinese.substring(0, wanIndex) : chinese.substring(yiIndex + 1, wanIndex);
        String num = chinese.substring(wanIndex + 1);

        // 分别计算每一段的值，然后乘以因子，最后求和
        return String.valueOf(chineseToArabicSection(yiNum) * 100000000L + chineseToArabicSection(wanNum) * 10000L + chineseToArabicSection(num));
    }

    private int chineseToArabicSection(String chinese) {
        if (chinese == null || chinese.length() == 0) {
            return 0;
        }

        // 数字
        Map<Character, Integer> numMap = new HashMap<>();
        numMap.put('零', 0);
        numMap.put('一', 1);
        numMap.put('二', 2);
        numMap.put('三', 3);
        numMap.put('四', 4);
        numMap.put('五', 5);
        numMap.put('六', 6);
        numMap.put('七', 7);
        numMap.put('八', 8);
        numMap.put('九', 9);

        // 单位
        Map<Character, Long> unitMap = new HashMap<>();
        unitMap.put('十', 10L);
        unitMap.put('百', 100L);
        unitMap.put('千', 1000L);

        // 遍历计算
        int index = 0, sum = 0;
        while (index < chinese.length()) {
            int num = 0;
            if (numMap.containsKey(chinese.charAt(index))) {
                num += numMap.get(chinese.charAt(index++));
            }
            if (index < chinese.length() && unitMap.containsKey(chinese.charAt(index))) {
                num *= unitMap.get(chinese.charAt(index++));
            }
            sum += num;
        }
        return sum;
    }

}
