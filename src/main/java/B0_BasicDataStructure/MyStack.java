package B0_BasicDataStructure;
import java.util.*;

public class MyStack {
    private List<Integer> data;//常规方法，用列表即可
    private Queue<Integer> queue;//面试常问：用队列实现栈

    public MyStack() {
        data = new ArrayList<>();
        queue = new LinkedList<>();
    }

    public void push(int x) {
        //常规方法
        data.add(x);
        //队列实现
//        int n = queue.size();//原始长度
//        queue.offer(x);
//        for (int i = 0; i < n; i++) {
//            queue.offer(queue.poll());
//        }
    }

    public boolean isEmpty() {
        return data.isEmpty();
//        return queue.isEmpty();
    }

    public int top() {
        return data.get(data.size() - 1);
//        return queue.peek();
    }

    public boolean pop() {
        if (isEmpty()) {
            return false;
        }
        data.remove(data.size() - 1);
//        queue.poll();
        return true;
    }
}