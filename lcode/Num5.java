class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // the total length of two arrays
        int len = nums1.length + nums2.length;
        // is length of two arrays odd
        boolean isOdd;
        // two pointers for two arrays
        int p1 = 0, p2 = 0;
        // median position
        int medianPos;
        // check the median is in which arrays, the default is in num1
        int medianArr = 1;
        
        // find median position
        if (len % 2 == 0) {
            isOdd = false;
            medianPos = len / 2;
        } else {
            isOdd = true;
            medianPos = (len + 1) / 2;
        }
        
        // total offset
        int i = 0;
        for (; i < medianPos && p1 < nums1.length && p2 < nums2.length; i++) {
            // move p1 and p2 pointers to 
            // find median position: 3 situations (2 can be combined)
            if (nums1[p1] <= nums2[p2]) {
                // only move p1 to next element to compare with p2
                System.out.println(nums1[p1]);
                p1++;
                i++;
                medianArr = 1;
            } else {
                System.out.println(nums2[p2]);
                p2++;
                i++;
                medianArr = 2;
            }
        }
        
        // if offset does not reach the median
        if (i < medianPos) {
            if (medianArr == 2) {
                for (; i < medianPos; i++) {
                    p1++;
                    i++;
                    medianArr = 1;
                    // System.out.println(nums1[p1]);
                }
            } else {
                for (; i < medianPos; i++) {
                    p2++;
                    i++;
                    medianArr = 2;
                    // System.out.println(nums2[p2]);
                }
            }
        }
        
        // calculate median
        // median(s)
        int m1, m2;
        // check the median is in which array
        if (medianArr == 1)
                m1 = nums1[p1];
            else 
                m1 = nums2[p2];
        
        
        if (isOdd) {
            return m1;
        } else {
            // there are two medians
            if (nums1[p1 + 1] <= nums2[p2 + 1]) {
                m2 = nums1[p1 + 1];
            } else {
                m2 = nums2[p2 + 1];
            }

            return (m1 + m2) / 2;
        }
        
    }
}

public class Num5 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
          return new int[0];
        }
    
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
    
    public static String doubleToString(double input) {
        return new DecimalFormat("0.00000").format(input);
    }
    
    public static void main(String[] args){
        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] nums2 = stringToIntegerArray("[1, 3]");
        int[] nums1 = stringToIntegerArray("[2]");
        double ret = new Solution().findMedianSortedArrays(nums1, nums2);
        
        String out = doubleToString(ret);
        
        System.out.print(out);
            
    }
}