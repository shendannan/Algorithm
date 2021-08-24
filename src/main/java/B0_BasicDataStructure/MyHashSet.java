package B0_BasicDataStructure;
import java.util.*;

public class MyHashSet {
    //不涉及扩容机制
    private final int MAX_LEN = 1000000007;
    private Bucket[] bucketArray;

    static class Bucket {
        private List<Integer> container;

        public Bucket() {
            container = new ArrayList<>();
        }

        public void insert(Integer key) {
            if (!exists(key)) {// 不存在这个值就添加，否则不变
                container.add(key);
            }
        }

        public void delete(Integer key) {
            if (exists(key)) {// 不存在这个值就添加，否则不变
                container.remove(key);
            }
        }

        public boolean exists(Integer key) {
            return container.contains(key);
        }
    }

    private int getIndex(int key) {
        return key % MAX_LEN;
    }

    public MyHashSet() {
        bucketArray = new Bucket[MAX_LEN];
        for (int i = 0; i < MAX_LEN; i++){
            bucketArray[i] = new Bucket();
        }
    }

    public void add(int key) {
        int bucketIndex = getIndex(key);
        bucketArray[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = getIndex(key);
        bucketArray[bucketIndex].delete(key);
    }

    public boolean contains(int key) {
        int bucketIndex = getIndex(key);
        return bucketArray[bucketIndex].exists(key);
    }
}