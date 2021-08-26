package C_scenario;

import java.util.ArrayList;
import java.util.List;

/**
 * 二进制手表顶部有4个 LED 代表小时（0-11），底部的6个LED代表分钟（0-59）。
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *  
 * 提示：
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 * 超过表示范围（小时 0-11，分钟 0-59）的数据将会被舍弃，也就是说不会出现 "13:00", "0:61" 等时间。
 * 链接：https://leetcode-cn.com/problems/binary-watch
 */
public class BinaryWatch {
    public List<String> readBinaryWatch(int num) {
        List<String> ans = new ArrayList<>();
        int[] nums = new int[]{8, 4, 2, 1, 32, 16, 8, 4, 2, 1};
        dfs(ans, num, nums, 0, 0, 0);
        return ans;
    }

    public void dfs(List<String> ans, int num, int[] nums, int curH, int curM, int index) {
        if (num == 0) {
            if (curH > 11 || curM > 59) return;
            //格式化输出
            String time;
            if (curM < 10) {
                time = curH + ":0" + curM;
            } else {
                time = curH + ":" + curM;
            }
            ans.add(time);
        }

        for (int i = index; i < nums.length; i++) {
            if (i < 4) {//hour
                curH += nums[i];
            } else {//minute
                curM += nums[i];
            }
            dfs(ans, num - 1, nums, curH, curM, i + 1);//关键：这里是i+1而不是index+1
            if (i < 4) {//hour
                curH -= nums[i];
            } else {//minute
                curM -= nums[i];
            }
        }
    }
}
