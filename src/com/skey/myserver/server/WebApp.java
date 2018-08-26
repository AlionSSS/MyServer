package com.skey.myserver.server;

import com.skey.myserver.servlet.Servlet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Map;

/**
 * WebApp 用于Servlet的工厂创建
 *
 * @author ALion
 * @version 2018/8/4 23:07
 */
public class WebApp {

    private static ServletContext context;

    //WebApp被第一次访问时，会执行该代码块
    static {
        try {
            //SAX解析xml，得到servlet servlet-mapping
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            WebHandler handler = new WebHandler();
            parser.parse(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("WEB_INFO/web.xml"),
                    handler);

            //信息存入context
            context = new ServletContext();
            Map<String, String> mapping = context.getMapping();
            for (Mapping map : handler.getMappingList()) {
                for (String url : map.getUrlPattern()) {
                    mapping.put(url, map.getName());
                }
            }
            Map<String, String> servlet = context.getServlet();
            for (Entity entity : handler.getEntityList()) {
                servlet.put(entity.getName(), entity.getClazz());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        System.out.println("init WebApp");
    }

    /**
     * 获取 Servlet
     * @param url url路径
     * @return Servlet
     */
    public static Servlet getServlet(String url) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if ("".equals(url) || url == null) {
            return null;
        }

        //反射实例化对象，根据url->name->servlet包
        String name = context.getMapping().get(url);
        String servletName = context.getServlet().get(name);

        System.out.println("反射 " + servletName);
        Class<?> clazz = Class.forName(servletName);
        Servlet serv = (Servlet) clazz.newInstance();
        return serv;
    }

}
