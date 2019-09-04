public class N06 {
    public static String convert(String s, int numRows) {
        // s is empty
        if (s.length() == 0)
            return "";
        
        // only one row (no zigzag shape)
        if (numRows == 1)
            return s;
        
        // string to char array
        char[] sArr = s.toCharArray();
        // link length between two column
        int link = numRows - 2;
        int offset;
        // result
        String result = "";
        
        // follow the formula: 2(#end-#row) * ((#col+1) % 2) + 
        // 2(#start-#row)*((#col+1)%2)+i
        // i is the position in original string
        for (int row = 0, i = 0, flag = 0; row < numRows && row < sArr.length;) {
            if (row == 0 || row == numRows - 1)
                offset = numRows + link;
            else {
                offset = ((flag + 1) % 2) * 2 * (numRows - 1 - row) + 
                    (flag % 2) * 2 * row ;
                // flag only change when it's not in first row and last row
                flag++;
            }
            
            result += sArr[i];
            
            i += offset;
            // when i is out of boundary then to next row
            if (i >= sArr.length) {
                i = ++row;
                // reset flag
                flag = 0;
            }
        }
        return result;
    }

     public static void main(String[] args) {
         System.out.println(convert("PAYPALISHIRING", 4));
     }
}