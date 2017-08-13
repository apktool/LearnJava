/**
 * Description:
 * Collection
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/13
 */

import java.lang.Object;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public class CollectionDemo {
    public static void main(String[] args){
        Collection c = new ArrayList();
        c.add("hello");
        c.add("world");
        System.out.println(c);

        c.remove("world");
        System.out.println(c);

        System.out.println(c.contains("hello"));

        c.clear();
        System.out.println(c);

        System.out.println(c.isEmpty());
        System.out.println(c.size());

        System.out.println("------------------");

        Collection c1 = new ArrayList();
        c1.add("abc1");
        Collection c2 = new ArrayList();
        c2.add("abc2");
        c1.addAll(c2);
        System.out.println(c1);
        System.out.println(c2);

        System.out.println("-->| " + c1.containsAll(c2));
        System.out.println(c1);
        System.out.println(c2);

        System.out.println("--->| " + c1.retainAll(c2));
        System.out.println(c1);
        System.out.println(c2);

        System.out.println("->| " + c1.removeAll(c2));
        System.out.println(c1);
        System.out.println(c2);

        System.out.println("------------------");
        // iterate through container
        Collection c3 = new ArrayList();
        c3.add("Hello");
        c3.add("Java");
        Object[] objs = c3.toArray();
        for(int i=0; i<objs.length; i++){
            String s = (String) objs[i];
            System.out.println(s + " ->| " + s.length());
        }

        System.out.println("------------------");
        // iterate through container
        Collection c4 = new ArrayList();
        c4.add("Hello");
        c4.add("Python");
        for(Iterator it = c4.iterator(); it.hasNext(); ){
            System.out.print(it.next() + " ");
        }
    }
}
