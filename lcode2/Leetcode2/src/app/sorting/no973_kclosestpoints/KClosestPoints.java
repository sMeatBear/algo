package app.sorting.no973_kclosestpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We have a list of points on the plane. Find the K closest points to the
 * origin (0, 0).
 * 
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * 
 * You may return the answer in any order. The answer is guaranteed to be unique
 * (except for the order that it is in.)
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: points = [[1,3],[-2,2]], K = 1 Output: [[-2,2]] Explanation: The
 * distance between (1, 3) and the origin is sqrt(10). The distance between (-2,
 * 2) and the origin is sqrt(8). Since sqrt(8) < sqrt(10), (-2, 2) is closer to
 * the origin. We only want the closest K = 1 points from the origin, so the
 * answer is just [[-2,2]]. Example 2:
 * 
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2 Output: [[3,3],[-2,4]] (The
 * answer [[-2,4],[3,3]] would also be accepted.)
 * 
 * 
 * Note:
 * 
 * 1 <= K <= points.length <= 10000 -10000 < points[i][0] < 10000 -10000 <
 * points[i][1] < 10000
 */

class Solution {
    static class Point implements Comparable<Point> {
        int[] point;
        double distance;
        
        public Point(int[] point) {
            this.point = point;
            this.distance = getDistToOri(point);
        }
        
        @Override
        public int compareTo(Point p2) {
            if (this.distance < p2.distance) {
                return -1;
            } else {
                return 1;
            }
        }
    }
    // approach 2: min heap 2 (k in heap and traverse n)
    // approach 2: max heap (k in heap then pop n)
    public int[][] kClosest(int[][] points, int K) {
        // sanity check
        if (points == null || points.length == 0 || K <= 0) {
            return new int[0][0];
        }
        K = Math.min(points.length, K);
        
        // use heap
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((int)(K * 1.25), (a, b) -> (getDistToOri(a) < getDistToOri(b) ? 1 : -1));
        for (int i = 0; i < K; i++) {
            maxHeap.offer(points[i]);
        }
        for (int i = K; i < points.length; i++) {
            maxHeap.offer(points[i]);
            maxHeap.poll();
        }
        
        return maxHeap.toArray(new int[K][]);
    }

    // approach 1: min heap 1 (all in heap then pop k)
    public int[][] kClosest1(int[][] points, int K) {
        // sanity check
        if (points == null || points.length == 0 || K <= 0) {
            return new int[0][0];
        }
        K = Math.min(points.length, K);
        int[][] result = new int[K][];
        List<Point> pointObjs = new ArrayList<>(points.length);
        
        // create point objs
        for (int i = 0; i < points.length; i++) {
            pointObjs.add(new Point(points[i]));
        }
        // create the heap
        PriorityQueue<Point> minHeap = new PriorityQueue<>(pointObjs);
        for (int i = 0; i < K; i++) {
            result[i] = minHeap.poll().point;
        }
        
        return result;
    }
    
    private static double getDistToOri(int[] p) {
        return Math.sqrt(p[0] * p[0] +  p[1] * p[1]);
    }
}

public class KClosestPoints {
    
}