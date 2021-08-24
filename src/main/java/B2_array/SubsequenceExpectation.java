package B2_array;
import java.util.*;
/**
 * 连续子序列最大值的期望
 * 序列arr={a1,a2,…,an}的所有连续子序列最大值的期望是多少？（结果保留小数点后6位有效数字）
 * 注意是连续子序列，序列是有原列表中连续的元素组成。
 * 即求每个序列最大值的平均值
 *
 * 输入：
 * 3　　　　 //表示序列的个数
 * 1 2 3　　//表示序列的值
 * 输出：
 * 2.333333
 * 分析：
 * 所有连续子序列：1 2 3 (1 2) (2 3) (1 2 3)
 * 期望=(1+2+3+2+3+3) / 6 = 14/6 = 2.333333
 */
public class SubsequenceExpectation {
    //思路：动态规划
    //  使用map保留前n项所有子序列集合的最大值(key)和出现次数(value)，3在第n+1项时将map中的key与a[n+1]比较
    //1.若key比a[n+1]大则计算key与value的乘积，累加到result结果中
    //2.若key比a[n+1]小则将key对应的value值加到a[n+1]对应的value值上，并清空原来key对应的值
    //3.最终将a[n+1]乘上对应的value，累加到result结果中
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int all = n * (n + 1) / 2;
        double result = 0;

        //保留最大值，以及最大值出现的次数
        HashMap<Integer, Integer> maxValue = new HashMap<>(n);

        for (int i = 0; i < n; i++) {
            if (maxValue.containsKey(a[i])) {
                maxValue.put(a[i], maxValue.get(a[i]) + 1);
            } else {
                maxValue.put(a[i], 1);
            }

            int count = maxValue.get(a[i]);

            for (Integer key : maxValue.keySet()) {

                if (key > a[i]) {   //之前子序列最大值大于a[i]，则子序列最大值不变
                    result += key * maxValue.get(key);
                } else if (key < a[i]) {  //之前子序列最大值小于a[i]，则将原来子序列的值加到a[i]为key的值上，并清空原来子序列值
                    count += maxValue.get(key);
                    maxValue.put(a[i], count);
                    maxValue.put(key, 0);
                }
            }
            //最后将以a[i]为最大值的子序列个数乘以a[i]
            result += a[i] * count;
        }

        //求期望并调整有效位
        result /= all;
        System.out.printf("%.6f\n",result);
    }
}
