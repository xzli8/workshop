package data_structure_algorithm.leetcode;

import java.util.*;

public class _355Twitter {


    /**
     * Your Twitter object will be instantiated and called as such:
     * Twitter obj = new Twitter();
     * obj.postTweet(userId,tweetId);
     * List<Integer> param_2 = obj.getNewsFeed(userId);
     * obj.follow(followerId,followeeId);
     * obj.unfollow(followerId,followeeId);
     */

    public static class Solution1 {


        /**
         系统设计：面向对象 + 多路归并(单链表)
         */
        class Twitter {

            // 推文类
            class Tweet {
                int id;
                int timestamp;
                Tweet next;

                public Tweet(int id, int timestamp) {
                    this.id = id;
                    this.timestamp = timestamp;
                    this.next = null;
                }
            }

            // 用户类
            class User {
                int id;
                Tweet head;
                Set<Integer> following; // 只需要记录关注了谁，不需要记录谁关注了我

                public User(int id) {
                    this.id = id;
                    this.head = null;
                    this.following = new HashSet<>();
                    follow(id); // 关注一下自己
                }

                public void follow(int userId) {
                    following.add(userId);
                }

                public void unfollow(int userId) {
                    if (userId == id) return;  // 不能取关自己
                    following.remove(userId);
                }

                public void post(int tweetId, int timestamp) {
                    // 往单链表的头部插入一个节点
                    Tweet tweet = new Tweet(tweetId, timestamp);
                    tweet.next = head;
                    head = tweet;
                }
            }

            // 时间戳
            private int timestamp = 0;
            // 用户映射(实际开发中，需要存在DB中)
            private Map<Integer, User> userId2User = new HashMap<>();

            public Twitter() {
                // nothing here
            }

            public void postTweet(int userId, int tweetId) {
                userId2User.putIfAbsent(userId, new User(userId));
                userId2User.get(userId).post(tweetId, timestamp++);
            }

            public void follow(int followerId, int followeeId) {
                userId2User.putIfAbsent(followerId, new User(followerId));
                userId2User.putIfAbsent(followeeId, new User(followeeId));
                userId2User.get(followerId).follow(followeeId);
            }

            public void unfollow(int followerId, int followeeId) {
                if (!userId2User.containsKey(followerId)) return;
                userId2User.get(followerId).following.remove(followeeId);
            }

            public List<Integer> getNewsFeed(int userId) {
                List<Integer> res = new ArrayList<>();
                User user = userId2User.get(userId);
                if (user == null) return res;

                Set<Integer> following = user.following;
                PriorityQueue<Tweet> pq = new PriorityQueue<>((t1, t2) -> t2.timestamp - t1.timestamp);
                for (int id : following) {
                    Tweet tweet = userId2User.get(id).head;
                    if (tweet != null) pq.offer(tweet);
                }
                while (!pq.isEmpty()) {
                    if (res.size() == 10) break;
                    Tweet tweet = pq.poll();
                    res.add(tweet.id);
                    if (tweet.next != null) pq.offer(tweet.next);
                }
                return res;
            }
        }

    }

}
