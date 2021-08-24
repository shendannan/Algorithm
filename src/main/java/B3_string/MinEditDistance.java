package B3_string;

import java.util.Arrays;

/**
 * 最小编辑距离
 * 给我们两个字符串s1和s2，只能用三种操作（增加一个字符、删除一个字符、替换一个字符），
 * 让我们把s1变成s2，求最少的操作数。
 * 输入s1 = horse s2 = ros
 * 输出 3
 *
 * 扩展：如果需要记录具体操作，则dp函数的返回值自定义一个类，增加一个int代表操作，然后根据最小编辑距离对应的操作逆推
 */
public class MinEditDistance {
    public int minDistance(String s1, String s2){
        int[][] memo = new int[s1.length()][s2.length()];
        for(int[] i:memo){
            Arrays.fill(i,-1);
        }
        return dp(s1.length()-1,s2.length()-1,memo,s1,s2);
    }

    public int dp(int i, int j, int[][] memo, String s1, String s2){
        //base case 一个字符串处理完，剩余字符都要挨个增加上去，操作数为剩余字符串的字符数
        if(j == -1) return i + 1;
        if(i == -1) return j + 1;
        if(memo[i][j] != -1) return memo[i][j];
        if(s1.charAt(i) == s2.charAt(j)) {
            //当前位置字符相同，啥都不做，两个指针往前移
            memo[i][j] = dp(i-1,j-1,memo,s1,s2);
        }else{
            int add = dp(i,j-1,memo,s1,s2)+1;
            int delete = dp(i-1,j,memo,s1,s2)+1;
            int replace = dp(i-1,j-1,memo,s1,s2)+1;
            memo[i][j] = Math.min(Math.min(add,delete),replace);
        }
        return memo[i][j];
    }

    public static void main(String[] args) {
        MinEditDistance obj = new MinEditDistance();
        System.out.println(obj.minDistance("horse","ros"));
    }
}
