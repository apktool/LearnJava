package packagedemo.myexceptiondemo;

/**
 * Description:
 * Created with IntelliJ IDEA.
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/15
 */
public class TestDemo {
    public static void main(String[] args){
        MyExceptionDemo a = new MyExceptionDemo();
        try {
            a.check(120);
        }catch (ExceptionDemo e){
            System.out.println("Hello world");
        }
    }
}
