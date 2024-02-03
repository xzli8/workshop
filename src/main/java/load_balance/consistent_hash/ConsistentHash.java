package load_balance.consistent_hash;

import java.util.*;

public class ConsistentHash {

    // 哈希环：虚拟节点的哈希值 -> 实际节点列表
    // 因为存在哈希冲突，多个虚拟节点可能映射到同一个哈希值，所以Map的value是List
    private final NavigableMap<Integer, List<Server>> hashRing = new TreeMap<>();

    // 每个server有100个虚拟节点
    private final int virtualReplicaNum = 100;

    // 向哈希环中添加一个节点(将该节点的100个虚拟节点添加到哈希环中)
    public void addServer(Server server) {
        for (int i = 0; i < virtualReplicaNum; i++) {
            String key = server.getIp() + i;
            int hash = key.hashCode();  // 可以替换成其他哈希算法
            hashRing.putIfAbsent(hash, new ArrayList<>());
            hashRing.get(hash).add(server);
        }
    }

    public void removeServer(Server server) {
        for (int i = 0; i < virtualReplicaNum; i++) {
            String key = server.getIp() + i;
            int hash = key.hashCode();
            if (hashRing.containsKey(hash)) {
                List<Server> servers = hashRing.get(hash);
                servers.remove(server);
                if (servers.size() == 0) hashRing.remove(hash);
            }
        }
    }

    public Server route(int hash) {
        if (hashRing.isEmpty()) return null;
        // 选择顺时针方向的第一个server，如果没找到，转一圈回到开头的第一个
        Map.Entry<Integer, List<Server>> entry = hashRing.ceilingEntry(hash);
        if (entry == null) entry = hashRing.firstEntry();
        List<Server> servers = entry.getValue();
        return servers.get(hash % servers.size());
    }



    public static void main(String[] args) {
        ConsistentHash consistentHash = new ConsistentHash();
        Server server1 = new Server("198.162.0.1");
        Server server2 = new Server("198.162.0.2");
        Server server3 = new Server("198.162.0.3");
        consistentHash.addServer(server1);
        consistentHash.addServer(server2);
        consistentHash.addServer(server3);

        System.out.println("----- before remove server1 -----");
        System.out.println(consistentHash.route(-979057805).getIp());
        System.out.println(consistentHash.route(-979056807).getIp());
        System.out.println(consistentHash.route(-979055759).getIp());

        consistentHash.removeServer(server1);
        System.out.println("----- after remove server1 -----");
        System.out.println(consistentHash.route(-979057805).getIp());
        System.out.println(consistentHash.route(-979056807).getIp());
        System.out.println(consistentHash.route(-979055759).getIp());

        consistentHash.removeServer(server2);
        System.out.println("----- after remove server2 -----");
        System.out.println(consistentHash.route(-979057805).getIp());
        System.out.println(consistentHash.route(-979056807).getIp());
        System.out.println(consistentHash.route(-979055759).getIp());
    }

}
