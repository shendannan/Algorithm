package B1_sort;

public class S02_QuickSort {
    /**
     * 快速排序
     * 基本思想：采用分治法，每次找一个数作为基准，使比它大的或比它小的位于它两侧。
     * O(nlogn)
     */
    public static void quickSort(int[] array, int start, int end) {
        if (start < 0 || end >= array.length || start > end) return;
        int smallIndex = partition(array, start, end);
        if (smallIndex > start)
            quickSort(array, start, smallIndex - 1);
        if (smallIndex < end)
            quickSort(array, smallIndex + 1, end);
    }

    public static int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++)
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex)
                    swap(array, i, smallIndex);
            }
        return smallIndex;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,3,2,5,7,6,8,9,1,0};
        quickSort(array, 0, array.length-1);
        for(int i:array){
            System.out.println(i);
        }
    }
}
