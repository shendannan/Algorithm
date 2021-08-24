package B0_BasicDataStructure;

public class MyHashMap {
    //不涉及扩容机制，不考虑哈希冲突（新值覆盖）
    private final int MAX_LEN = 1000000007;
    private Bucket[] bucketArray;

    static class Bucket {
        private int value;
        private boolean hasValue;
        public Bucket() {
            hasValue = false;
        }

        public void insert(Integer value) {
            this.value = value;
            hasValue = true;
        }

        public void delete() {
            hasValue = false;
        }

        public int get() {
            if(hasValue){
                return value;
            }else{
                return -1;
            }
        }
    }

    private int getIndex(int key) {
        return (key % MAX_LEN);
    }

    public MyHashMap() {
        bucketArray = new Bucket[MAX_LEN];
        for (int i = 0; i < MAX_LEN; i++){
            bucketArray[i] = new Bucket();
        }
    }

    public void put(int key, int value) {
        int bucketIndex = getIndex(key);
        bucketArray[bucketIndex].insert(value);
    }

    public int get(int key) {
        int bucketIndex = getIndex(key);
        return bucketArray[bucketIndex].get();
    }

    public void remove(int key) {
        int bucketIndex = getIndex(key);
        bucketArray[bucketIndex].delete();
    }
}