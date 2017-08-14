/**
 * Description:
 * TreeSet
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/14
 */

package packagedemo.treesetdemo;

public class Student implements Comparable<Student> {
    private String name;
    private int age;

    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Student s) {
        int num = this.name.length() - s.name.length();
        int num2 = num == 0 ? this.name.compareTo(s.name) : num;
        int num3 = num2 == 0 ? this.age - s.age : num2;
        return num3;
    }
}
