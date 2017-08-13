/**
 * Description:
 * BigInteger
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/13
 */

import java.math.BigInteger;

public class BigIntegerDemo {
    public static void main(String[] args){
        BigInteger bi1 = new BigInteger("100");
        BigInteger bi2 = new BigInteger("50");

        System.out.println(bi1.add(bi2));
        System.out.println(bi1.subtract(bi2));
        System.out.println(bi1.multiply(bi2));
        System.out.println(bi1.divide(bi2));

        BigInteger[] bis = bi1.divideAndRemainder(bi2);
        System.out.println("quotient: " + bis[0] + "\nreminder: " + bis[1]);
    }
}
