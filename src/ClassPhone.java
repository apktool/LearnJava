class ClassDemo{
    String brand;
    String owner;
    int price;

    public void callContact(String name){
        System.out.println("Mr' " + name + " is called.");
    }

    public void phoneInfo(){
        System.out.println(brand + " - " + price + " - " + owner);
    }
}

public class ClassPhone{
    public static void main(String[] args){
        ClassDemo st =  new ClassDemo();

        st.brand = "Apple";
        st.owner = "Mr' Li";
        st.price = 6799;

        st.callContact("Bob");
        st.phoneInfo();
    }
}

/**
 * 成员变量和局部变量的区别
 * 在类中的位置不同
 *     成员变量 类中方法外
 *     局部变量 方法内或者方法声明上
 * 在内存中的位置不同
 *     成员变量 堆内存
 *     局部变量 栈内存
 * 生命周期不同
 *     成员变量 随着对象的存在而存在，随着对象的消失而消失
 *     局部变量 随着方法的调用而存在，随着方法的调用完毕而消失
 * 初始化值不同
 *     成员变量 有默认的初始化值
 *     局部变量 没有默认的初始化值，必须先定义，赋值，才能使用。
 */
