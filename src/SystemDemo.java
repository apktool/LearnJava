import java.lang.System;
import java.util.Arrays;

/**
 * Description:
 * System method
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/13
 */

class Parent {
    public void show() {
        System.out.println("I am Parent");
    }

    @Override
    public void finalize() throws Throwable{
        System.out.println("The object will be cleaned: " + this);
        super.finalize();
    }
}

public class SystemDemo {
    public static void main(String[] args){
        Parent pt = new Parent();
        pt.show();
        pt = null;
        System.gc(); //垃圾回收

        System.out.println(System.currentTimeMillis());

        int[] arr1 = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {7, 8, 9, 10, 11};
        System.arraycopy(arr1, 1, arr2, 3, 2);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        System.exit(0);
    }
}
