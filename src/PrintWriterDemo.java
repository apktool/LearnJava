import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description:
 * PrintWriter
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/17
 */
public class PrintWriterDemo {
    public static void main(String[] argv) throws IOException{
        PrintWriter pw = new PrintWriter("pw.txt");

        pw.write("string");
        pw.print(100);
        pw.print("java");

        pw.close();

        PrintWriter wp = new PrintWriter(new FileWriter("wp.txt"), true);
        wp.println(true);
        wp.println(100);
        wp.println("java");
    }
}
