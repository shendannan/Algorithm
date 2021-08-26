package B3_string;

/**
 * 最小覆盖子串
 * 给你一个字符串s、一个字符串t。返回s中涵盖t所有字符的最小子串。
 * 如果s中不存在涵盖t所有字符的子串，则返回空字符串 "" 。
 * 注意：如果s中存在这样的子串，我们保证它是唯一的答案。
 *
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 */
public class MinWindow {
    public String minWindow(String s, String t) {
        if (s == null || s.equals("") || t == null || t.equals("") || s.length() < t.length()) {
            return "";
        }
        //维护两个数组，记录已有字符串指定字符的出现次数，和目标字符串指定字符的出现次数
        //ASCII表总长128
        int[] need = new int[128];
        int[] have = new int[128];

        //将目标字符串指定字符的出现次数记录
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        //分别为左指针，右指针，最小长度
        //已有字符串中目标字符串指定字符的出现总频次、最小覆盖子串在原字符串中的起始位置
        int left = 0, right = 0, min = Integer.MAX_VALUE, count = 0, start = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            //不需要，直接过
            if (need[r] == 0) {
                right++;
                continue;
            }
            //需要且不足，添加
            if (have[r] < need[r]) {
                count++;
            }
            //需要且多了，但其他字母缺少，继续增大窗口
            have[r]++;
            right++;
            //满足条件，开始比长度、缩小窗口
            while (count == t.length()) {
                //更改最小值，并记录起始位置
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }
                char l = s.charAt(left);
                //如果左边字符不被目标字符串需要，直接移动左指针
                if (need[l] == 0) {
                    left++;
                    continue;
                }
                //左边字符的数量刚好等于需要的量，则打破循环，继续右移窗口
                if (have[l] == need[l]) {
                    count--;
                }
                //左边字符被目标字符串需要，但有多的，删除多余的
                left++;
                have[l]--;
            }
        }
        return min == Integer.MAX_VALUE?"":s.substring(start, start + min);
    }
}
