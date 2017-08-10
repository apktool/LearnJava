/*
    算数运算符: + - * / % ++ --
    赋值运算符: = += -= *= /= %=
    比较运算符: == != > >= < <=
    逻辑运算符: & | ^ ! && ||
    位运算符:   << >> >>> & | ^ ~
    三目运算符: a+b?a:b
 */
public class Operator {
    static public void main(String[] args){
        int a=1;
        int b=2;
        System.out.println(a/b);
        System.out.println(a*1.0/b); // 为了得到准确的小数而不仅仅是取商

        int x=4;
        int y=(x++)+(++x)+(x*10); // 注意这里'++'的运算  | y=70
        System.out.println(y);

        /* + 的用法：加法；正号；连接字符串
           a+=b 的运算不是简单的等于 a=a+b 这里面还包含了一次强制类型转换
        */
        short s=1;
        // s=s+1; //这句会报错，但是下面的不会，因此+=隐式的进行了一次强制类型转换
        s+=1; // 等价于 s=(short)(s+1)
        System.out.println(s);
    }
}