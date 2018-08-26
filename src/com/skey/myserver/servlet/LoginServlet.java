package com.skey.myserver.servlet;

import com.skey.myserver.server.Request;
import com.skey.myserver.server.Response;

/**
 * 示例-登录页面的Servlet
 *
 * @author ALion
 * @version 2018/8/4 23:11
 */
public class LoginServlet extends Servlet {

    @Override
    public void doGet(Request request, Response response) throws Exception {
        if (request.getParameter("uname") == null) {
            response.print("未传递uname参数");
            return;
        }
        if (request.getParameter("pwd") == null) {
            response.print("未传递pwd参数");
            return;
        }
        String uname = request.getParameter("uname")[0];
        String pwd = request.getParameter("pwd")[0];
        boolean result = login(uname, pwd);
        String content = result ? "登录成功" : "登录失败";

        response.print("<html><head><title>登录结果</title>")
                .print("</head><body>")
                .print("^_^ " + content + " ！")
                .print("</body></html>");
    }

    public boolean login(String uname, String pwd) {
        return "jhz".equals(uname) && "12345".equals(pwd);
    }

    @Override
    public void doPost(Request request, Response response) throws Exception {

    }
}
