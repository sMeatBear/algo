package app.basic.recursion;

public class PrintReversely {
    // Bottom-up
    public static String printReversely(String str) {
        return printReverselyHelper(str.toCharArray(), 0);
    }
    public static String printReverselyHelper(char[] strArr, int i) {
        if (i == strArr.length - 1) {
            return strArr[i] + "";
        }

        String pre = printReverselyHelper(strArr, i + 1);
        return pre + strArr[i];
    }

    public static void main(String[] args) {
        String str = printReversely("Print a string in reverse order.");
        System.out.println(str);
    }
}