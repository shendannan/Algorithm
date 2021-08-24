package B0_BasicDataStructure;
import java.util.*;

//栈实现队列
public class MyQueue {
    Deque<Integer> stack;
    Deque<Integer> another;

    public MyQueue() {
        //双栈实现
        stack = new LinkedList<>();
        another = new LinkedList<>();
    }

    public void offer(int x) {
        while(!stack.isEmpty()){
            another.push(stack.pop());
        }
        stack.offerFirst(x);
        while(!another.isEmpty()){
            stack.push(another.pop());
        }
    }

    public int poll() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}