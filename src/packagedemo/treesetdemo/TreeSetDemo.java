/**
 * Description:
 * TreeSet | nature order; comparator order
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/14
 */

package packagedemo.treesetdemo;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<Student> st = new TreeSet<Student>(); // nature order

        Student s1 = new Student("linqingxia", 27);
        Student s2 = new Student("zhangguorong", 29);
        Student s3 = new Student("wanglihong", 23);
        Student s4 = new Student("linqingxia", 27);
        Student s5 = new Student("liushishi", 22);
        Student s6 = new Student("wuqilong", 40);
        Student s7 = new Student("fengqingy", 22);
        Student s8 = new Student("linqingxia", 29);

        st.add(s1);
        st.add(s2);
        st.add(s3);
        st.add(s4);
        st.add(s5);
        st.add(s6);
        st.add(s7);
        st.add(s8);

        for (Student s : st) {
            System.out.println(s.getName() + "<->" + s.getAge());
        }

        System.out.println("-------------------");

        TreeSet<Student> ts = new TreeSet<Student>(new MyComparator()); // comparator order

        Student t1 = new Student("linqingxia", 27);
        Student t2 = new Student("zhangguorong", 29);
        Student t3 = new Student("wanglihong", 23);
        Student t4 = new Student("linqingxia", 27);
        Student t5 = new Student("liushishi", 22);
        Student t6 = new Student("wuqilong", 40);
        Student t7 = new Student("fengqingy", 22);
        Student t8 = new Student("linqingxia", 29);

        ts.add(t1);
        ts.add(t2);
        ts.add(t3);
        ts.add(t4);
        ts.add(t5);
        ts.add(t6);
        ts.add(t7);
        ts.add(t8);

        for (Student t : ts) {
            System.out.println(t.getName() + "<->" + t.getAge());
        }
    }
}
