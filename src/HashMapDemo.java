/**
 * Description:
 * Map Demo
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/15
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {
    public static void main(String[] args){
        Map<String, Integer> map = new HashMap<String, Integer>();
        // the previous value associated with key, or null if there was no mapping for key.
        map.put("li", 23);
        map.put("wang", 30);
        System.out.println(map);

        Set<String> set = map.keySet();
        for(String key: set){
            Integer value = map.get(key);
            System.out.println(key + " : " + value);
        }

        Set<Map.Entry<String, Integer>> ste = map.entrySet();
        for(Map.Entry<String, Integer> me: ste){
            String key = me.getKey();
            Integer value = me.getValue();
            System.out.println(key + " : " + value);
        }

        System.out.println(map.containsKey("li"));
        System.out.println(map.containsValue(23));
        System.out.println(map.size());

        map.remove("wang");
        System.out.println(map);

        map.clear();
        System.out.println(map.isEmpty());
    }
}