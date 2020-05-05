package app.string.slowfastpointers;

public class SlowFastPointers {
    // normalization
    public static String removeExtraSpace(char[] str) {
        if (str == null) {
            return null;
        }

        int slow = 0, fast = 0;
        boolean nonFirst = false;
        // slow fast pointer
        while (true) {
            while (fast < str.length && str[fast] == ' ') {
                fast++;
            }
            if (fast == str.length) {
                break;
            }
            if (nonFirst) {
                str[slow++] = ' ';
            }
            while (fast < str.length && str[fast] != ' ') {
                str[slow++] = str[fast++];
            }
            nonFirst = true;
        }

        return new String(str, 0, slow);
    }

    public static String removeAll(char[] str, char ch) {
        if (str == null) {
            return null;
        }

        // inplace work
        int slow = 0, fast = 0;
        for (; fast < str.length;) {
            if (str[fast] == ch) {
                fast++;
            } else {
                str[slow++] = str[fast++];
            }
        }

        return new String(str, 0, slow);
    }

    public static void main(String[] args) {
        char[] str = "student".toCharArray();
        System.out.println(removeAll(str, 't'));
        char[] str2 = "      I         love        who   ?".toCharArray();
        System.out.println(removeExtraSpace(str2));
    }

}