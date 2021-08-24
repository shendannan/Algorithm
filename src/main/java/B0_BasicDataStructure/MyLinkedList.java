package B0_BasicDataStructure;

public class MyLinkedList {
    static class SingleListNode {
        public int val;
        public SingleListNode next;
        public SingleListNode(int x) { val = x; }
    }

    static class DoubleListNode {
        public int val;
        public DoubleListNode next;
        public DoubleListNode prev;
        public DoubleListNode(int x) { val = x; }
    }

    int size;
    //用单链表实现
    SingleListNode dummy;
    //用双链表实现
    DoubleListNode dummyhead;
    DoubleListNode dummytail;

    public MyLinkedList() {
        size = 0;
        //用单链表实现
        dummy = new SingleListNode(0);
        //用双链表实现
        dummyhead = new DoubleListNode(0);
        dummytail = new DoubleListNode(0);
        dummyhead.next = dummytail;
        dummytail.prev = dummyhead;
    }

    /** 得到第index个节点的值，如果不存在，返回-1. */
    public int get(int index) {
        if(index < 0 || index >= size)  return -1;
        //用单链表实现
        SingleListNode cur = dummy;
        for(int i = 0;i < index+1;i++){
            cur = cur.next;
        }

        //用双链表实现
        //根据index的位置来选择从头开始还是从尾开始
//        DoubleListNode cur;
//        if (index + 1 < size - index){
//            cur = dummyhead;
//            for(int i = 0; i < index + 1; i++){
//                cur = cur.next;
//            }
//        }else {
//            cur = dummytail;
//            for(int i = 0; i < size - index; i++){
//                cur = cur.prev;
//            }
//        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0,val);
    }

    public void addAtTail(int val) {
        addAtIndex(size,val);
    }

    /** 在第index个结点前加入新节点，如果index=链表长度，则等同于addAtTail，如果大于链表长度，则不插入 */
    public void addAtIndex(int index, int val) {
        if(index > size) return;
        if(index < 0) index = 0;
        size++;
        //用单链表实现
        SingleListNode pre = dummy;
        for(int i = 0;i<index;i++){
            pre = pre.next;
        }
        SingleListNode toAdd = new SingleListNode(val);
        toAdd.next = pre.next;
        pre.next = toAdd;

        //用双链表实现
//        DoubleListNode pred, succ;//前驱结点和后继节点
//        if (index < size - index) {
//            pred = dummyhead;
//            for(int i = 0; i < index; i++){
//                pred = pred.next;
//            }
//            succ = pred.next;
//        } else {
//            succ = dummytail;
//            for (int i = 0; i < size - index; ++i){
//                succ = succ.prev;
//            }
//            pred = succ.prev;
//        }
//        DoubleListNode toAdd1 = new DoubleListNode(val);
//        // 先加新节点与前驱结点后继节点的关联，再修改前驱结点与后继节点
//        toAdd1.prev = pred;
//        toAdd1.next = succ;
//        pred.next = toAdd1;
//        succ.prev = toAdd1;
    }

    /** 若index不合法则不删除 */
    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size) return;
        size--;
        //用单链表实现
        SingleListNode pre = dummy;
        for(int i = 0;i<index;i++){
            pre = pre.next;
        }
        pre.next = pre.next.next;

        //用双链表实现
//        DoubleListNode pred, succ;
//        if (index < size - index) {
//            pred = dummyhead;
//            for(int i = 0; i < index; i++){
//                pred = pred.next;
//            }
//            succ = pred.next.next;
//        }
//        else {
//            succ = dummytail;
//            for (int i = 0; i < size - index - 1; i++){
//                succ = succ.prev;
//            }
//            pred = succ.prev.prev;
//        }
//
//        pred.next = succ;
//        succ.prev = pred;
    }
}