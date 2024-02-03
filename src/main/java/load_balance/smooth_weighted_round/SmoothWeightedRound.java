package load_balance.smooth_weighted_round;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmoothWeightedRound {

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


    public static void main(String[] args) {
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
