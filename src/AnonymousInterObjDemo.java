/**
 * Description:
 * Anonymous object of function-nested
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/12
 */

interface Inter{
    abstract public void show();
}

class Outer{
    public void method(){
        new Inter(){
            public void show(){
                System.out.println("Hello world");
            }
        }.show();

        // -------------------------------

        Inter i = new Inter() {
            @Override
            public void show() {
                System.out.println("Hello Java.");
            }
        };

        i.show();

    }
}

public class AnonymousInterObjDemo {
    public static void main(String[] args){
        Outer o = new Outer();
        o.method();
    }
}
