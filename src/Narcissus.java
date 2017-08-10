public class Narcissus {
    public static void main(String[] args){
        System.out.println("The number of narcissus is :");
        for(int number=100; number<1000; number++){
            int unitsPlace=number%10;
            int tensPlace=number/10%10;
            int hundredPlace=number/10/10%10;

            if(number == unitsPlace*unitsPlace*unitsPlace+tensPlace*tensPlace*tensPlace+hundredPlace*hundredPlace*hundredPlace){
                System.out.println(number);
            }
        }
    }
}
