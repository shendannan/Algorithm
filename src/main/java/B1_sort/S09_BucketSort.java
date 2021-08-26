package B1_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S09_BucketSort {
    /**
     * 桶排序
     * 基本思想：桶排序是计数排序的升级版。假设输入数据服从均匀分布，将数据分到有限数量的桶里，
     * 每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序）。
     * O(n+k)
     */

    public static List<Integer> bucketSort(List<Integer> array, int bucketSize) {
        if (array == null || array.size() < 2)
            return array;
        int max = array.get(0), min = array.get(0);
        // 找到最大值最小值
        for (Integer value : array) {
            if (value > max)
                max = value;
            if (value < min)
                min = value;
        }
        int bucketCount = (max - min) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        ArrayList<Integer> resultArr = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<>());
        }
        for (Integer integer : array) {
            bucketArr.get((integer - min) / bucketSize).add(integer);
        }
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSize == 1) { // 如果带排序数组中有重复数字时  感谢 @见风任然是风 朋友指出错误
                resultArr.addAll(bucketArr.get(i));
            } else {
                if (bucketCount == 1)
                    bucketSize--;
                List<Integer> temp = bucketSort(bucketArr.get(i), bucketSize);
                resultArr.addAll(temp);
            }
        }
        return resultArr;
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(4,3,2,5,7,6,8,9,1,0);
        array = bucketSort(array,5);
        for(int i:array){
            System.out.println(i);
        }
    }
}
