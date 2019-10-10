package app.linear;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class SkyLineSolution {
	class ListComparatorSame implements Comparator<List<Integer>> {

		@Override
		public int compare(List<Integer> l1, List<Integer> l2) {
			return l2.get(1) - l1.get(1);
		}
		
	}
	
	class ListComparator implements Comparator<List<Integer>> {

		@Override
		public int compare(List<Integer> l1, List<Integer> l2) {
			return l1.get(0) - l2.get(0);
		}
		
	}
	
	 public List<List<Integer>> getSkyline(int[][] buildings) {
	        // record left bound and height of buildings
	        List<List<Integer>> heights = new ArrayList<>();
	        List<List<Integer>> res = new ArrayList<>();
	        
	        if (buildings.length == 0) return res;
	        
	        // seperate left bound and right bound
	        for (int[] b : buildings) {
	            List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
	            left.add(b[0]);
	            left.add(b[2]);
	            // negative height means it's end of the building
	            right.add(b[1]);
	            right.add(-b[2]);
	            
	            heights.add(left);
	            heights.add(right);
	        }
	        
	        // sort the contour by height, left to right traverse to find the highest contour
	        Collections.sort(heights, new ListComparatorSame());
	        Collections.sort(heights, new ListComparator());
	        // store current height
	        Queue<Integer> pq = new PriorityQueue<>((a, b) -> {return b - a;});
	        
	        // ** lowest value (no building)
	        pq.offer(0);
	        
	        int preMax = 0;
	        for (List<Integer> h : heights) {
	        	System.out.println(h.get(0) + " " + h.get(1));
	            if (h.get(1) > 0) {
	                // left bound of a building
	                pq.offer(h.get(1));
	            } else {
	                // right bound of a building
	                pq.remove(-h.get(1));
	                
	            }
	            
	            // if currMax has changed
	            int currMax = pq.peek();
	            if (currMax != preMax) {
	                // it shows this is a key point for the overall contour, because the max height of the building has changed
	                List<Integer> one = new ArrayList<>();
	                // max change coordinate (key point)
	                one.add(h.get(0));
	                // current contour max height
	                one.add(currMax);
	                
	                res.add(one);
	                preMax = currMax;
	            }
	        }
	        
	        return res;
	    } 
	/*
	 class CoordinateComparator implements Comparator<List<Double>> {
	        public int compare(List<Double> l1, List<Double> l2) {
	            if (l1.get(0) < l2.get(0)) return -1;
	            else if (l1.get(0) > l2.get(0)) return 1;
	            else return 0;
	        }
	    }
	    
	    public List<List<Integer>> getSkyline(int[][] buildings) {
	        List<List<Integer>> res = new ArrayList<>();
	        
	        if (buildings.length == 0) {return res;}
	        // 1. separate left and right coordinate
	        Queue<List<Double>> sep = new PriorityQueue<>(new CoordinateComparator());
	        
	        for (int i = 0; i < buildings.length; i++) {
	        	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! left included, right excluded!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	        	List<Double> left = new ArrayList<>();
	            List<Double> right = new ArrayList<>();
                // add coordinate (left, val), (right, val)
                left.add(buildings[i][0] + 0.0);
                left.add(buildings[i][2] + 0.0);
                right.add(buildings[i][1] - 0.1);
                right.add(buildings[i][2] - 0.1);
	            
	            // enqueue
	            sep.offer(left);
	            sep.offer(right);
	        }
	        
	        // add to a list waiting to be fixed (value)
	        List<List<Double>> fix = new ArrayList<>();
	        while(!sep.isEmpty()) {
	            fix.add(sep.poll());
	        }
	        
	        // complement empty interval
	        for (int j = 0; j < fix.size() - 1; j++) {
	        	// j is right bound and j + 1 is left bound
	        	double jB = fix.get(j).get(0);
	        	double jBN = fix.get(j + 1).get(0);
	            if (((double) jB- (int)(double)jB) > 0 && ((double)jBN - (int)(double)jBN) == 0 &&
	            		jBN - jB > 0.1) {
	            	List<Double> empty = new ArrayList<>();
	            	empty.add(jB + 0.1);
	            	empty.add(0.0);
	            	fix.add(j + 1,empty);
	            }
	        }
	        
	        // fix the value
	        for (int i = 0; i < buildings.length; i++) {
	            int left = buildings[i][0];
	            int right = buildings[i][1];
	            int val = buildings[i][2];
	            
	            // fix array index of current building's left
	            int j = getValIndex(left, fix);
	            
	            for (; j != -1 && j < fix.size() && fix.get(j).get(0) < right; j++) {
	                // update value
	                fix.get(j).set(1, Math.max(fix.get(j).get(1), val));
	            }
	        }
	        
	        System.out.println(fix);
	        
	        
	        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!get res, add first!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	        int left = (int)(double)(fix.get(0).get(0)), val = (int)(double)(fix.get(0).get(1));
	        List<Integer> one = new ArrayList<>();
	        one.add(left);
	        one.add(val);
	        res.add(one);
	        int i;
	        for (i = 1; i < fix.size(); i++) {
	        	int last = res.get(res.size() - 1).get(1);
	        	int currVal = (int)(double)fix.get(i).get(1);
	        	if (last != currVal) {
	        		// if current is a right bound
	        		double coord = fix.get(i).get(0);
	        		one = new ArrayList<>();
	        		if(coord - (int)coord > 0) {
	        			int start = (int)Math.round((double)fix.get(i - 1).get(0));
	        			one.add(start);
	        		} else {
	        			// current is left bound
	        			one.add((int)(double)fix.get(i).get(0));
	        		}
	        		one.add(1, currVal);
	        		res.add(one);
	        	}
	        }
	        // add last
	        one = new ArrayList<>();
	        one.add((int)Math.round((double)fix.get(i - 1).get(0)));
	        one.add(0);
	        res.add(one);
	        
	        return res;
	        
	    }
	    
	    // use binary search to find left value in the fix
	    public int getValIndex(int target, List<List<Double>> fix) {
	        int l = 0, r = fix.size() - 1;
	        
	        while (l <= r) {
	            int mid = l + (r - l) / 2;
	            if (fix.get(mid).get(0) < target) {
	                l = mid + 1;
	            } else {
	                r = mid - 1;
	            }
	        }
	        
	        // 0 value pair
	        if (l >= fix.size() || fix.get(l).get(0) != target) return -1;
	        return l;
	    }
	    */
}

public class SkyLine {
    public static void main(String[] args) {
        SkyLineSolution s = new SkyLineSolution();
        int[][] buildings = new int[][] 
        		{{99,1001,2},{100,1001,1}};
        int[][] buildings3 = new int[][] {{9999,10001,2},{10000,10001,1}};
        List<List<Integer>> res = s.getSkyline(buildings);
        System.out.println(res);
    }
}