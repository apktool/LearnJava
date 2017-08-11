/**
 * Description:
 * Abstract class
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/11
 */
abstract class Animal {
    public abstract void eat();
}

class Dog extends Animal{
    public void eat(){
        System.out.println("Dog is eatting.");
    }
}

public class AbstractClassDemo {
    public static void main(String[] args){
        Dog d = new Dog();
        d.eat();
    }
}

/**
 * 抽象类中不一定有抽象方法，但是抽象方法的类必须为抽象类
 * 抽象类不能实例化，因为它不是具体的；
 * 抽象类有构造方法，但是不能实例化。用于子类访问父类数据的初始化
 */
