package com.liaoin.muti.test.file.fileBase64;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {
    private static Properties prop;

    private PropertiesUtil() {
    }

    public static void init(Properties prop) {
        PropertiesUtil.prop = prop;
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

    public static String get(String key, String... args) {
        String value = prop.getProperty(key);
        if (null != args && args.length > 0) {
            if (value == null) {
                return args[0];
            }
            for (int i = 0; i < args.length; i++) {
                value = value.replace("{" + i + "}", args[i]);
            }
        }

        return value;
    }

    public static Set<Map.Entry<Object, Object>> getKeyVal() {
        return prop.entrySet();
    }

}