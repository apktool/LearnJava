/**
 * Description:
 * Created with IntelliJ IDEA.
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/15
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        list.add(30);
        list.add(20);
        list.add(50);
        list.add(10);
        list.add(40);

        System.out.println("list:" + list);

        Collections.sort(list);
        System.out.println("list:" + list);

        System.out.println("binarySearch:" + Collections.binarySearch(list, 30));
        System.out.println("binarySearch:" + Collections.binarySearch(list, 300));

        System.out.println("max:" + Collections.max(list));

        Collections.reverse(list);
        System.out.println("list:" + list);

        Collections.shuffle(list); // random replace
        System.out.println("list:" + list);
    }
}
