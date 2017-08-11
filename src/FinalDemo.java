/**
 * Description:
 * final keyword
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/11
 */

// final class Parents{  // 因为这里有final修饰，因此下面的Child子类不能从这里派生
class Parents{
    public Parents(){
        System.out.println("I am parents constructor");
    }
}

class Child extends Parents{
    public Child(){
        System.out.println("I am child constructor");
    }

    public int test = 0;
}


public class FinalDemo {
    public static void main(String[] args){
        Child cd  = new Child();

        final int a = 999;
        System.out.println(a);

        // a = 8; // 因为前面的变量a已经被final修饰，并且已经初始化，因此这里不能再次赋值

        final Child ch = new Child();  // 这里虽然已经被final修饰，但是因为修饰的是类对象，故其对象中的成员变量的值是可以变化的。
        ch.test = 666;

        // ch = cd; // 因为ch已经被final修饰，其对象的地址值是不能变化的，故这里会报错
    }
}

/**
 * final
 *     可以修饰类，则该类不能被继承
 *     可以修饰方法，则该方法不能被重写
 *     可以修饰变量，则该变量不能被重新赋值
 *         但是如果被final修饰的变量没有被初始化，则后面可以仅一次通过赋值的方式对其初始化一次
 *
 * final 修饰局部变量的问题
 *     基本类型：基本类型的值不能发生改变
 *     引用类型：引用类型的地址值不能发生改变，但是该对象的堆内存的值是可以改变的。
 */
