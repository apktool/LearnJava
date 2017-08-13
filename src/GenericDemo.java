/**
 * Description:
 * Created with IntelliJ IDEA.
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/13
 */
import java.util.ArrayList;
import java.util.Iterator;

public class GenericDemo {
    public static void main(String[] args){
        ArrayList<String> array = new ArrayList<String>();
        array.add("Hello");
        array.add("Java");
        // array.add(10);

        for(Iterator<String> it=array.iterator(); it.hasNext();){
            System.out.print(it.next());
        }System.out.println();
    }
}
