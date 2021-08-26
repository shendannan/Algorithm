package B3_string;

import java.util.*;

/**
 * 给定一个字符串s，你可以从中删除一些字符，使得剩下的串是一个回文串。如何删除才能使得回文串最长呢？
 * 输出需要删除的字符个数。
 * 例如输入google，输出2，即删除le
 */
public class CreatePalindrome {
    //求原字符串和其反串的最大公共子序列（不是子串，因为可以不连续）的长度（动态规划）
    // 然后用原字符串的长度减去这个最大公共子序列（LCS）的长度就得到了最小编辑长度。
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.next();
            String s2 = new StringBuilder(s1).reverse().toString();
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];//最长公共子序列的长度
            for (int i = 1; i <= s1.length(); i++ ) {
                for (int j = 1; j <= s2.length(); j++ ) {
                    dp[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1) ?
                            dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            System.out.println(s1.length() - dp[s1.length()][s2.length()]);
        }
    }
}

