package app.tree;

class TrieNode {
    // have children or not (end of the word)
    boolean isEnd;
    // next letters
    TrieNode[] children = new TrieNode[26];

    public static int char2int(char ch) {
        return ch - 'a';
    }
}

public class Trie {
    TrieNode head;
    /** Initialize your data structure here. */
    public Trie() {
        head = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] letters = word.toCharArray();
        TrieNode cur = head;

        // create trie nodes
        for (char ch : letters) {
            // find the corresponding index of the char
            int index = TrieNode.char2int(ch);
            // have the child
            if (cur.children[index] == null) {
                // intialize an new node
                TrieNode node = new TrieNode();
                // connect its parent
                cur.children[index] = node;
            } 
            // move the pointer to the new node
            cur = cur.children[index];
        }

        // mark the last node
        cur.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] letters = word.toCharArray();
        TrieNode cur = head;

        for (char ch : letters) {
            // get the ch index
            int index = TrieNode.char2int(ch);

            if (cur.children[index] == null) return false;
            cur = cur.children[index];
        }

        return cur.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        char[] letters = prefix.toCharArray();
        TrieNode cur = head;

        for (char ch : letters) {
            // get the ch index
            int index = TrieNode.char2int(ch);

            if (cur.children[index] == null) return false;
            cur = cur.children[index];
        }

        return true;
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("word");
        t.insert("words");
        t.insert("prefix");

        System.out.println(t.search("word"));
        System.out.println(t.startsWith("wo"));
        System.out.println(t.search("words"));
    }
}