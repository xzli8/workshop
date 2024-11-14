package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _535Codec {

    public static class Solution1 {


        /**
         Auto-increasing id
         TC: O(1)
         SC: O(N)
         */
        public class Codec {

            // mock database
            private Map<Integer, String> map = new HashMap<>();

            // auto increasing id, furthermore we could encrypt id by hash algorithm for safety.
            private int id;

            // Encodes a URL to a shortened URL.
            public String encode(String longUrl) {
                id++;
                map.put(id, longUrl);
                return "http://tinyurl.com/" + id;
            }

            // Decodes a shortened URL to its original URL.
            public String decode(String shortUrl) {
                int p = shortUrl.lastIndexOf("/") + 1;
                int key = Integer.parseInt(shortUrl.substring(p));
                return map.get(key);
            }

        }

        // Your Codec object will be instantiated and called as such:
        // Codec codec = new Codec();
        // codec.decode(codec.encode(url))

    }

}
