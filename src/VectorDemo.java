/**
 * Description:
 * Vector
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/13
 */

import java.util.Enumeration;
import java.util.Vector;

public class VectorDemo {
    public static void main(String[] args){
        Vector v = new Vector();

        v.addElement("hello");
        v.addElement("world");

        for(int i=0; i<v.size(); i++) {
            String s = (String) v.elementAt(i);
            System.out.print(s);
        }System.out.println();

        Enumeration en = v.elements();
        while(en.hasMoreElements()){
            String s = (String) en.nextElement();
            System.out.print(s);
        }System.out.println();
    }
}
