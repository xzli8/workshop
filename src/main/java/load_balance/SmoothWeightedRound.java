package load_balance;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Ref: https://juejin.cn/post/6844903793012768781
 * Note: 既有轮询的效果，又避免了某台服务器压力突然升高。
 */
public class SmoothWeightedRound {

    public class Server {

        // IP地址
        private String ip;

        // 固定权重
        private int weight;

        // 当前权重
        private int currentWeight;

        public Server(String ip, int weight, int currentWeight) {
            this.ip = ip;
            this.weight = weight;
            this.currentWeight = currentWeight;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getCurrentWeight() {
            return currentWeight;
        }

        public void setCurrentWeight(int currentWeight) {
            this.currentWeight = currentWeight;
        }

    }

    private Server[] servers;

    private int weightSum;

    public SmoothWeightedRound(Server[] servers) {
        this.servers = servers;
        this.weightSum = Arrays.stream(servers).mapToInt(Server::getWeight).sum();
    }

    public Server route() {
        Server maxWeightServer = null;
        for (Server server : servers) {
            if (maxWeightServer == null || server.getCurrentWeight() > maxWeightServer.getCurrentWeight()) {
                maxWeightServer = server;
            }
        }
        maxWeightServer.setCurrentWeight(maxWeightServer.getCurrentWeight() - weightSum);
        for (Server server : servers) {
            server.setCurrentWeight(server.getCurrentWeight() + server.getWeight());
        }
        return maxWeightServer;
    }


    @Test
    public void test() {
        Server[] servers = new Server[3];
        servers[0] = new Server("192.168.0.1", 1, 1);
        servers[1] = new Server("192.168.0.2", 2, 2);
        servers[2] = new Server("192.168.0.3", 3, 3);
        SmoothWeightedRound smoothWeightedRound = new SmoothWeightedRound(servers);
        Map<String, Integer> serverIp2Num = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            Server server = smoothWeightedRound.route();
            System.out.println(server.getIp());
            serverIp2Num.put(server.getIp(), serverIp2Num.getOrDefault(server.getIp(), 0) + 1);
        }
        System.out.println(serverIp2Num);
    }

}
