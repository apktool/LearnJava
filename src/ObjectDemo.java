/**
 * Description:
 * Object
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/12
 */

class Student extends Object{
    @Override
    public String toString() {
        // return super.toString();
        return "Member various";
    }
}

public class ObjectDemo {
    public static void main(String[] args){
        Student std = new Student();
        System.out.println(std.hashCode());
        System.out.println(std.getClass().getName());
        System.out.println(std.toString());
        // std.toString() 等价于 std.getClass().getName() + '@' + Integer.toHexString(hasCode())
    }
}
