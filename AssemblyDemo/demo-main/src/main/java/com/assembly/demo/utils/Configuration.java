package com.assembly.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author apktool
 * @package com.ants.ds.configuration
 * @class Configuration
 * @description TODO
 * @date 2020-09-05 19:18
 */

@Slf4j
public class Configuration {
    private static final CopyOnWriteArrayList<String> defaultResources = new CopyOnWriteArrayList();
    private static final WeakHashMap<Configuration, Object> REGISTRY = new WeakHashMap();
    @Setter
    private ClassLoader classLoader;
    private ArrayList<Resource> resources;
    private Properties properties;
    private Properties overlay;
    private boolean loadDefaults;
    private String dfsConfigLocation = null;

    public Configuration() {
        this(true);
    }

    public Configuration(boolean loadDefaults) {
        this.resources = new ArrayList();
        this.loadDefaults = true;
        this.classLoader = Thread.currentThread().getContextClassLoader();
        if (this.classLoader == null) {
            this.classLoader = Configuration.class.getClassLoader();
        }

        this.loadDefaults = loadDefaults;

        if (getProperty("dfs.config.location") != null) {
            dfsConfigLocation = getProperty("dfs.config.location");
        }

        synchronized (Configuration.class) {
            REGISTRY.put(this, null);
        }
    }

    public static synchronized void addDefaultResource(String name) {
        if (!defaultResources.contains(name)) {
            defaultResources.add(name);
            Iterator var1 = REGISTRY.keySet().iterator();

            while (var1.hasNext()) {
                Configuration conf = (Configuration) var1.next();
                if (conf.loadDefaults) {
                    conf.reloadConfiguration();
                }
            }
        }
    }

    public void addResource(String name) {
        this.addResourceObject(new Resource(name));
    }

    public void addResource(URL url) {
        this.addResourceObject(new Resource(url));
    }

    public void addResource(Path file) {
        this.addResourceObject(new Resource(file));
    }

    public void addResource(InputStream in) {
        this.addResourceObject(new Resource(in));
    }

    public void addResource(InputStream in, String name) {
        this.addResourceObject(new Resource(in, name));
    }

    private synchronized void addResourceObject(Resource resource) {
        this.resources.add(resource);
        this.reloadConfiguration();
    }

    public synchronized void reloadConfiguration() {
        this.properties = null;
    }

