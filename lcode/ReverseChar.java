public class ReverseChar {
    // in-place reverse array using recursion
    public static void reverseChar (char[] chs, int index) {
        int swapIndex = chs.length - index - 1;
        // outlet
        if (index >= swapIndex) return;

        char temp;
        temp = chs[index];
        chs[index] = chs[swapIndex];
        chs[swapIndex] = temp;

        reverseChar(chs, index + 1);
    }

    public static void main(String[] args) {
        char[] chs = {'o', 'l', 'l', 'e', 'h'};
        reverseChar(chs, 0);
        System.out.println(chs);
    }
}