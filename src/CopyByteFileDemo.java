import java.io.*;

/**
 * Description:
 * Copy binary file
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/17
 */
public class CopyByteFileDemo {
    public static void main(String[] argv) throws IOException {
        File srcFile = new File("D:\\a.jpg");
        File destFile = new File("D:\\b.jpg");
        method1(srcFile, destFile);
        method2(srcFile, destFile);
        method3(srcFile, destFile);
        method4(srcFile, destFile);
    }

    private static void method1(File srcFile, File destFile) throws IOException {
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        int by = 0;
        while ((by = fis.read()) != -1) {
            fos.write(by);
        }
        fos.close();
        fis.close();
    }

    private static void method2(File srcFile, File destFile) throws IOException {
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        byte[] chs = new byte[1024];
        int len;
        while ((len = fis.read(chs)) != -1) {
            fos.write(chs, 0, len);
        }

        fis.close();
        fos.close();
    }

    private static void method3(File srcFile, File destFile) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));

        int by = 0;
        while ((by = bis.read()) != -1) {
            bos.write(by);
        }

        bis.close();
        bos.close();
    }

    private static void method4(File srcFile, File destFile) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));

        byte[] chs = new byte[1024];
        int len = 0;
        while ((len = bis.read(chs)) != -1) {
            bos.write(chs, 0, len);
        }

        bis.close();
        bos.close();
    }
}
