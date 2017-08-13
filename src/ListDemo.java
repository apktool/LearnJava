/**
 * Description:
 * list
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/13
 */

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.ListIterator;

public class ListDemo {
    public static void main(String[] args){
        List list = new ArrayList();

        list.add("hello");
        list.add("world");

        System.out.println(list.get(0));

        // iterator through the list
        for(Iterator it = list.iterator(); it.hasNext();){
            System.out.print(it.next());
        } System.out.println();

        for(ListIterator it = list.listIterator(); it.hasNext();) {
            System.out.print((String)it.next());
        } System.out.println();

        for(int i = 0; i < list.size(); i++) {
            System.out.print((String) list.get(i));
        } System.out.println();

        System.out.println(list.remove(1));
    }
}
