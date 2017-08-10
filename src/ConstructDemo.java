class Students {
    public Students(){ // 构造方法
        this.name = "defulat";
        this.age = 999;
    }

    public Students(String name, int age){ // 构造方法重载
        this.name = name;
        this.age = age;
    }

    public void showStuInfo(){
        System.out.println(this.name + " ->|<- " + this.age);
    }

    private String name;
    private int age;
}

public class ConstructDemo {
    public static void main(String[] args){
        /*
            首先把Students.class文件加载到内存
            把栈内存给st变量开辟一个空间
            再堆内存中为对象申请一个空间
            给成员变量进行默认初始化  null; 0;
            给成员变量进行显式初始化  apktool; 23;
            通过构造方法给成员变量进行初始化
            数据初始化完毕，然后把堆内存的地址值赋值给栈内存的st变量
         */
        Students st = new Students();
        st.showStuInfo();

        Students st1 = new Students("apktool", 23);
        st1.showStuInfo();
    }
}

/*
    静态变量和成员变量的区别
    所属不同
        静态变量属于类，所以也称为为类变量
        成员变量属于对象，所以也称为实例变量(对象变量)
    内存中位置不同
        静态变量存储于方法区的静态区
        成员变量存储于堆内存
    内存出现时间不同
        静态变量随着类的加载而加载，随着类的消失而消失
        成员变量随着对象的创建而存在，随着对象的消失而消失
    调用不同
        静态变量可以通过类名调用，也可以通过对象调用
        成员变量只能通过对象名调用
 */
