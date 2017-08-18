/**
 * Description:
 * RandomAccessFile
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/18
 */

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    public static void main(String[] argv) throws IOException{
        write();
        read();
    }

    private static void read() throws IOException{
        RandomAccessFile raf = new RandomAccessFile("raf.txt", "rw");
        int i =raf.readInt();
        System.out.println(i);
        System.out.println("Pointer position is " + raf.getFilePointer());

        char ch = raf.readChar();
        System.out.println(ch);
        System.out.println("Pointer position is " + raf.getFilePointer());

        String s = raf.readUTF();
        System.out.println(s);
        System.out.println("Pointer position is " + raf.getFilePointer());

        raf.seek(4);;
        ch = raf.readChar();
        System.out.println(ch);
    }

    private static void write() throws IOException{
        RandomAccessFile raf = new RandomAccessFile("raf.txt", "rw");
        raf.writeInt(100);
        raf.writeChar('a');
        raf.writeUTF("中国");
    }
}
