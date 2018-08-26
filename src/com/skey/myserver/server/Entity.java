package com.skey.myserver.server;

/**
 * 存Servlet信息
 *
 * @author ALion
 * @version 2018/8/5 13:41
 */
public class Entity {

    private String name;
    private String clazz;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }

}
