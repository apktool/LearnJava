package packagedemo.myexceptiondemo;

/**
 * Description:
 * Created with IntelliJ IDEA.
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/15
 */
public class MyExceptionDemo {
    public void check(int score) throws ExceptionDemo{
        if (score > 100 || score < 0){
            throw new ExceptionDemo();
        }else{
            System.out.println("The score is normal!");
        }
    }
}
