
public class ArrayCopyDemo {
    public static void display(int[] array) {
        for (int item: array) {
            System.out.print(" " + item);
        }
        System.out.println();
    }

    public static void main(String[] argv) {
        int[] org = new int[] {1, 2, 3};
        display(org);

        int[] copy = new int[10];
        System.arraycopy(org, 0, copy, 1, org.length);

        display(copy);

        System.out.println(copy.length);
    }
}
