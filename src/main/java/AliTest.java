import java.util.*;

public class AliTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); //n个人
        int m = scanner.nextInt(); //m个关系
        Map<Integer,Set<Integer>> adj = new HashMap<>();//邻接表
        for(int i = 0;i < m;i++){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if(adj.containsKey(a)){
                Set<Integer> cur = adj.get(a);
                cur.add(b);
                adj.put(a,cur);
            }else{
                Set<Integer> cur = new HashSet<>();
                cur.add(b);
                adj.put(a,cur);
            }
        }
        //邻接表构建完毕
        int q = scanner.nextInt();
        while(q > 0){
            q--;
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            //开始搜索最短路径
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new ArrayDeque<>();
            visited.add(x);
            queue.add(x);
            int ans = -1;
            boolean flag = false;
            while(!queue.isEmpty()){
                ans++;
                int size = queue.size();
                for(int i = 0;i<size;i++){
                    int cur = queue.poll();
                    if(cur == y) {
                        flag = true;
                        break;
                    }
                    if(adj.get(cur) == null) continue;
                    for(int nei:adj.get(cur)){
                        if(!visited.contains(nei)){
                            visited.add(nei);
                            queue.offer(nei);
                        }
                    }
                }
                if(flag) break;
            }
            if(flag){
                System.out.println(ans);
            }else{
                System.out.println(-1);
            }
        }
    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int T = scanner.nextInt();
//        while(T>0){
//            T--;
//            int n = scanner.nextInt();
//            int m = scanner.nextInt();
//            List<int[]> pt = new ArrayList<>();
//            for(int i = 0;i < m;i++){
//                int x = scanner.nextInt();
//                int y = scanner.nextInt();
//                int[] point = {x,y};
//                pt.add(point);
//            }
//            if(m == 1){
//                int x = pt.get(0)[0];
//                int y = pt.get(0)[1];
//                System.out.println(Math.abs(x-y));
//            }
//            System.out.println(new Random().nextInt());
//        }
//    }
}
