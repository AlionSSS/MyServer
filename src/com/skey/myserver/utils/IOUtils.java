package com.skey.myserver.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * IO关闭工具
 *
 * @author ALion
 * @version 2018/7/29 13:23
 */
public class IOUtils {

    public static void close(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
