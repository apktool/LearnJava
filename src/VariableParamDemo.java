/**
 * Description:
 * Function with variable parameter
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/14
 */
public class VariableParamDemo {
    public static void main(String[] args){
        int result;
        result = sum(1, 2, 3);
        System.out.println(result);

        result = sum(1, 2, 3, 4, 5);
        System.out.println(result);
    }

    public static int sum(int... num){
        int s = 0;
        for(int x: num){
            s+=x;
        }
        return s;
    }
}
