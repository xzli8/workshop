package load_balance.smooth_weighted_round;

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
