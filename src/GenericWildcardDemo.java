/**
 * Description:
 * Generic wildcard
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/14
 */

import java.util.ArrayList;
import java.util.Collection;

class Animal{
    public void show(){
        System.out.println("Hello Animal");
    }
}

class Dog extends Animal {
    public void show(){}
}

class Cat extends Animal {
    public void show(){}
}

public class GenericWildcardDemo {
    Collection<Object> c1 = new ArrayList<Object>();

    Collection<?> c2 = new ArrayList<Object>();
    Collection<?> c3 = new ArrayList<Animal>();
    Collection<?> c4 = new ArrayList<Dog>();
    Collection<?> c5 = new ArrayList<Cat>();

    Collection<? extends Animal> c6 = new ArrayList<Animal>();
    Collection<? extends Animal> c7 = new ArrayList<Dog>();
    Collection<? extends Animal> c8 = new ArrayList<Cat>();

    Collection<? super  Animal> c10 = new ArrayList<Object>();
    Collection<? super  Animal> c11 = new ArrayList<Animal>();
}
