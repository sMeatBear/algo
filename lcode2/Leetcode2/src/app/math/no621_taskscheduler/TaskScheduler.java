package app.math.no621_taskscheduler;

import java.util.Arrays;

/**
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

 

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 

Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
 */
class Solution {
    // Optimization problem
    public int leastInterval(char[] tasks, int n) {
        /* 
            Fill the n empty
        */
        // 1. Find the mode
        int[] mode = new int[26];
        int max = 0;
        for (char task : tasks) {
            int idx = task - 'A';
            max = ++mode[idx] > max ? mode[idx] : max;
        }
        
        // 2. Sort
        Arrays.sort(mode);

        // 3. The max interval
        // Other tasks except the mode task
        int exceptMode = tasks.length - max;
        // Idle slot which can be inserted
        int totalSlot = n * max;
        // If there is enough empty slot to insert different tasks
        int diff = totalSlot - exceptMode;
        if (diff <= 0) return tasks.length;
        else {
            // Same appearance freq as the mode in the arr
            int sameMax = 0;
            for (int i = mode.length - 1; i >= 0 && mode[i] == max; i--) sameMax++;
            // The last task flow do not need more idle slot any more
            return (n + 1) * (max - 1) + sameMax;
        }
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        char[] tasks = new char[] {'A','A','A','A','A','A','B','C','D','E','F','G'};
        int n = 4;
        System.out.println(new Solution().leastInterval(tasks, n));
    }
}