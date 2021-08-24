import java.util.*;

public class TencentTest {
    static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static ListNode node = null;
    /**
     * 你需要返回m个指针，第i个指针指向一条链，表示第i个问题的答案
     *
     * @param root TreeNode类 指向链表树的根
     * @param b    int整型一维数组 表示每个问题是什么
     * @return ListNode类一维数组
     */
    public static ListNode[] question1(TreeNode root, int[] b) {
        List<ListNode> res = new ArrayList<>();
        for(int i:b){
            dfs(root,i,new ArrayList<>());
            res.add(node);
        }
        ListNode[] ans = new ListNode[res.size()];
        for(int i = 0;i<res.size();i++){
            ans[i] = res.get(i);
        }
        return ans;
    }

    public static boolean dfs(TreeNode root, int target, List<Integer> trace){
        if(trace.size() != 0 && trace.get(trace.size()-1) == target){
            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            for(int i:trace){
                cur.next = new ListNode(i);
                cur = cur.next;
            }
            node = dummy.next;
            return true;
        }
        boolean flag = false;
        //做选择
        if(root != null){
            trace.add(root.val);
            flag = dfs(root.left,target,trace);
            flag = flag | dfs(root.right,target,trace);
            trace.remove(trace.size()-1);
        }
        return flag;
    }

    public static void question2() {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while(T>0){
            T--;
            int n = scanner.nextInt();
            //dp[n]代表 n的最少次数
            int[] dp = new int[n+1];
            dp[0] = 0;
            for(int i = 1;i<=n;i++){
                int dp1 = Integer.MAX_VALUE,dp2 = Integer.MAX_VALUE;
                if(i % 2 == 0){
                    dp1 = dp[i/2] + 1;
                }
                if(i % 3 == 0){
                    dp2 = dp[i/3] + 1;
                }
                dp[i] = Math.min(dp[i-1]+1,Math.min(dp1,dp2));
            }
            System.out.println(dp[n]);
        }
    }

    public static void question3(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<int[]> arrays = new ArrayList<>();
        for(int i = 0;i<n;i++){
            int size = scanner.nextInt();
            int[] a1 = new int[size];
            for(int j = 0;j<size;j++){
                a1[j] = scanner.nextInt();
            }
            arrays.add(a1);
        }
        int qNum = scanner.nextInt();
        while(qNum>0){
            qNum--;
            int arrays2Combine = scanner.nextInt();
            List<int[]> list = new ArrayList<>();//list里的是要合并的数组
            for(int i = 0;i<arrays2Combine;i++){
                list.add(arrays.get(scanner.nextInt()-1));
            }
            int k = scanner.nextInt();
            int[] res = merge(list);
            System.out.println(res[k-1]);
        }
    }

    public static int[] merge(List<int[]> arrays){
        int size = arrays.size();
        if(size == 1) return arrays.get(0);
        List<Integer> pq = new ArrayList<>();
        for (int[] defalt : arrays) {
            for (int i : defalt) {
                pq.add(i);
            }
        }
        int[] ans = new int[pq.size()];
        for(int i = 0;i<pq.size();i++){
            ans[i] = pq.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(7);
        ListNode[] res = question1(node,new int[]{7});
        for(ListNode i:res){
            while(i!=null){
                System.out.println(i.val);
                i = i.next;
            }
        }
    }
}