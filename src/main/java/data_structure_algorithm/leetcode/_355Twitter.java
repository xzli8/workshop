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

    /**
     * ============ 面试 follow-up：多线程下的 follow 与死锁 ============
     *
     * ---------- 情况一：只存 following ----------
     * 业务场景：
     *   只记录"我关注了谁"，不记录"谁关注了我"（当前 Solution1 的结构），多个线程会同时操作同一个用户：follow(A,B) 和 follow(A,C) 同时往 A.following 里加，
     *   或 getNewsFeed(A) 遍历 A.following 时 follow(A,D) 往里加（普通 HashSet 在这种情况下会丢元素、遍历抛异常、扩容时结构损坏）。
     * 本质：
     *   一个资源，容器自己的原子操作够用。
     * 解决方案：
     *   把容器换成线程安全的版本，它保证单次 add、remove、遍历在多线程下正确：
     *      following → ConcurrentHashMap.newKeySet()
     *      userId2User → ConcurrentHashMap + computeIfAbsent
     *      timestamp → AtomicInteger
     *      head → volatile（Tweet 节点建好后 next 不变，读线程顺着走是一致快照）
     *   不会死锁：没有显式锁，不存在持有一把去等另一把的路径。
     *
     * ---------- 情况二：加 followers，强一致 ----------
     * 业务场景：
     *   要查"谁关注了我（粉丝数）"，每个 User 加一个 followers，要求 follow(A,B) 完成后，A.following 和 B.followers 同时生效，
     *   任何时刻不能读到"A 关注了 B，但 B 的粉丝里没有 A"。
     * 本质：
     *   两个资源，而且两次修改必须一起生效。
     *   容器只能保证各自原子，保证不了合起来原子，所以要在外面加锁把两步包住。
     * 解决方案：
     *   方案 0：整个 Twitter 一把全局锁，四个方法全部 synchronized。
     *         绝对安全，绝对不死锁，因为只有一把锁不存在互相等。
     *         缺点是锁粒度大效率低（所有用户的所有操作排队，A 发推时 B 刷 feed 也要等，吞吐是单线程水平）。
     *         要解决这个问题的思路是减小锁粒度，每用户一把锁（follow(A,B) 要同时持有 A 和 B 的锁，但不持有其他用户的锁），不相关的用户可以并行。
     *   死锁方案：加锁顺序跟着参数走（follow(A,B) 先锁 A 再锁 B，follow(B,A) 先锁 B 再锁 A，两个同时跑，各拿一把，各等一把，互相等死。）
     *      死锁的四个条件（必须同时满足才会死锁，破坏任意一个即可）：
     *        互斥：一个资源同一时刻只能被一个线程占有（每用户一把锁，一次只能一个线程持有）。破坏方式是根本不持有锁，没有锁就没有互斥（情况一就是这样，但情况二要求两步一起生效，不持锁做不到）。
     *        持有并等待：线程已经持有至少一把锁，还在等别的锁，且等的时候不释放手里的（ follow(A,B) 拿着 A 的锁去申请 B 的锁）。破坏方式是一次拿全，要么全拿到要么一把不拿（方案 0 的全局锁是极端形式，只有一把，拿到即全拿；也可以用一把全局锁只保护"拿 A 和 B"这一步，拿到后放掉再干活，但不如排序）。
     *        不可抢占：线程拿到的锁别人夺不走，只能它自己放。破坏方式是拿不到第二把就自己放掉第一把（方案 2，不预防死锁，只是事后解开）。
     *        循环等待：若干线程互相等对方手里的锁，等待关系成环（线程 1 拿着 A 等 B，线程 2 拿着 B 等 A）。破坏方式是给锁编号、按序申请，等待关系只能单向（方案 1，本题 userId 就是现成的编号，所以是首选）。
     *   方案 1（首选）：按 userId 排序加锁，永远先锁小 id 再锁大 id -> 所有线程顺序一致，等待关系成不了圈。
     *         锁放在全局 ConcurrentHashMap<userId, ReentrantLock>，同一 id 永远同一把（自己关注自己时两把是同一把，ReentrantLock 可重入）。
     *   方案 2（兜底）：tryLock 超时。第一把正常拿，第二把 tryLock(50ms)，拿不到就放掉第一把，随机退避后重试（不防死锁，只能从死锁里退出来；固定退避会活锁，所以要随机）。
     *         适合锁顺序无法事先确定的场景，本题有 userId 可排序，用方案 1。
     *
     * ---------- 情况三：加 followers，最终一致 ----------
     * 业务场景：
     *   同样要查"谁关注了我（粉丝数）"，但允许 B 的粉丝列表晚几毫秒到几秒更新（即最终一致性，对 Twitter 这类产品没有业务影响，真实的 Twitter 粉丝数就是这么做的）
     * 本质：
     *   两个资源，但允许各自独立生效，退回到和情况一一样，容器够用。
     * 解决方案：
     *   follow(A,B) 只同步写 A.following，发一个事件到队列；后台消费线程写 B.followers。
     *   不会死锁：前台写 A，后台写 B，各自是容器的一次原子操作，一把显式锁都不需要。
     *   前提：事件不能丢。先落持久化队列再返回，或定期用 following 反向对账，否则进程挂了就从"暂时不一致"变成"永久不一致"。
     *   边界：A 自己的 following 必须同步写，follow 完立刻刷 feed 要能看到 B，"互关才能私信"这类要同时读两边的规则不能走最终一致。
     *
     * ---------- 延伸：分布式场景 ----------
     *   以上讨论都在单机 Java 进程内，服务部署多个实例后，每个实例的内存数据是独立的一份（A 在实例 1 上 follow 的 B 实例 2 看不到），
     *   所以数据必须搬到共享存储（数据库或 Redis），并发控制随之下沉到存储层。
     *      情况一和三的每步都是存储的单次原子操作（DB 插一行、Redis 一个 SADD），存储自己保证原子，和单机一样。
     *      情况三多一个点：前台"写库 + 发事件"是跨两个系统的两步，写完库没发出事件进程就挂了会丢事件，用 outbox 表或 CDC 解决。
     *      情况二：两步必须一起生效，进程内的锁管不了跨实例，需要存储层的手段。
     *
     *   数据库事务：关系表 follows(follower_id, followee_id) 一行同时就是 A.following 和 B.followers，插一行是原子的，情况二的两步问题在这一层直接消失。
     *         两步会因为冗余字段回来：粉丝数高频展示，COUNT(*) 太慢，users 表上存 follower_count，follow 时要插一行 + 改 A 行 + 改 B 行，三步放进一个事务。
     *         事务用行锁实现原子 -> 死锁会回来（事务 1 先锁 A 行再锁 B 行，事务 2 反过来），DB 能检测并回滚其中一个，但 DBA 的建议依然是按主键顺序更新，和方案 1 完全一样。
     *   Redis Lua：脚本把两个 SADD 打包，Redis 单线程执行 -> 天然原子，没有加锁动作（本质就是方案 0 的全局锁，只是 Redis 单线程足够快，排队的代价可以接受）。
     *         坑：Cluster 下 A、B 的 key 不在同一 slot，脚本跑不了；原子不等于持久（默认异步落盘，脚本返回成功后机器挂了可能两句都丢）。
     *   分布式锁：用 Redis 或 ZooKeeper 实现跨实例的每用户一把锁，再按 userId 排序拿（思路和方案 1 一样，但多了锁过期、续租、网络分区等问题，一般不如直接用 DB 事务）。
     *
     * ---------- 面试回答顺序 ----------
     *   1. 先指出当前结构只存 following，换线程安全容器即可，没有必须原子的多步，不会死锁。
     *   2. 面试官加 followers 后：先说全局锁能解但粒度太粗，改每用户一把锁，画出 A/B 互关的死锁表，给按 id 排序方案；提 tryLock 作对比，说明它是兜底不是预防。
     *   3. 主动说 followers 可以走最终一致，一把锁都不需要，说清代价和事件不丢的前提。
     *
     */

}
