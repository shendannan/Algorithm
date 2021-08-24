package C_scenario;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 高楼扔鸡蛋
 * 你面前有一栋从 1到 N 共 N 层的楼，然后给你K个鸡蛋（K至少为 1）。
 * 现在确定这栋楼存在楼层0 <= F <= N，在这层楼将鸡蛋扔下去，鸡蛋恰好没摔碎（高于F的楼层都会碎，低于F的楼层都不会碎）。
 * 最坏情况下，你至少要扔几次鸡蛋，才能确定这个楼层F呢？
 *
 * PS：F 可以为 0，比如说鸡蛋在 1 层都能摔碎，那么 F = 0。
 */
public class EggsThrowing {
    //K个鸡蛋 N层楼
    public int superEggDrop(int K, int N){
        int[][] memo = new int[K+1][N+1];
        for(int[] a:memo){
            Arrays.fill(a,-1);
        }
        return dp(K,N,memo);
    }

    public int dp(int K, int N, int[][] memo){
        //base case
        if(K==1) return N;
        if(N==0) return 0;
        if(memo[K][N] != -1) return memo[K][N];
        int res = Integer.MAX_VALUE;
        //方案1：穷举所有可能的选择
//        for(int i = 1;i <= N;i++){
//            res = Math.min(res, //第i楼不扔
//                    Math.max(
//                    dp(K,N-i,memo),     //没碎
//                    dp(K-1,i-1,memo) //碎了
//            )+1);//在第i楼扔了一次
//        }

        //方案2：二分法加速穷举
        int low = 1, high = N;
        while(low <= high){
            int mid = low + (high - low)/2;
            int broken = dp(K - 1, mid - 1, memo);
            int notBroken = dp(K,N - mid, memo);
            if(broken > notBroken){
                high = mid - 1;
                res = Math.min(res, broken + 1);
            }else{
                low = mid + 1;
                res = Math.min(res ,notBroken + 1);
            }
        }

        memo[K][N] = res;
        return res;
    }

    public static void main(String[] args) {
        EggsThrowing obj = new EggsThrowing();
        System.out.println(obj.superEggDrop(2,50));
    }
}
