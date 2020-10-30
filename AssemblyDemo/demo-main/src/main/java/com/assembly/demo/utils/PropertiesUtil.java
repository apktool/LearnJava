package com.assembly.demo.utils;

public class PropertiesUtil {
    private static Configuration instance = null;

    public static Configuration getProp() {
        if (instance != null) {
            return instance;
        }
        synchronized (PropertiesUtil.class) {
            if (instance != null) {
                return instance;
            }

            instance = new Configuration();
            instance.addResource("application.properties");
        }

        return instance;
    }
}
