/**
 * Description:
 * Generic class
 * Generic function
 * Generic interface
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/14
 */

package packagedemo.genericdemo;

public class ObjectDemo {
    public static void main(String[] args) {
        ObjectTool1<String> ot1 = new ObjectTool1<String>();
        ot1.show("Hello world");

        ObjectTool1<Boolean> ot2 = new ObjectTool1<Boolean>();
        ot2.show(true);

        ObjectTool1<Integer> ot3 = new ObjectTool1<Integer>();
        ot3.show(666);

        System.out.println("-----------------------------");

        ObjectTool2 ot4 = new ObjectTool2();
        ot4.show("Hello Java");

        ObjectTool2 ot5 = new ObjectTool2();
        ot5.show(false);

        ObjectTool2 ot6 = new ObjectTool2();
        ot6.show(999);

        System.out.println("-----------------------------");

        ObjectTool3<String> ot7 = new ObjectTool3<String>();
        ot7.show("Hello Python");

        ObjectTool3<Boolean> ot8 = new ObjectTool3<Boolean>();
        ot8.show(true);

        ObjectTool3<Integer> ot9 = new ObjectTool3<Integer>();
        ot9.show(888);
    }
}
