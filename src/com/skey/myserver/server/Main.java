package com.skey.myserver.server;

/**
 * Server启动入口
 *
 * @author ALion
 * @version 2018/8/4 18:58
 */
public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        server.start(8888);
    }

}
