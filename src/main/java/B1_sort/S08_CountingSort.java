package B1_sort;

import java.util.Arrays;

public class S08_CountingSort {
    /**
     * 计数排序
     * 基本思想：计数排序不是基于比较的排序算法，其核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
     * 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。适合密集数据
     * O(n+k)
     */

    public static void countingSort(int[] array) {
        if (array.length == 0) return;
        int bias, min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max){
                max = array[i];
            }
            if (array[i] < min){
                min = array[i];
            }
        }
        bias = -min;
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int value : array) {
            bucket[value + bias]++;
        }
        int index = 0, i = 0;
        while (index < array.length) {
            if (bucket[i] != 0) {
                array[index] = i - bias;
                bucket[i]--;
                index++;
            } else{
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,3,2,5,7,6,8,9,1,0};
        countingSort(array);
        for(int i:array){
            System.out.println(i);
        }
    }
}
