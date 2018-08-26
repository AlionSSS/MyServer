package com.skey.myserver.server;

import com.skey.myserver.servlet.Servlet;
import com.skey.myserver.utils.IOUtils;

import java.io.IOException;
import java.net.Socket;

/**
 * Servlet调度器
 * 一个请求与响应 对应 一个Dispatcher
 *
 * @author ALion
 * @version 2018/8/4 18:43
 */
public class Dispatcher implements Runnable {

    private Socket client;
    private Request request;
    private Response response;

    private int code = 200;

    public Dispatcher(Socket socket) {
        this.client = socket;
        try {
            request = new Request(socket.getInputStream());
            response = new Response(socket.getOutputStream());
        } catch (IOException e) {
//            e.printStackTrace();
            code = 500;
        }
    }

    @Override
    public void run() {
        try {
            Servlet servlet = WebApp.getServlet(request.getUrl());//根据不同的request，调用不同的Servlet
            if (servlet == null) code = 404;
            else servlet.service(request, response);
            response.pushToClient(code);//返回到客户端
        } catch (Exception e) {
            try {
                response.pushToClient(500);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            System.out.println("响应结束 close");
            IOUtils.close(client);
        }
    }

}
