/**
 * Description:
 * Polymorphism
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/11
 */

class Parents{
    public int num = 0;

    public void show(){
        System.out.println("I am Parent's show");
    }
}

class Childs extends Parents{
    public int num = 1;
    public void show(){
        System.out.println("I am Child's show");
    }
    public void method(){
        System.out.println("I am Child's method");
    }
}

public class PolymorphismDemo {
    public static void main(String[] args){
        Parents chd = new Childs();
        System.out.println(chd.num);
        chd.show();
        // chd.method(); // 因为父类中并没有定义method方法，因此这里不能这么使用。可以重新创建子类对象或者使用向下转型

        Childs chd1 = (Childs)(chd); // 向下转型(Downcasting) | 将父类转换为子类
                                      // 向上转型(Upcasting) | 将子类转换为父类
        chd1.method();
    }
}

/**
 *  多态中的成员访问特点
 *      成员变量：编译看左边，运行看左边
 *      构造方法：创建子类对象的时候，访问父类的构造方法，对父类的进行数据初始化
 *      成员方法：编译看左边，运行看右边
 *      静态方法：编译看左边，运行看左边
 */
