public class Next {
    public static void getNext(int[] next, String mode) {
        if (next.length == 0) return;
        
        // initializing
        next[0] = -1;

        // mode index
        int i = 1;
        while (i < next.length) {
            // find longest prefix
            int j = i - 1;
            while (true) {
                // mode[next[j]] == mode[i - 1] means: 0~next[j] is the prefix of k~i-1
                if (j == 0 || mode.charAt(next[j]) == mode.charAt(i - 1)) {
                    next[i] = next[j] + 1;
                    break;
                }
                j = next[j];
            }
            i++;
        }
    }
    public static void main(String[] args) {
        String mode = "ababaaababaa";
        int[] next = new int[mode.length()];
        getNext(next, mode);
        for (int val : next)
            System.out.print(val);
    }
}