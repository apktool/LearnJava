/**
 * Description:
 * Properties
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/18
 */
import java.io.*;
import java.util.Properties;
import java.util.Set;

public class PropertiesDemo {
    public static void main(String[] argv) throws IOException {
        Properties prop = new Properties();

        prop.put("it1", "hello");
        prop.put("it2", "world");
        System.out.println(prop);

        Set<Object> set = prop.keySet();
        for (Object key : set) {
            Object value = prop.get(key);
            System.out.println(key + "--" + value);
        }

        System.out.println("--------------------");

        prop.setProperty("it3", "what");
        prop.setProperty("it4", "java");
        Set<String> set1 = prop.stringPropertyNames();
        for (String key : set1) {
            String value = prop.getProperty(key);
            System.out.println(key + "--" + value);
        }

        System.out.println("--------------------");

        Writer w = new FileWriter("prop.txt");
        prop.store(w, "hello world");
        w.close();

        System.out.println("--------------------");

        Reader r = new FileReader("prop.txt");
        prop.load(r);
        r.close();
    }
}

/*
 * prop.txt
 * a=1
 * b=2
 */
