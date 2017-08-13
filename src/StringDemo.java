/**
 * Description:
 * String and method
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/12
 */

public class StringDemo {
    public static void main(String[] args){
        byte[] bys = { 97, 98, 99, 100};
        System.out.println(new String(bys));

        char[] chr = {'a', 'b', 'c', 'd'};
        System.out.println(new String(chr));

        String str = "Hello world";
        System.out.println(str.charAt(0));

        byte[] btr = str.getBytes();
        for(int i=0; i!=str.length(); i++){
            System.out.print(btr[i]);
        }
        System.out.println();

        char[] str1 = str.toCharArray();
        for(int i=0; i!=str.length(); i++){
            System.out.print(str1[i]);
        }

    }
}
