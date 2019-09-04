class Solution13 {
    public int getValue(char ch) {
        char[] symbol = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] value = {1, 5, 10, 50, 100, 500, 1000}; 
        
        for (int i = symbol.length - 1; i >= 0; i--) {
            if (ch == symbol[i])
                return value[i];
        }
        return 0;
    }
    
    
    public int romanToInt(String s) {
        // out
        if (s.isEmpty()) return 0;
        
        char first = s.charAt(0);
        int res = 0;
        
        // get value
        int firstValue = getValue(first);
        // design a recursive method
        if (s.length() > 1) {
            int secondValue = getValue(s.charAt(1));
            if (firstValue < secondValue)
                return res + secondValue - firstValue + romanToInt(s.substring(2));
            else
                return res + firstValue + romanToInt(s.substring(1));
        } else {
            return res + firstValue + romanToInt(s.substring(1));
        }
            
    }
    public int romanToIntNonRec(String s) {
        char[] symbol = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] value = {1, 5, 10, 50, 100, 500, 1000}; 
        int res = 0;

        // if s is empty
        if (s.isEmpty()) return res;
        
        // non empty
        int j = symbol.length - 1;
        int firstValue = 0;
        for (int i = 0; i < s.length(); i++) {
            char first = s.charAt(i);
            while (j >= 0) {
                if (first == symbol[j]) {
                    firstValue = value[j];
                    break;
                }
                
                j--;
            }
            if (i + 1 < s.length()) {
                char second = s.charAt(i + 1);
                if (j + 1 < symbol.length && second == symbol[j + 1]) {
                    res += value[j + 1] - value[j];
                    // move i to next two
                    i++;
                    // rewind the j pointer
                    j += 1;
                } else if (j + 2 < symbol.length && second == symbol[j + 2]) {
                    res += value[j + 2] - value[j];
                    i++;
                    j += 2;
                } else {
                    res += firstValue;
                }

            } else {
                res += firstValue;
            }
        }


        return res;
    }
}

public class No13 {

    public static void main(String[] args) {
        Solution13 s = new Solution13();
        System.out.println(s.romanToIntNonRec("MCDXCVIII"));
    }
}