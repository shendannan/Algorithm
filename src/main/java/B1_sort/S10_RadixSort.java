package B1_sort;

import java.util.ArrayList;
import java.util.List;

public class S10_RadixSort {
    /**
     * 基数排序
     * 基本思想：基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。
     * 有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。
     * 最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。
     * O(nk)
     */

    public static void radixSort(int[] array) {
        if (array == null || array.length < 2) return;
        // 先算出最大数的位数；
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div = 1;
        List<ArrayList<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            bucketList.add(new ArrayList<>());
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int value : array) {
                int num = (value % mod) / div;
                bucketList.get(num).add(value);
            }
            int index = 0;
            for (ArrayList<Integer> integers : bucketList) {
                for (Integer integer : integers){
                    array[index++] = integer;
                }
                integers.clear();
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,3,2,5,7,6,8,9,1,0};
        radixSort(array);
        for(int i:array){
            System.out.println(i);
        }
    }
}
