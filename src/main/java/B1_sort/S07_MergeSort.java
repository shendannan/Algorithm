package B1_sort;

import java.util.Arrays;

public class S07_MergeSort {
    /**
     * 归并排序
     * 基本思想：采用分治法，将已有序的子序列合并，得到完全有序的序列；
     * 即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
     * O(nlogn)
     */
    public static int[] mergeSort(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 归并过程——将两段排序好的数组结合成一个排序数组
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0;
        for (int index = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,3,2,5,7,6,8,9,1,0};
        array = mergeSort(array);
        for(int i:array){
            System.out.println(i);
        }
    }
}
