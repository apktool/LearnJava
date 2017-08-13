
/**
 * Description:
 * BigDecimal
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/13
 */

import java.math.BigDecimal;

public class BigDecimalDemo {
    public static void main(String[] args){
        System.out.println(0.09 + 0.01);
        System.out.println(1.0 - 0.32);
        System.out.println(1.015 * 100);
        System.out.println(1.301 / 100);

        /*
            上述计算结果与正确值之间存在误差，原因是因为浮点数的存储所致
            因此可以使用下面的方法来保证精度
         */

        BigDecimal bd1 = new BigDecimal("0.09");
        BigDecimal bd2 = new BigDecimal("0.01");
        System.out.println(bd1.add(bd2));

        BigDecimal bd3 = new BigDecimal("1.00");
        BigDecimal bd4 = new BigDecimal("0.32");
        System.out.println(bd3.subtract(bd4));

        BigDecimal bd5 = new BigDecimal("1.015");
        BigDecimal bd6 = new BigDecimal("100");
        System.out.println(bd5.multiply(bd6));

        BigDecimal bd7 = new BigDecimal("1.301");
        BigDecimal bd8 = new BigDecimal("100");
        System.out.println(bd7.divide(bd8));
        System.out.println(bd7.divide(bd8, 3, BigDecimal.ROUND_HALF_UP));
    }
}
