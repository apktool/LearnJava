package com.tmp;

import com.utils.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        String[] files = new String[]{
                "application.properties",
                "log4j2.properties"
        };

        Properties properties = new Properties();

        for (String file : files) {
            properties = PropertyUtil.addProperty(file);
        }


        String tmp = properties.getProperty("tmp.name");
        logger.info(tmp);

        tmp = properties.getProperty("rootLogger.level");
        logger.debug(tmp);
    }
}
