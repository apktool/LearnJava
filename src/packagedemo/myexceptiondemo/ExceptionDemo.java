package packagedemo.myexceptiondemo;

/**
 * Description:
 * Created with IntelliJ IDEA.
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/15
 */
public class ExceptionDemo extends Exception {
    public ExceptionDemo(){
        super();
    }

    public ExceptionDemo(String s){
        super(s);
    }
}
