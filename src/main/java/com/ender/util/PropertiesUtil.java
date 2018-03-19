package com.ender.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by ender on 2017/3/15.
 */
public class PropertiesUtil {

    private static Properties prop;

    static {
        prop = new Properties();
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("conf/httpcode.properties");
        try {
            prop.load(new InputStreamReader(in, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                
            }
        }
    }

    public static Properties getProp() {
        return prop;
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static String getProperty(int key) {
        return getProperty(BaseUtil.dealString(key));
    }
}
