/**
 * Description:
 * Interface
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/11
 */

interface AnimalTrain{
    public abstract void jump();
}

// 抽象类实现接口，但意义不大
abstract class Dog implements AnimalTrain{}

// class Cat extends Object implements AnimalTrain{} 本格式即下述格式
class Cat implements AnimalTrain{
    public void jump(){
        System.out.println("Cat try to jump");
    }
}

public class InterfaceDemo {
    public static void main(String[] args){
        AnimalTrain ct = new Cat();
        ct.jump();
    }
}

/**
 * 类实现用implements表示；
 * 接口不能直接实例化，但是可以使用多态的方式来实现实例化
 * 接口没有构造方法
 * 接口中的方法只能是抽象方法
 *
 * 多态类型：
 *     具体类多态
 *     抽象类多态
 *     接口多态
 */
