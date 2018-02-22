import java.util.Arrays;

public class ArrayCopyOfDemo {
    public static void display(int[] array) {
        for(int item: array) {
            System.out.print(" " + item);
        }
        System.out.println();
    }

    public static void main(String[] argv) {
        int[] org = new int[] {1, 2, 3};
        display(org);

        int[] copy = Arrays.copyOf(org, 5);
        display(copy);

        copy = Arrays.copyOf(copy, 10);
        display(copy);
    }

}
