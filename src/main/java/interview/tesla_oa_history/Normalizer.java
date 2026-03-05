package interview.tesla_oa_history;

import org.junit.Test;

public class Normalizer {

    /**
     * Implement method "normalize", returns a unified format of [protocol]://[domain]:[port]/path
     * and must fill in the missing parts with the defaults:
     * protocol -> "http"
     * domain -> "localhost"
     * port -> 80
     * path -> "/"
     */

    public String normalize(String s) {
        if (s == null || s.isEmpty()) {
            return "http://localhost:80/";
        }

        // 1. 解析协议：有就用，没有默认 http
        String protocol = "http";
        String remaining = s;
        if (s.contains("://")) {
            String[] parts = s.split("://", 2);
            protocol = parts[0];
            remaining = parts[1];
        }

        // 2. 解析路径：从第一个 / 开始，没有就用默认 /
        String path = "/";
        int pathIndex = remaining.indexOf('/');
        if (pathIndex != -1) {
            path = remaining.substring(pathIndex);
            remaining = remaining.substring(0, pathIndex);
        }

        // 3. 解析域名 + 端口 → 完全用你说的逻辑！
        String domain = "localhost";
        int port = 80;

        // 分割成 [域名, 端口] 两部分
        String[] domainPort = remaining.split(":", 2);
        // 域名：不为空就用，空则默认 localhost
        domain = domainPort[0].isEmpty() ? "localhost" : domainPort[0];

        // 端口：有就用，没有默认 80 （你的思路！）
        if (domainPort.length > 1) {
            port = Integer.parseInt(domainPort[1]);
        }

        // 拼接成标准格式
        return protocol + "://" + domain + ":" + port + path;
    }

    @Test
    public void test() {
        System.out.println(normalize("https://example.com:8080/a/b/c"));   // https://example.com:8080/a/b/c
        System.out.println(normalize("example.com"));   // http://example.com:80/
        System.out.println(normalize(""));  // http://localhost:80/
    }

}
