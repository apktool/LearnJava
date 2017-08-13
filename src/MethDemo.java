/**
 * Description:
 * Math and method-related
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/13
 */

import java.lang.Math;

public class MethDemo {
    public static void main(String[] args){
        System.out.println(Math.abs(-10));
        System.out.println(Math.ceil(12.34));
        System.out.println(Math.floor(12.56));
        System.out.println(Math.round(12.34f));
        System.out.println(Math.max(12, 34));
        System.out.println(Math.min(12, 34));
        System.out.println((int)(Math.random()*100) + 1);
        System.out.println(Math.sqrt(16));
    }
}
