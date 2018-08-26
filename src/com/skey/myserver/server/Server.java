package com.skey.myserver.server;

import com.skey.myserver.utils.IOUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * MyServer 服务
 *
 * @author ALion
 * @version 2018/8/3 23:27
 */
public class Server {

    private static ServerSocket server;

    private boolean isShutdown = false;

    public void start() {
        start(8888);
    }

    public void start(int port) {
        try {
            server = new ServerSocket(port);
            WebApp.init();//触发静态代码块初始化，XML解析
            this.receive();
        } catch (IOException e) {
            e.printStackTrace();
            stop();
        }
    }

    private void receive()  {
        try {
            while (!isShutdown) {
                Socket client = server.accept();
                new Thread(new Dispatcher(client)).start();
            }
        } catch (IOException e) {
//            e.printStackTrace();
            stop();
        }
    }


    public void stop() {
        isShutdown = true;
        IOUtils.close(server);
    }

}
