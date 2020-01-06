package app.tree;

import java.util.Arrays;
import java.util.List;

class ReplaceWords {
    public String replaceWords(List<String> dict, String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder res = new StringBuilder();
        
        for (String str : words) {
            boolean inDict = false;
            
            for (String d : dict) {
                if (str.startsWith(d)) {
                    res.append(d).append(" ");
                    inDict = true;
                    break;
                } 
            }

            if (!inDict) {
                res.append(str).append(" ");
            }
        }

        return res.substring(0, res.length() - 1);
    }
}

public class No648_ReplaceWords {
    public static void main(String[] args) {
        String[] dictArr = new String[] {"cat", "bat", "rat"};
        List<String> dict = Arrays.asList(dictArr);

        ReplaceWords s = new ReplaceWords();
        String sentence = "the cattle was rattled by the battery";
        String res = s.replaceWords(dict, sentence);
        System.out.println(res);
    }
}