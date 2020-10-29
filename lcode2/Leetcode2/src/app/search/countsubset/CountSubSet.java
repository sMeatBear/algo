package app.search.countsubset;

import java.util.ArrayList;
import java.util.List;

public class CountSubSet {
    public List<String> countSubset(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder curr = new StringBuilder();

        countSubsetHelper(s.toCharArray(), res, curr, 0);
        return res;
    }

    private void countSubsetHelper(char[] sArr, List<String> res, StringBuilder curr, int i) {
        res.add(curr.toString());
        
        for (int idx = i; idx < sArr.length; idx++) {
            if (idx > i && sArr[idx - 1] == sArr[idx]) {continue;}
            curr.append(sArr[idx]);
            countSubsetHelper(sArr, res, curr, idx + 1);
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    public static void main(String[] args) {
        CountSubSet c = new CountSubSet();
        List<String> res = c.countSubset("www.qq.com");
        System.out.println(res);
        System.out.println(res.size());
    }
}
