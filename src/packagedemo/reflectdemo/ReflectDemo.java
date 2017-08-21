/**
 * Description:
 * reflect
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/20
 */
package packagedemo.reflectdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Person p = new Person();
        Class c = p.getClass();

        Person p2 = new Person();
        Class c2 = p.getClass();

        System.out.println(p == p2);
        System.out.println(c == c2);

        Class c3 = Person.class;
        System.out.println(c == c3);

        Class c4 = Class.forName("packagedemo.reflectdemo.Person");
        System.out.println(c == c4);

        System.out.println("--------------");

        // Constructor[] cons = c4.getConstructors(); //get all public constructor method
        Constructor[] cons = c4.getDeclaredConstructors(); //get all constructor method
        for (Constructor con : cons) {
            System.out.println(con);
        }

        System.out.println("--------------");

        Constructor conn = c.getConstructor();
        Object obj = conn.newInstance();
        System.out.println(obj);

        System.out.println("--------------");

        /*
        Constructor conn1 = c.getConstructor(String.class, int.class, String.class);
        Object obj1 = conn1.newInstance("Li", 27, "北京");
        System.out.println(obj1);
        */
    }
}
