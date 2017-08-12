/**
 * Description:
 * Package Demo, class-nested
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/12
 */
package pack.demo;

class Outer{
    public class Inter{
        void show(){
            System.out.println("I am Inter class");
        }
    }

    public static class Inten{
        void show(){
            System.out.println("I am Inten class");
        }
    }

    private int age = 0;
}

public class PackageDemo {
    public static void main(String[] args){
        Outer.Inter oi1 = new Outer().new Inter();
        oi1.show();

        Outer.Inten oi2 = new Outer.Inten();
        oi2.show();
    }
}

/**
 * javac -d . PackageDemo.java
 * java pack.demo.PackageDemo
 */
