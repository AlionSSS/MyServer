package com.skey.myserver.server;

import java.util.HashMap;
import java.util.Map;

/**
 * ServletContext 上下文
 *
 * @author ALion
 * @version 2018/8/4 23:04
 */
public class ServletContext {

    private Map<String, String> mapping;//key为url,value为name
    private Map<String, String> servlet;//key为name,value为Servlet类完整路径 例com.skey.test.server.LoginServlet

    public ServletContext() {
        this.mapping = new HashMap<>();
        this.servlet = new HashMap<>();
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }

    public Map<String, String> getServlet() {
        return servlet;
    }

    public void setServlet(Map<String, String> servlet) {
        this.servlet = servlet;
    }
}
