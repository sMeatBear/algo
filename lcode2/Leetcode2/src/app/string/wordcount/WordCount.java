package app.string.wordcount;

public class WordCount {
    public static int wordCount2(String str) {
        // corner case
        if (str == null) {
            return -1;
        }

        int count = 0;
        for (int i = 0; i < str.length();) {
            while (i < str.length() && str.charAt(i) == ' ') {
                i++;
            }
            count++;
            while (i < str.length() && str.charAt(i) != ' ') {
                i++;
            }
        }

        return count;
    }

    public static int wordCount(String str) {
        // corner case
        if (str == null) {
            return -1;
        }

        int count = 0;
        boolean foundWord = false;
        for (int i = 0; i < str.length(); i++) {
            if (!foundWord && str.charAt(i) != ' ') {
                // found first non blank space char
                count++;
                foundWord = true;
            } else if (foundWord && str.charAt(i) == ' ') {
                // end of word 
                foundWord = false;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String str = "Hello";
        System.out.println(wordCount(str));
    }
}