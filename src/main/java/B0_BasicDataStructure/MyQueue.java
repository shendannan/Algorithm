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
        //永远保持新元素位于栈底，因此当加入新元素时，首先将原有的元素移到另一个栈，添加新元素后再加回来
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