package app.sorting.no406_queuereconstructionbyheight;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Suppose you have a random list of people standing in a queue. Each person is
 * described by a pair of integers (h, k), where h is the height of the person
 * and k is the number of people in front of this person who have a height
 * greater than or equal to h. Write an algorithm to reconstruct the queue.
 * 
 * Note: The number of people is less than 1,100.
 * 
 * 
 * Example
 * 
 * Input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 
 * Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */

class Solution {
    
    public int[][] reconstructQueue(int[][] people) {
        // Quick sort
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
        
        // insert sort
        List<int[]> resList = new LinkedList<>();
        for (int[] p : people) {
            resList.add(p[1], p);
        }
        
        return resList.toArray(new int[people.length][2]);
    }
    
}
public class QueueReconstruction {

}