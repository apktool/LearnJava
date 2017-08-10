public class ArrayDemo {
    public static void main(String[] argv){
        int[]  array = new int[3];

        System.out.println(array);
        System.out.print(array[0]+","+array[1]+","+array[2]+"\n");

        System.out.println("-----------------------------");

        int [] array1=new int[]{1,2,3};
        // int [] array1=new int[3]{1,2,3}; // 本写法错误
        System.out.print(array1[0]+","+array1[1]+","+array1[2]+"\n");

        System.out.println("-----------------------------");

        int [] array2={3,2,1};
        System.out.print(array2[0]+","+array2[1]+","+array2[2]+"\n");

        System.out.println("-----------------------------");

        int [][] array4=new int[4][];
        array4[0]=new int[2];
        array4[1]=new int[3];
        array4[2]=new int[4];
        array4[3]=new int[5];

        /** 上述数组大概是下面的这个样子
            0 0
            0 0 0
            0 0 0 0
            0 0 0 0 0
         */

        for(int i=0; i!=4; i++){
            for(int j=0; j!=i+2; j++){ // 加入array4 并不是上述的样子，那么将这里的2改成3应该不会报错，但是改了之后的确会报错，故可认为数组就是上述的样子
                System.out.print(array4[i][j]+"\t");
            }
            System.out.println();
        }

        // 上述代码也可以使用Java提供的方法来获取数组长度

        for(int i=0; i!=array4.length; i++){
            for(int j=0; j!=array4[i].length; j++){
                System.out.print(array4[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
