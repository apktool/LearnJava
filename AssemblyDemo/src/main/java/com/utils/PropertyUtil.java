package com.utils;

import com.tmp.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class PropertyUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private static Properties props;
    private static String prefix = "conf";

    synchronized static private void loadProps(String fileName) {
        props = new Properties();

        ClassLoader loader = App.class.getClassLoader();

        try (InputStream in1 = loader.getResourceAsStream(prefix + "/" + fileName)) {
            props.load(in1);
        } catch (IOException | NullPointerException ec) {
            try (InputStream in2 = loader.getResourceAsStream(fileName)) {
                props.load(in2);
            } catch (IOException | NullPointerException e) {
                logger.error("Please check where are properties");
            }
        }
    }


    public static String getProperty(String fileName, String key) {
        if (null == props) {
            loadProps(fileName);
        }
        return props.getProperty(key);
    }

    public static String getProperty(String fileName, String key, String defaultValue) {
        if (null == props) {
            loadProps(fileName);
        }
        return props.getProperty(key, defaultValue);
    }

    public static Properties getAll(String fileName) {
        if (null == props) {
            loadProps(fileName);
        } else {
            if (!props.propertyNames().equals(fileName)) {
                loadProps(fileName);
            }
        }

        return props;
    }

    public static Properties addProperty(String fileName) {

        Properties pre = props;

        if (null == pre) {
            getAll(fileName);
            pre = props;
        } else {
            getAll(fileName);
        }

        for (Map.Entry<Object, Object> map : pre.entrySet()) {
            props.put(map.getKey(), map.getValue());
        }

        return props;
    }
}