    protected synchronized Properties getProps() {
        if (this.properties == null) {
            this.properties = new Properties();
            try {
                this.loadResources(this.properties, this.resources);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (this.overlay != null) {
                this.properties.putAll(this.overlay);
            }
        }

        return this.properties;
    }

    private void loadResources(Properties properties, ArrayList<Resource> resources) throws IOException {
        if (this.loadDefaults) {
            Iterator var4 = defaultResources.iterator();

            while (var4.hasNext()) {
                String resource = (String) var4.next();
                this.loadResource(properties, new Resource(resource));
            }
        }

        for (int i = 0; i < resources.size(); ++i) {
            Resource ret = loadResource(properties, resources.get(i));
            if (ret != null) {
                resources.set(i, ret);
            }
        }
    }

    private Resource loadResource(Properties properties, Resource wrapper) throws IOException {
        Object resource = wrapper.getResource();

        if (resource instanceof URL) {
            properties.load(parse((URL) resource));
        } else if (resource instanceof String) {
            if (dfsConfigLocation != null) {
                File file = new File(dfsConfigLocation + File.separator + resource);
                if (file.exists()) {
                    log.debug("parsing File " + file);
                    properties.load(new FileInputStream(file));
                } else {
                    throw new RuntimeException("I can't find " + file.getAbsolutePath());
                }
            } else {
                URL url = this.classLoader.getResource((String) resource);
                properties.load(parse(url));
            }

        } else if (resource instanceof Path) {
            File file = (new File(((Path) resource).toUri().getPath())).getAbsoluteFile();
            if (file.exists()) {
                log.debug("parsing File " + file);
                properties.load(new FileInputStream(file));
            }
        } else if (resource instanceof InputStream) {
            properties.load((InputStream) resource);
        } else if (resource instanceof Properties) {
            overlay(properties, (Properties) resource);
        }

        return wrapper;
    }

    private InputStream parse(URL url) throws IOException {
        if (url == null) {
            throw new RuntimeException("I can't find " + resources.toString());
        } else {
            URLConnection connection = url.openConnection();
            if (connection instanceof JarURLConnection) {
                connection.setUseCaches(false);
            }

            return connection.getInputStream();
        }
    }

    private void overlay(Properties to, Properties from) {
        Iterator var3 = from.entrySet().iterator();

        while (var3.hasNext()) {
            Map.Entry<Object, Object> entry = (Map.Entry) var3.next();
            to.put(entry.getKey(), entry.getValue());
        }
    }

    String getenv(String name) {
        return System.getenv(name);
    }

    String getProperty(String key) {
        return System.getProperty(key);
    }

    public String getTrimmed(String name) {
        String value = this.get(name);
        return null == value ? null : value.trim();
    }

    public String getTrimmed(String name, String defaultValue) {
        String ret = this.getTrimmed(name);
        return ret == null ? defaultValue : ret;
    }

    public String getRaw(String name) {
        return getProps().getProperty(name);
    }

    private synchronized Properties getOverlay() {
        if (this.overlay == null) {
            this.overlay = new Properties();
        }

        return this.overlay;
    }

    public void set(String name, String value) {
        name = name.trim();
        this.getOverlay().setProperty(name, value);
        this.getProps().setProperty(name, value);

    }

    public String get(String name) {
        return getProps().getProperty(name);
    }

    public String get(String name, String defaultValue) {
        return getProps().getProperty(name, defaultValue);
    }

    public int getInt(String name, int defaultValue) {
        String valueString = this.getTrimmed(name);
        if (valueString == null) {
            return defaultValue;
        } else {
            String hexString = this.getHexDigits(valueString);
            return hexString != null ? Integer.parseInt(hexString, 16) : Integer.parseInt(valueString);
        }
    }

    public void setInt(String name, int value) {
        this.set(name, Integer.toString(value));
    }

    public long getLong(String name, long defaultValue) {
        String valueString = this.getTrimmed(name);
        if (valueString == null) {
            return defaultValue;
        } else {
            String hexString = this.getHexDigits(valueString);
            return hexString != null ? Long.parseLong(hexString, 16) : Long.parseLong(valueString);
        }
    }

    private String getHexDigits(String value) {
        boolean negative = false;
        String str = value;
        String hexString = null;
        if (value.startsWith("-")) {
            negative = true;
            str = value.substring(1);
        }

        if (!str.startsWith("0x") && !str.startsWith("0X")) {
            return null;
        } else {
            hexString = str.substring(2);
            if (negative) {
                hexString = "-" + hexString;
            }

            return hexString;
        }
    }

    public void setLong(String name, long value) {
        this.set(name, Long.toString(value));
    }

    public float getFloat(String name, float defaultValue) {
        String valueString = this.getTrimmed(name);
        return valueString == null ? defaultValue : Float.parseFloat(valueString);
    }

    public void setFloat(String name, float value) {
        this.set(name, Float.toString(value));
    }

    public double getDouble(String name, double defaultValue) {
        String valueString = this.getTrimmed(name);
        return valueString == null ? defaultValue : Double.parseDouble(valueString);
    }

    public void setDouble(String name, double value) {
        this.set(name, Double.toString(value));
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        String valueString = this.getTrimmed(name);
        if (null != valueString && !valueString.isEmpty()) {
            if (valueString.equalsIgnoreCase("true")) {
                return true;
            } else {
                return valueString.equalsIgnoreCase("false") ? false : defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public void setBoolean(String name, boolean value) {
        this.set(name, Boolean.toString(value));
    }

    public <T extends Enum<T>> void setEnum(String name, T value) {
        this.set(name, value.toString());
    }

    public <T extends Enum<T>> T getEnum(String name, T defaultValue) {
        String val = this.getTrimmed(name);
        return null == val ? defaultValue : Enum.valueOf(defaultValue.getDeclaringClass(), val);
    }

    public Pattern getPattern(String name, Pattern defaultValue) {
        String valString = this.get(name);
        if (null != valString && !valString.isEmpty()) {
            try {
                return Pattern.compile(valString);
            } catch (PatternSyntaxException var5) {
                log.warn("Regular expression '" + valString + "' for property '" + name + "' not valid. Using default", var5);
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    public void setPattern(String name, Pattern pattern) {
        assert pattern != null : "Pattern cannot be null";

        this.set(name, pattern.pattern());
    }

    public URL getResource(String name) {
        return this.classLoader.getResource(name);
    }

    public InputStream getConfResourceAsInputStream(String name) {
        try {
            URL url = this.getResource(name);
            if (url == null) {
                log.info(name + " not found");
                return null;
            } else {
                log.info("found resource " + name + " at " + url);
                return url.openStream();
            }
        } catch (Exception var3) {
            return null;
        }
    }

    public Reader getConfResourceAsReader(String name) {
        try {
            URL url = this.getResource(name);
            if (url == null) {
                log.info(name + " not found");
                return null;
            } else {
                log.info("found resource " + name + " at " + url);
                return new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
            }
        } catch (Exception var3) {
            return null;
        }
    }

    private static class Resource {
        private final Object resource;
        private final String name;

        public Resource(Object resource) {
            this(resource, resource.toString());
        }

        public Resource(Object resource, String name) {
            this.resource = resource;
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public Object getResource() {
            return this.resource;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
