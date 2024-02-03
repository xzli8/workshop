package load_balance.consistentHash;

public class Server {

    // ip地址
    private String ip;

    public Server(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
