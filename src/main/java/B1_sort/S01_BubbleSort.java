package B1_sort;

public class S01_BubbleSort {
    /**
     * 冒泡排序
     * 基本思想：两个数比较大小，较大的数下沉，较小的数冒起来。
     * O(n^2)
     */
    public static void bubbleSort(int[] array) {
        if (array.length == 0) return;
        for (int i = 0; i < array.length; i++){
            for(int j = 0;j < array.length-1-i;j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,3,2,5,7,6,8,9,1,0};
        bubbleSort(array);
        for(int i:array){
            System.out.println(i);
        }
    }
}
