import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        sortInsertion(generateArray());
        sortSelection(generateArray());
        sortBubble(generateArray());

    }

    public static int[] generateArray() {
        int[] array = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int) (Math.random() * 20000) - 10000);
        }
        return array;
    }

    public static void sortInsertion(int[] arr) {
        System.out.println("sortInsertion");
        long start = System.currentTimeMillis();
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void sortSelection(int[] arr) {
        System.out.println("sortSelection");
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void sortBubble(int[] arr) {
        System.out.println("sortBubble");
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

}