package app.backtracking.subset;

import java.util.ArrayList;
import java.util.List;

public class Subset {
    public static List<List<Character>> subset(char[] chs) {
        List<List<Character>> res = new ArrayList<>();
        List<Character> tmp = new ArrayList<>();
        backtrack(chs, 0, tmp, res);
        return res;
    }

    public static void backtrack(char[] chs, int position, List<Character> tmp, List<List<Character>> res) {
        // set is no duplicate, so we just move the index to the end and decide each element is included
        // or not
        if (position < chs.length) {
            // include this element
            tmp.add(chs[position]);
            backtrack(chs, position + 1, tmp, res);
            // backtrack
            tmp.remove(tmp.size() - 1);
            // not include this element
            backtrack(chs, position + 1, tmp, res);
        } else if (position == chs.length) {
            res.add(new ArrayList<>(tmp));
        }
    }

    public static void main(String[] args) {
        char[] chs = new char[] {'a', 'b', 'c'};
        System.out.println(subset(chs));
    }
}