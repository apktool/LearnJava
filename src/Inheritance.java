/**
 * Description:
 * Created with IntelliJ IDEA.
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/11
 */

import java.lang.System;

class Person{
    public int age = 10;

    public Person(){
        System.out.println("I am Person constructor");
    }

    public void eat(){
        System.out.println("I am eatting");
    }
    public void sleep(){
        System.out.println("I am sleeping");
    }
}

class students extends Person{
    public students(){
        super();  // 默认会调用该方法，这里只是显式写出来了而已。
        System.out.println("I am students constructor");
    }
    public void study(){
        System.out.println("I am studying");
    }
}

class teather extends Person{
    public void teach(){
        System.out.println("I am teaching");
    }
}

class emploee extends Person{
    public int age = 20;

    public void show(){
        int age = 30;
        System.out.println(age);         // 打印局部变量
        System.out.println(this.age);    // 打印成员变量
        System.out.println(super.age);   // 打印基类变量
    }
}

public class Inheritance{
    public static void main(String[] args){
        students stu = new students();
        stu.eat();
        stu.study();

        teather tth =  new teather();
        tth.sleep();
        tth.teach();

        emploee em = new emploee();
        em.show();
    }
}

/**
 * 子类只能继承父类所有非私有的成员(成员方法和成员变量)
 *     其实这也体现了继承的另一个弊端：打破了封装性
 * 子类不能继承父类的构造方法，但是可以通过super关键字去访问父类构造方法。
 */
