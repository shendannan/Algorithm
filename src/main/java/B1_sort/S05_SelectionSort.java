package B1_sort;

public class S05_SelectionSort {
    /**
     * 选择排序
     * 基本思想：每次找到最小元素，存放到排序序列的起始位置
     * O(n^2)
     */
    public static void selectionSort(int[] array) {
        if (array.length == 0) return;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) //找到最小的数
                    minIndex = j; //将最小数的索引保存
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,3,2,5,7,6,8,9,1,0};
        selectionSort(array);
        for(int i:array){
            System.out.println(i);
        }
    }
}
