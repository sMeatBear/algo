public class NumTrue5 {
    // new 
    // from middle to two sides
    public static String palindromeStr(String s, int index) {
        // if the palindromic in the beginning or the end
        if (index == 0)
            return s.substring(0, 1);
        if (index == s.length() - 1)
            return s.substring(s.length() - 1);
        
        char[] sArr = s.toCharArray();
        // from middle to two sides
        int i = index - 1;
        int j = index + 1;
        // two situations
        // 1. single middle pos
        String s1 = sArr[index] + "", s2 = s1;
        
        for (; i >= 0 && j < s.length(); i--, j++) {
            if (sArr[i] == sArr[j])
                s1 = sArr[i] + s1 + sArr[j];
            else
                break;
        } 
        
        // 2. two middle positions
        i = index - 1;
        j = index + 2;
        if (sArr[index] == sArr[index + 1]) {
            s2 += sArr[index + 1] + "";
            
            for (; i >= 0 && j < s.length(); i--, j++) {
                if (sArr[i] == sArr[j])
                    s2 = sArr[i] + s2 + sArr[j];
                else
                    break;
            }
        }
        
        // compare two situations
        if (s1.length() >= s2.length())
            return s1;
        else
            return s2;
    }

    public static void main(String[] args) {
        
        System.out.println(palindromeStr("babad", 2));
    }
}