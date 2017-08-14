import java.util.Set;
import java.util.HashSet;

/**
 * Description:
 * Set
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
    }
}
