import java.util.LinkedList;

/**
 * Description:
 * LinkList
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/13
 */
public class LinkedListDemo {
    public static void main(String[] args){
        LinkedList link = new LinkedList();

        link.add("Hello");
        link.add("World");

        System.out.println(link);

        link.addFirst("OK"); // link.add("OK")
        link.addLast("!!");
        System.out.println(link);

        System.out.println(link.getFirst());
        System.out.println(link.getLast());

        System.out.println(link.removeFirst());
        System.out.println(link.removeLast());
    }
}
