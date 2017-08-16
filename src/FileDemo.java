/**
 * Description:
 * File or Directory | create, delete, judge, ergodic
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/16
 */

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileDemo {
    public static void main(String[] args) {
        File dir = new File("D:\\test");
        System.out.println(dir.mkdir());

        File dirs = new File("D:\\demo\\test");
        System.out.println(dirs.mkdirs());
        System.out.println(dirs.delete());

        File file = new File("D:\\test\\a.out");
        try {
            System.out.println(file.createNewFile());
        } catch (IOException e) {
            e.getMessage();
        }

        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println(file.exists());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        System.out.println(file.isHidden());

        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.length());
        System.out.println(file.lastModified());

        Date d = new Date(file.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Last modified time: " + sdf.format(d));
        System.out.println(file.renameTo(new File("D:\\test\\b.out")));

        File fle = new File("D:\\");
        String[] strArray = fle.list();
        for (String s : strArray) {
            System.out.println(s);
        }

        File[] fileArray = fle.listFiles();
        for(File f: fileArray){
            System.out.println(f.getName());
        }

    }
}
