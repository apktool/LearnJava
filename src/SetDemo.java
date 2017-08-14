import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Description:
 * Set, TreeSet
 * unique, out-of-order
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/14
 */
public class SetDemo {
    public static void main(String[] args){
        Set<String> set = new HashSet<String>();
        set.add("Hello");
        set.add("world");
        set.add("world");

        for(String x: set){
            System.out.print(x);
        }System.out.println();

        TreeSet<Integer> ts = new TreeSet<Integer>();
        ts.add(8);
        ts.add(6);
        ts.add(7);
        ts.add(6);

        for (Integer it: ts){
            System.out.print(it);
        }System.out.println();
    }
}
