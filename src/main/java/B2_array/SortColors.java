package B2_array;

/**
 * 颜色分类
 * 给定一个包含红色、白色和蓝色，一共n个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，
 * 并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、1和2分别表示红色、白色和蓝色。
 *
 * 链接：https://leetcode-cn.com/problems/sort-colors
 */
public class SortColors {
    public void sortColors(int[] nums) {
        //双指针 p0从左到右 p2从右到左
        int p0 = 0, p2 = nums.length-1;
        for(int i = 0;i<=p2;i++){//注意这里要带等号
            while(nums[i]==2 && i<=p2){//因为循环体里有p2--，如果不加约束则会出现p2为负
                //交换nums[i]和nums[p2],p2--
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                p2--;
            }
            if(nums[i]==0){
                //交换nums[i]和nums[p0],p0++
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                p0++;
            }
        }
        //其他方法：1、两次遍历，先把0移到前面去，接着把2移到末尾
        //2、双指针一次遍历，都从头开始，p0交换0，p1交换1，找到0则p0++，p1++；找到1则p1++
    }
}
