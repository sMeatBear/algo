class Solution12 {
    public String intToRoman(int num) {
        // use recursion
        String res = "";
        char[] symbol = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        char[] chArr = String.valueOf(num).toCharArray();
        int digit = chArr.length - 1;
        
        // rules and loop
        for (int i = 0; i < chArr.length; i++ ) {
            int value = Integer.parseInt(chArr[i] + "");
            while (value > 0) {
                if (digit == 3) {
                    res += symbol[2 * digit];
                    value--;
                } else if (value == 4 || value == 9) {
                    res += symbol[digit * 2];
                    value++;
                } else if (value == 10) {
                    res += symbol[digit * 2 + 2];
                    value -= 10;
                } else if (value >= 5) {
                    res += symbol[digit * 2 + 1];
                    value -= 5;
                } else {
                    res += symbol[digit * 2];
                    value--;
                }
            }
            digit--;
        }
        
        
        return res;
    }
}

public class No12 {
    public static void main(String[] args) {
        Solution12 s = new Solution12();
        String res = s.intToRoman(12);
        System.out.println(res);
    }
}