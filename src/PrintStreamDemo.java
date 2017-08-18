/**
 * Description:
 * PrintStream
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/17
 */

import java.io.*;

public class PrintStreamDemo {
    public static void main(String[] argv) throws IOException {
        // equal to : System.out.println("Hello world");
        PrintStream ps = System.out;
        ps.println("Hello world");

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        System.out.print("Please input something: ");
        String line = br.readLine();
        System.out.println(line);
    }
}
