package com.skey.myserver.servlet;

import com.skey.myserver.server.Request;
import com.skey.myserver.server.Response;

/**
 * 示例-注册页面的Servlet
 *
 * @author ALion
 * @version 2018/8/4 23:11
 */
public class RegisterServlet extends Servlet {

    @Override
    public void doGet(Request request, Response response) throws Exception {

    }

    @Override
    public void doPost(Request request, Response response) throws Exception {
        String[] names = request.getParameter("name");
        String content = names == null ? "未传递name参数！" : "欢迎注册 " + names[0] + " ！";

        response.print("<html><head><title>HTTP响应示例</title>")
                .print("</head><body>")
                .print(content)
                .print("</body></html>");
    }
}
