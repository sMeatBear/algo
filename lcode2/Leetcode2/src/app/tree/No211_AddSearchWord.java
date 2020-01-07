package app.tree;

class TNode {
    // end flag
    boolean isEnd;
    // for regex expression
    static final char WILDCARD = '.';
    // wildcard value
    static final int WC_VAL = 256;
    TNode[] children = new TNode[26];
    static int char2int(char ch) {
        // check if the char is a wildcard
        if (ch == WILDCARD) return WC_VAL;
        return ch - 'a';
    }
}


class WordDictionary {
    TNode head;

    /** Initialize your data structure here. */
    public WordDictionary() {
        head = new TNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        // change the string into char array
        char[] wordArr = word.toCharArray();
        // node pointer
        TNode cur = head;

        // add the word
        for (char ch : wordArr) {
            // get the index of the word
            int index = TNode.char2int(ch);
            if (cur.children[index] == null) {
                TNode node = new TNode();
                cur.children[index] = node;
            }
            // move the pointer to new node
            cur = cur.children[index];
        }

        // mark it as the end of the word
        cur.isEnd = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        char[] wordArr = word.toCharArray();
        return search(head, wordArr, 0);
    }

    /** search a single char each time for the whole word by dfs */
    public boolean search(TNode node, char[] wordArr, int start) {
        TNode cur = node;

        // check
        char ch = wordArr[start];
        if (start < wordArr.length - 1) {
            int index = TNode.char2int(ch);
            // if not a wild card
            if (index != TNode.WC_VAL) {
                if (cur.children[index] != null) {
                    return search(cur.children[index], wordArr, start + 1);
                } else {
                    return false;
                }
            } else {
                // meet a wild card
                // find all not empty children node and continue to compare next letter
                boolean res = false;
                for (TNode child : cur.children) {
                    if (child != null) {
                        res = res || search(child, wordArr, start + 1);
                    }
                }
                return res;
            }
        } else {
            // the end of the letter
            int index = TNode.char2int(ch);
            // next letter
            if (index != TNode.WC_VAL) {
                return cur.children[index] != null && cur.children[index].isEnd;
            } else {
                // the last letter is a wildcard
                for (TNode child : cur.children) {
                    if (child != null && child.isEnd) return true;
                }
                return false;
            }
        }
        
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

public class No211_AddSearchWord {
    public static void main(String[] args) {
        WordDictionary obj = new WordDictionary();
        obj.addWord("word");
        boolean param_2 = obj.search("...a");
        System.out.println(param_2);
    }

}