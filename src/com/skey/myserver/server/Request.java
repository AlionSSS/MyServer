package com.skey.myserver.server;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Request 请求
 *
 * @author ALion
 * @version 2018/8/3 23:59
 */
public class Request {

    public static final String CRLF = "\r\n";

//    private InputStream is;
    private String requestInfo;

    private String method;
    private String url;
    private Map<String, List<String>> parameterMap;

    private Request() {
        requestInfo = "";
        method = "";
        url = "";
        parameterMap = new HashMap<>();
    }

    public Request(Socket socket) throws IOException {
        this(socket.getInputStream());
    }

    public Request(InputStream is) {
        this();
//        this.is = is;

        byte[] data = new byte[2048];
        try {
            int len = is.read(data);
            System.out.println("len = " + len);
            requestInfo = len == -1 ? "" : new String(data, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseRequestInfo(requestInfo);
    }

    /**
     * 解析请求信息
     *
     * @param requestInfo 请求信息
     */
    private void parseRequestInfo(String requestInfo) {
        System.out.println("------ Request ------");
        System.out.println(requestInfo);
        System.out.println("------ ------- ------");
        if (requestInfo == null || "".equals(requestInfo.trim())) {
            return;
        }
        String parameter = "";
        String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
        int idx = requestInfo.indexOf("/");
        method = firstLine.substring(0, idx).trim();
        String urlString = firstLine.substring(idx, firstLine.indexOf("HTTP/")).trim();
        if ("GET".equalsIgnoreCase(method)) {
            if (urlString.contains("?")) {
                String[] fields = urlString.split("\\?");
                url = fields[0];
                parameter = fields[1];
            } else {
                url = urlString;
            }
        } else if ("POST".equalsIgnoreCase(method)) {
            url = urlString;
            parameter = requestInfo.substring(requestInfo.lastIndexOf(CRLF) + 2);
        }

        // 解析parameter
//        parameter = "name=小王&age=21&fav=book&fav=movie&add";//测试用
        if (!"".equals(parameter)) {
            parseParameter(parameter);
        }
    }

    private void parseParameter(String parameter) {
        String[] fields = parameter.split("&");
        for (String field : fields) {
            String[] kv = field.split("=");
            List<String> vList = parameterMap.get(kv[0]);
            if (vList == null) vList = new ArrayList<>();
//            if(kv.length == 2) vList.add(decode(kv[1], "utf-8"));
            if (kv.length == 2) vList.add(kv[1]);
            parameterMap.put(kv[0], vList);
        }
    }

    public void print() {
        System.out.println(method);
        System.out.println(url);
        System.out.println(parameterMap);
    }

    public String[] getParameter(String key) {
        List<String> values = parameterMap.get(key);
        if (values == null) {
            return null;
        } else {
            return values.toArray(new String[0]);
        }
    }

    public String getUrl() {
        return url;
    }

    public Map<String, List<String>> getParameterMap() {
        return parameterMap;
    }

    /**
     * 解码
     */
    private String decode(String value, String code) {
        try {
            return URLDecoder.decode(value, code);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
