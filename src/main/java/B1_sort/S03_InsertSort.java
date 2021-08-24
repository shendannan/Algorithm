package B1_sort;

public class S03_InsertSort {
    /**
     * 插入排序
     * 基本思想：在要排序的一组数中，假定前n-1个数已经排好序，
     * 现在将第n个数插到前面的有序数列中，使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
     * O(n^2)
     */
    public static void insertionSort(int[] array) {
        if (array.length == 0) return;
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,3,2,5,7,6,8,9,1,0};
        insertionSort(array);
        for(int i:array){
            System.out.println(i);
        }
    }
}
