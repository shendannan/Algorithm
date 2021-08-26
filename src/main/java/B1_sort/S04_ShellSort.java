package B1_sort;

public class S04_ShellSort {
    /**
     * 基本思想：希尔排序是一种改良版插入排序，也称为缩小增量排序。
     * 它与插入排序的不同之处在于，它会优先比较距离较远的元素。
     * O(n^1.3)
     */
    public static void ShellSort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            //静态定义间隔序列
            gap /= 2;
            //也可以动态定义间隔序列
//            while (gap < len / 3) {
//                gap = gap * 3 + 1;
//            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,3,2,5,7,6,8,9,1,0};
        ShellSort(array);
        for(int i:array){
            System.out.println(i);
        }
    }
}
