import java.util.Set;
import java.util.TreeMap;

/**
 * Description:
 * Created with IntelliJ IDEA.
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/15
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
        tm.put("li", 24);
        tm.put("wang", 25);

        Set<String> set = tm.keySet();
        for (String key : set) {
            Integer value = tm.get(key);
            System.out.println(key + ":" + value);
        }
    }
}
