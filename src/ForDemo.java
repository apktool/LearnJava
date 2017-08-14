/**
 * Description:
 * foreach function in order to loop array
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/14
 */
import java.util.ArrayList;
import java.util.Iterator;

public class ForDemo {
    public static void main(String[] args){
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};

        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }System.out.println();

        for(int x: arr){
            System.out.print(x + " ");
        }System.out.println();

        String[] arr1 = { "Hello", "World", "Java" };
        for(String x: arr1){
            System.out.print(x + " ");
        }System.out.println();

        System.out.println("------------");

        ArrayList<String> arr2 = new ArrayList<String>();
        arr2.add("Hello");
        arr2.add("Python");

        for(int i=0; i!=arr2.size(); i++){
            System.out.print(arr2.get(i) + " ");
        }System.out.println();

        for(Iterator<String> it=arr2.iterator(); it.hasNext();){
            System.out.print(it.next() + " ");
        }System.out.println();

        for(String x: arr2){
            System.out.print(x + " ");
        }System.out.println();
    }
}
