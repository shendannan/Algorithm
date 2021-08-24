package B0_BasicDataStructure;

import java.util.*;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 */
class Trie {
    List<String> list;
    /** Initialize your data structure here. */
    public Trie() {
        list = new ArrayList<>();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        list.add(word);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return list.contains(word);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        for(String s:list){
            if(s.startsWith(prefix)){
                return true;
            }
        }
        return false;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */