package data_structure_algorithm.leetcode;

import java.util.Scanner;

public class Buchongti_22ip2Num {

    /**
     * 题目链接：https://www.nowcoder.com/questionTerminal/66ca0e28f90c42a196afd78cc9c496ea
     * ref：https://mp.weixin.qq.com/s/UWCuEtNS2kuAuDY-eIbghg
     */

    // 注意类名必须为 Main, 不要有任何 package xxx 信息
    public static class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String ip = sc.nextLine(); // 转十进制
            long num = sc.nextLong(); // 转IP地址
            System.out.println(ip2Num(ip));
            System.out.println(num2Ip(num));
        }

        private static long ip2Num(String ip) {
            String[] ipList = ip.split("\\.");
            long seg0 = Long.valueOf(ipList[0]) << 24;
            long seg1 = Long.valueOf(ipList[1]) << 16;
            long seg2 = Long.valueOf(ipList[2]) << 8;
            long seg3 = Long.valueOf(ipList[3]);
            return seg0 | seg1 | seg2 | seg3;
        }

        private static String num2Ip(long num) {
            StringBuilder res = new StringBuilder();
            res.append((num >>> 24) & 255).append(".");
            res.append((num >>> 16) & 255).append(".");
            res.append((num >>> 8) & 255).append(".");
            res.append(num & 255);
            return res.toString();
        }

    }

}
