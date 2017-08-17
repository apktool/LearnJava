/**
 * Description:
 * DataStream
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/17
 */

import java.io.*;

public class DataStreamDemo {
    public static void main(String[] argv) throws IOException{
        write();
        read();
    }

    private static void read() throws IOException{
        DataInputStream dis = new DataInputStream(
                new FileInputStream("dos.txt")
        );

        byte b = dis.readByte();
        short s = dis.readShort();
        int i = dis.readInt();
        long l = dis.readLong();
        float f =dis.readFloat();
        double d = dis.readDouble();
        char c =dis.readChar();
        boolean bb = dis.readBoolean();

        System.out.println(b);
        System.out.println(s);
        System.out.println(i);
        System.out.println(l);
        System.out.println(f);
        System.out.println(d);
        System.out.println(c);
        System.out.println(bb);
    }

    private static void write() throws IOException{
        DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("dos.txt")
        );

        dos.writeByte(10);
        dos.writeShort(100);
        dos.writeInt(1000);
        dos.writeLong(10000);
        dos.writeFloat(12.34F);
        dos.writeDouble(12.56);
        dos.writeChar('a');
        dos.writeBoolean(true);
    }
}
