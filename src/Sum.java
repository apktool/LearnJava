import java.util.Scanner;

public class Sum {
    public static void main(String[] args){
        int a=1, b=2;
        int c=sum(a,b);
        System.out.println(c);

        int x, y;
        Scanner sc=new Scanner(System.in);
        x=sc.nextInt();
        y=sc.nextInt();
        c=max(x, y);
        System.out.println(c);
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int max(int a, int b){
        return a>b?a:b;
    }
}