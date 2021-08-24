package B0_BasicDataStructure;
import java.util.*;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 */
public class MinStack {
    Deque<Integer> list;
    Deque<Integer> minlist;

    /** initialize your data structure here. */
    public MinStack() {
        list = new ArrayDeque<>();
        minlist = new ArrayDeque<>();
    }

    public void push(int x) {
        //同时插入同时取出
        list.offerFirst(x);
        if(minlist.isEmpty()||x<minlist.peekFirst()){
            minlist.offerFirst(x);
        }else{
            minlist.offerFirst(minlist.peekFirst());
        }
    }

    public void pop() {
        list.pollFirst();
        minlist.pollFirst();
    }

    public int top() {
        return list.peekFirst();
    }

    public int getMin() {
        return minlist.peekFirst();
    }
}