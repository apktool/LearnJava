public class Temp {
    public static void main(String[] argv){
        for(int i=0; i!=10; i++){
            for(int j=1; j!=i+1; j++){
                System.out.print(i+"*"+j+'='+i*j+'\t');
            }
            System.out.println();
        }
    }
}
