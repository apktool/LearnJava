/**
 * Description:
 * Regex expression
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/12
 */

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[]  args){
        String str = "haha@163.com.cn";
        String regex = "\\w+@\\w+\\.com";
        boolean flag = str.matches(regex);
        System.out.println(flag);

        String str1 = "91 27 46 38 50";
        String[] strArray = str1.split(" ");
        int[] arr = new int[strArray.length];
        for(int i=0; i<arr.length; i++){
            arr[i] = Integer.parseInt(strArray[i]);
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<arr.length;i++){
            sb.append(arr[i]).append(" ");
        }
        String result = sb.toString().trim();
        System.out.println("result: " + result);

        String regex1 = "\\h";
        String str2 = "-";
        System.out.println("result: " + str1.replaceAll(regex1, str2));

        Pattern p = Pattern.compile("a*b");
        Matcher m = p.matcher("aaaaaab");
        System.out.println(m.matches());

        String str3 = "I just don't want her to go through what I went through with Caral!";
        String regex2 = "\\b\\w{4}\\b";
        Pattern p1 = Pattern.compile(regex2);
        Matcher m1 = p1.matcher(str3);
        while(m1.find()){
            System.out.print(m1.group() + " ");
        }
    }
}
