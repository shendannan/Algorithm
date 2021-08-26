package B0_BasicDataStructure;
import java.util.*;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 */
public class MinStack {
    //使用两个栈实现
    Deque<Integer> list;
    Deque<Integer> minlist;

    /** initialize your data structure here. */
    public MinStack() {
        list = new ArrayDeque<>();
        minlist = new ArrayDeque<>();
    }

    public void push(int x) {
        //同时插入同时取出 但是minlist永远push的是当前栈里的最小值，即构建一个最小值栈作为原栈的最小值一一映射
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