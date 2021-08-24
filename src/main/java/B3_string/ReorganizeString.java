package B3_string;

/**
 * 重构字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * 输入: S = "aab"
 * 输出: "aba"
 * 输入: S = "aaab"
 * 输出: ""
 * 注意: S 只包含小写字母并且长度在[1, 500]区间内。
 *
 * 链接：https://leetcode-cn.com/problems/reorganize-string
 */
public class ReorganizeString {
    public String reorganizeString(String S) {
        int[] tmp = new int[26];
        StringBuilder st = new StringBuilder();
        //对字符串中每个字符进行计数
        for(int i=0;i<S.length();i++){
            tmp[S.charAt(i)-'a']++;
        }
        //判断能否进行重新排序
        for(int i=0;i<26;i++){
            if(tmp[i]>(S.length()+1)/2) return "";
        }
        while(st.length()<S.length()){
            int index = 0;  //记录还未排序字符串中出现频次最多的字符
            for(int i=0;i<26;i++){
                if(tmp[index]<tmp[i]) index = i;
            }
            //放入一个还未经排序的字符中的频数最大的字符
            st.append((char)('a'+index));
            tmp[index]--;
            //放入一个与前一个放入不同的字符
            for(int i=0;i<26;i++){
                if(i!=index && tmp[i]>0){
                    st.append((char)('a'+i));
                    tmp[i]--;
                    break;
                }
            }
        }
        return st.toString();
    }
}
