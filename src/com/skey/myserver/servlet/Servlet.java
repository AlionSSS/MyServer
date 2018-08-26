package com.skey.myserver.servlet;

import com.skey.myserver.server.Request;
import com.skey.myserver.server.Response;

/**
 * Base Servlet 用于子类继承
 *
 * @author ALion
 * @version 2018/8/4 18:27
 */
public abstract class Servlet {

    public void service(Request request, Response response) throws Exception {
        this.doGet(request, response);
        this.doPost(request, response);
    }

    protected abstract void doGet(Request request, Response response) throws Exception;

    protected abstract void doPost(Request request, Response response) throws Exception;

}
