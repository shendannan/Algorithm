package B3_string;

import java.util.Arrays;

/**
 * 最长公共子序列
 * 输入: str1 = "abcde", str2 = "ace"
 * 输出: 3
 * 解释: 最长公共子序列是 "ace"，它的长度是 3
 */
public class LengthOfLCS {
    //dp[i][j]的含义是：对于s1[1..i]和s2[1..j]，它们的 LCS 长度是dp[i][j]
    //这里用的带备忘录的dfs方法，用动规更简单，可参考CreatePalindrome
    public int longestCommonSubsequence(String s1,String s2){
        int[][] memo = new int[s1.length()][s2.length()];
        for(int[] i:memo){
            Arrays.fill(i,-1);
        }
        return dfs(s1.length()-1,s2.length()-1,memo,s1,s2);
    }

    public int dfs(int i, int j, int[][] memo, String s1, String s2){
        //base case 一个字符串处理完，不可能再有公共部分
        if(j == -1 || i == -1) return 0;
        if(memo[i][j] != -1) return memo[i][j];
        if(s1.charAt(i) == s2.charAt(j)) {
            //当前位置字符相同，两个指针往前移
            memo[i][j] = dfs(i-1,j-1,memo,s1,s2) + 1;
        }else{
            memo[i][j] = Math.max(dfs(i-1,j,memo,s1,s2),dfs(i,j-1,memo,s1,s2));
        }
        return memo[i][j];
    }

    public static void main(String[] args) {
        LengthOfLCS obj = new LengthOfLCS();
        System.out.println(obj.longestCommonSubsequence("abcdefghij","acehhhs"));
    }
}
