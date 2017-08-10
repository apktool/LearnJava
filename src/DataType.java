public class DataType {
    public static void main(String[] args){
        byte a=10;
        System.out.println(a);

        int b=10;
        System.out.println(b);

        long c=9999999L;
        System.out.println(c);

        float f=12.345F;
        System.out.println(f);

        double d=12.345;
        System.out.println(d);

        boolean flag=true;
        System.out.println(flag);

        char str='a';
        System.out.println(str);

        byte z=1;
        int x=2;
        int v=z+x;
        System.out.println(v);

        char s='a';
        System.out.println(s+1);

        System.out.println("Hello world " + " Hello python" + "\n");

        System.out.println("Hello " +'a' + 1); // 字符串拼接
        System.out.println('a' + 1 + "Hello"); // 先进行加法运算，再进行字符串拼接
    }
}
