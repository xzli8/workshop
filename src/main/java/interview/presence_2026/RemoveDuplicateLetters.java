package interview.presence_2026;

public class RemoveDuplicateLetters {

    public static String removeDuplicates(String s) {
        // 用 Set 记录已经出现过的字符
        boolean[] seen = new boolean[128]; // ASCII 足够
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (!seen[c]) {
                seen[c] = true;
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "1CAA1";
        System.out.println(removeDuplicates(str)); // 输出：1CA
    }

}
