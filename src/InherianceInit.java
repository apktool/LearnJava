/**
 * Description:
 * The initial order to constructor function
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/11
 */

class Father{
    Temp t = new Temp();

    public Father(){
        System.out.print("F ");
    }

    public void show(){
        System.out.print("I am Father");
    }
}

class Son extends Father{
    Temp t = new Temp();

    public Son(){
        System.out.print("S ");
    }

    public void show(){
        super.show();   // 可实现对父类同名方法的扩展
        System.out.print("'s Son");
    }
}

class Temp{
    public Temp(){
        System.out.print("T ");
    }
}


public class InherianceInit {
    public static void main(String[] argv){
        Son s = new Son();
        System.out.println();
        s.show();
    }
}

/**
 * 初始化顺序是： 先初始化父类，再初始化子类；
 *
 * 类的初始化顺序是：
 *     成员变量初始化
 *         默认初始化
 *         显式初始化
 *         构造方法初始化
 */
