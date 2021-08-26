package B2_array;

import java.util.ArrayList;

/**
 * 求数组的全部子序列，包括连续的和非连续的，求数组的全排列
 */
public class Subsequence {
    //非连续的所有子序列
    private static ArrayList<ArrayList<Integer>> getSubArray(int[] arr, int length) {
        ArrayList<ArrayList<Integer>> bList = new ArrayList<>();
        int mark = 0;
        int nEnd = 1 << length;
        for (mark = 0; mark < nEnd; mark++) {
            ArrayList<Integer> aList = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                if (((1 << i) & mark) != 0) {
                    aList.add(arr[i]);
                }
            }
            bList.add(aList);
        }
        return bList;
    }

    //连续子序列
    private static void allContinuousSubArray(String str) {
        int i,j,k;
        int num=str.length();
        for (i=0;i<num;i++) {
            for (j=i;j<num;j++) {
                for (k=i;k<=j;k++) {
                    System.out.print(str.charAt(k));
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void swap(int [] arr,int i,int j){
        // 交换函数
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 对数组进行全排列
    public static void perum(int[] arr, int p, int q){
        // for循环将数组中所有的元素和第一个元素进行交换。然后进行全排列。
        // 递归结束条件
        if(p == q){
            //一次递归结束。将整个数组打印出来
            for (int value : arr) {
                System.out.print(value);
            }
            System.out.println();
        }
        for(int i =p ;i <= q;i++){
            swap(arr,i,p);
            // 把剩下的元素都做全排列。
            perum(arr,p+1,q);
            // 然后再交换回去，数组还原，保证下一次不会有重复交换。
            swap(arr,i,p);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        ArrayList<ArrayList<Integer>> list = getSubArray(a, a.length);
        System.out.println("全部子序列——————————");
        //输出列表：
        for (ArrayList<Integer> mList : list) {
            for (Integer integer : mList) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
        System.out.println("连续子序列——————————");
        allContinuousSubArray("123");
        System.out.println("全排列——————————");
        perum(a,0,a.length-1);
    }
}
