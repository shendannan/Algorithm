package B3_string;

/**
 * 至少有K个字符的最长子串
 * 给你一个字符串 s 和一个整数 k，找出 s 中的最长子串
 * 要求该子串中的每一字符出现次数都不少于k。返回这一子串的长度。
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由小写英文字母组成
 * 1 <= k <= 105
 * https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/
 */
public class LongestSubstring {
    //分治：对于一个字符串来说，如果要求子串最少出现k次，那么如果某些字母出现的次数小于k,
    //这些字母一定不会出现在最长的子串中，并且这些字母将整个字符子串分割成小段，这些小段有可能是最长的
    //但是由于被分割了，还是要检查这一小段，如果某些字母出现的次数小于k,会将小段继续分割下去,
    //比如字符串"aacbbbdc"，要求最少出现2次,我们记录左右闭区间
    //第一轮[0,7]，处理"aacbbbdc"，d只出现了一次不满足，于是递归解决区间[0,5]、[7,7]
    //第二轮[0,5]，处理"aacbbb"，  c只出现了一次不满足，于是递归解决区间[0,1]、[3,4]
    //第二轮[7,7]，处理"c"，       c只出现了一次不满足，不继续递归
    //第三轮[0,1]，处理"aa"，      满足出现次数>=2,ret=2
    //第三轮[3,4]，处理"bbb"，     满足出现次数>=2 ret=3;
    public int longestSubstring(String s, int k) {
        int n = s.length();
        if(n == 0) return 0;
        return dfs(s, 0, n - 1, k);
    }

    public int dfs(String s, int l, int r, int k) {
        int[] cnt = new int[26];
        //记录每个字母出现的次数
        for (int i = l; i <= r; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        char split = 0;
        //遍历每个字母，如果出现大于0小于K次的，就分割
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char)(i + 'a');
                break;
            }
        }
        //不要忘记特殊情况：如果没有分割则直接返回整个字符串（分治终点）
        if (split == 0) {
            return r - l + 1;
        }

        int i = l;
        int ret = 0;
        while (i <= r) {
            //特殊情况：如果开始位置就是分割点，就直接把开始位置右移
            while (i <= r && s.charAt(i) == split) {
                i++;
            }
            if (i > r) {
                break;
            }
            int start = i;//得到开始位置
            while (i <= r && s.charAt(i) != split) {
                i++;
            }

            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }
}
