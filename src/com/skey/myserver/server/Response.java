package com.skey.myserver.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Response 响应
 *
 * @author ALion
 * @version 2018/8/3 22:52
 */
public class Response {

    public static final String CRLF = "\r\n";
    public static final String BLANK = " ";

    //首行
    private StringBuilder firstLine;

    //头信息
    private StringBuilder head;

    //正文
    private StringBuilder content;

    private BufferedWriter bw;

    private Response() {
        head = new StringBuilder();
        firstLine = new StringBuilder();
        content = new StringBuilder();
    }

    public Response(Socket socket) {
        this();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            head = null;
        }
    }

    public Response(OutputStream os) {
        this();
        bw = new BufferedWriter(new OutputStreamWriter(os));
    }

    /**
     * 构建响应首行
     * @param code 响应码
     */
    private void createFirstLine(int code) {
        firstLine.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
        switch (code) {
            case 200:
                firstLine.append("OK").append(CRLF);
                break;
            case 404:
                firstLine.append("NOT FOUND").append(CRLF);
                break;
            case 500:
                firstLine.append("SERVER ERRO").append(CRLF);
                break;
        }
    }

    /**
     * 构建响应头
     */
    private void createHead() {
        head.append("Server:Skey Server/0.1").append(CRLF);
        head.append("Date:").append(new Date()).append(CRLF);
        head.append("Content-type:text/html;charset=UTF-8").append(CRLF);
        head.append("Content-Length:").append(content.toString().getBytes().length).append(CRLF);
    }

    /**
     * 构建正文
     *
     * @param info 信息
     * @return Response
     */
    public Response print(String info) {
        content.append(info);
        return this;
    }

    public void pushToClient(int code) throws IOException {
        if (head == null) code = 500;

        createFirstLine(code);
        createHead();

        bw.append(firstLine.toString());//请求首行
        bw.append(head.toString());//请求头
        bw.append(CRLF);//请求空行
        bw.append(content.toString());//请求正文
        bw.flush();
    }



}
