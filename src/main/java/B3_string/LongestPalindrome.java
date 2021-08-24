package B3_string;

/**
 * 找到给定字符串的最长回文子串
 * 输入：s = "babad"
 * 输出："bab"或"aba"
 * 输入：s = "cbbd"
 * 输出："bb"
 * 输入：s = "a"
 * 输出："a"
 * 输入：s = "ac"
 * 输出："a"或"c"
 *
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        //保存起止位置索引
        int[] range = new int[2];
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            //把回文看成中间的部分全是同一字符，左右部分相对称，i当做回文中心的起点
            //思路是从中间向外扩散来判断，而不是传统的左右指针从两侧到中间判断
            i = findLongest(chars, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    public static int findLongest(char[] str, int left, int[] range) {
        //查找中间部分，这是因为回文中心可能为1个或2个重复的字符
        int right = left;
        while (right < str.length - 1 && str[right + 1] == str[left]) {
            //如果中间部分字符一样则跳过
            right++;
        }
        //定位中间部分的最后一个字符，此时是最短回文，因为字符一样
        int ans = right;
        //从中间向左右扩散
        while (left > 0 && right < str.length - 1 && str[left - 1] == str[right + 1]) {
            left--;
            right++;
        }
        //更新最大长度
        if (right - left > range[1] - range[0]) {
            range[0] = left;
            range[1] = right;
        }
        //返回最右位置
        return ans;
    }

    //补充：双指针判断回文
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        String num = String.valueOf(x);
        char[] ns = num.toCharArray();
        int a = 0;
        int b = ns.length-1;
        while(a<b){
            if(ns[a] != ns[b]) return false;
            a++;
            b--;
        }
        return true;
    }
}
