package hello;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author apktool
 * @date 2018-09-22 10:09
 */
public class testDemo {
    @BeforeEach
    public void test2() {
        System.out.println("Hello python");
        assert 1 == 2;
    }

    @Test
    public void test() {
        System.out.println("Hello world");
        assert true == true;
    }
}
