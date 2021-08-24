import java.util.*;

public class Test {
    public static void test(){
        //字符串和int互转
        Integer.parseInt("0101011",2);
        Integer.parseInt("12345");
        String.valueOf(12);

        //Map的基本用法
        Map<Character,Integer> map = new HashMap<>();
        map.put('c',1);
        map.getOrDefault('a',0);
        map.containsKey('a');
        for(Map.Entry<Character,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        for(Character key:map.keySet()){
            System.out.println(key+" "+map.get(key));
        }
        for(Integer i:map.values()){
            System.out.println(i);
        }

        //set的基本用法
        Set<Integer> set = new HashSet<>();
        set.add(1);set.contains(1);set.isEmpty();
        for(int i:set){
            System.out.println(i);
        }

        //Deque作为列表
        Deque<Integer> deque = new ArrayDeque<>();
        deque.poll();deque.pollFirst();deque.offerLast(1);
        deque.peekFirst();deque.removeFirst();//为空时会抛异常
        //Deque作为栈
        deque.push(1);deque.pop();
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        //StringBuilder的基本用法
        StringBuilder ss = new StringBuilder();
        ss.append("sd");

    }

    public static void main(String[] args) {
        int[][] a = new int[3][];
        a[0] = new int[]{5,2,6};
        a[1] = new int[]{1,3,4};
        a[2] = new int[]{9,8,7};
        for(int[] i: a){
            for(int j: i){
                System.out.print(j+ " ");
            }
            System.out.println();
        }
        System.out.println();
        for(int[] i:a){
            Arrays.sort(i);
        }
        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2]-o2[2];
            }
        });

        for(int[] i: a){
            for(int j: i){
                System.out.print(j+ " ");
            }
            System.out.println();
        }
    }
}
