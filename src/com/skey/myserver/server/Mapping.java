package com.skey.myserver.server;

import java.util.ArrayList;
import java.util.List;

/**
 * 存Mapping信息
 *
 * @author ALion
 * @version 2018/8/5 13:41
 */
public class Mapping {

    private String name;

    private List<String> urlPattern = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(List<String> urlPattern) {
        this.urlPattern = urlPattern;
    }

    @Override
    public String toString() {
        return "Mapping{" +
                "name='" + name + '\'' +
                ", urlPattern=" + urlPattern +
                '}';
    }
}
