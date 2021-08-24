import java.util.Arrays;

public class Test03 {
    /**
     * 给你一个 严格升序排列 的正整数数组 arr 和一个整数 k 。
     *
     * 请你找到这个数组里从第1个数开始的第 k 个缺失的正整数。
     * 输入arr = [2,3,4,7,11], k = 5
     * 输出：10
     */
    public static int findKthPositive(int[] arr, int k) {
        if(arr.length == 0) return k;
        int cur = arr[0];
        int index = 0;//数组下标
        int miss = -1;
        for(int i = 0;i < k;cur++){
            if(index > arr.length-1){
                //数组下标超限
                miss = cur;
                i++;
                continue;
            }
            if(cur == arr[index]){
                //数组中存在，移动数组下标
                index++;
            }else{
                //数组不存在，更新缺失的数
                miss = cur;
                i++;
            }
        }
        return miss;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,3,4,7,11};
        System.out.println(findKthPositive(arr,5));
    }

    /**
     * 阿里笔试题汇总
     * 3.6  1411（网格涂色）  815（公交换乘）
     * 3.8  1539（第k个缺失整数）  879（背包问题变体）
     * 3.10 走矩阵迷宫（模拟）  1388（分3n块披萨）

     */
}
