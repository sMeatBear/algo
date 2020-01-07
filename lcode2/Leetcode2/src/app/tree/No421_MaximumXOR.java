package app.tree;

import java.util.HashSet;
import java.util.Set;

class MaximumXOR {
    // *wrong: approach 1: O(n)
    public int findMaximumXOR1(int[] nums) {
        int maxNum = Integer.MIN_VALUE, max = maxNum;

        // find the max number in number array
        for (int num : nums) {
            maxNum = num > maxNum ? num : maxNum;
        }

        // find another number to make XOR
        for (int num : nums) {
            int cur = num ^ maxNum;
            max = cur > max ? cur : max;
        }

        return max;
    }

    // approach 2: O(n^2)
    public int findMaximumXOR2(int[] nums) {
        int max = Integer.MIN_VALUE;

        // O(n^2) traverse
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int cur = nums[i] ^ nums[j];
                max = cur > max ? cur : max;
            }
        }

        return max;
    }

    // approach 3: O(n)
    public int findMaximumXOR(int[] nums) {
        int maxResult = 0; 
        int mask = 0;
        /*The maxResult is a record of the largest XOR we got so far. if it's 11100 at i = 2, it means 
        before we reach the last two bits, 11100 is the biggest XOR we have, and we're going to explore
        whether we can get another two '1's and put them into maxResult
        
        This is a greedy part, since we're looking for the largest XOR, we start 
        from the very begining, aka, the 31st postition of bits. */
        // e.g. [14, 11, 7, 2] = [1110, 1011, 0111, 0010]
        for (int i = 30; i >= 0; i--) {
            
            //The mask will grow like  100..000 , 110..000, 111..000,  then 1111...111
            //for each iteration, we only care about the left parts
            int curMask = (1 << i);
            mask = mask | curMask;
            
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                
/*                we only care about the left parts, for example, if i = 2, then we have
                {1100, 1000, 0100, 0000} from {1110, 1011, 0111, 0010}*/
                int leftPartOfNum = num & mask;
                // only one number
                set.add(leftPartOfNum);
            }
            
            // if i = 1 and before this iteration, the maxResult we have now is 1000, 
            // my wish is the maxResult will grow to 1100, so I will try to find a candidate
            // which can give me the greedyTry;
            int greedyTry = maxResult | curMask;
            
            for (int leftPartOfNum : set) {
                //This is the most tricky part, coming from a fact that if a ^ b = c, then a ^ c = b;
                // now we have the 'c', which is greedyTry, and we have the 'a', which is leftPartOfNum
                // If we hope the formula a ^ b = c to be valid, then we need the b, 
                // and to get b (only care about the MSB left part), 
                // we need a ^ c, if a ^ c exisited in our set, then we're good to go
                int anotherNum = leftPartOfNum ^ greedyTry;
                if (set.contains(anotherNum)) {
                    maxResult= greedyTry;
                    break;
                }
            }
            
            // If unfortunately, we didn't get the greedyTry, we still have our max, 
            // So after this iteration, the max will stay at 1100.
        }
        
        return maxResult;
    }
}


public class No421_MaximumXOR {
    public static void main(String[] args) {
        int[] nums = new int[] {2,8,10};
        MaximumXOR mx = new MaximumXOR();
        int max = mx.findMaximumXOR(nums);
        System.out.println(max);
    }
}