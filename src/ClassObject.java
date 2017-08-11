class Student{
    private int age; //私有成员

    public void setAge(int age){
        if( age < 0 || age > 120) {
            System.out.println("Out of Age!");
        }else{
            this.age=age;
        }
    }

    public void showAge(){
        System.out.println(age);
    }

    public void show(){
        System.out.println("Hello world");
    }
}

public class ClassObject {
    public static void main(String[] args){
        Student st = new Student();
        st.show();
        new Student().show(); //匿名对象

        st.setAge(15);
        st.showAge();
    }
}

/**
 * 匿名对象：就是没有名字的对象。
 * 匿名对象的两种使用情况
 *   对象调用方法仅仅一次的时候
 *   作为实际参数传递
 */
