package app.graph.no787_cheapestflight;

import java.util.Arrays;

/**
 * There are n cities connected by m flights. Each flight starts from city u and
 * arrives at v with a price w.
 * 
 * Now given all the cities and flights, together with starting city src and the
 * destination dst, your task is to find the cheapest price from src to dst with
 * up to k stops. If there is no such route, output -1.
 * 
 * Example 1: Input: n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]] src = 0, dst
 * = 2, k = 1 Output: 200 Explanation: The graph looks like this:
 * 
 * 
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as
 * marked red in the picture. Example 2: Input: n = 3, edges =
 * [[0,1,100],[1,2,100],[0,2,500]] src = 0, dst = 2, k = 0 Output: 500
 * Explanation: The graph looks like this:
 * 
 * 
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as
 * marked blue in the picture.
 * 
 * 
 * Constraints:
 * 
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0 to
 * n - 1. The size of flights will be in range [0, n * (n - 1) / 2]. The format
 * of each flight will be (src, dst, price). The price of each flight will be in
 * the range [1, 10000]. k is in the range of [0, n - 1]. There will not be any
 * duplicated flights or self cycles.
 */

class Solution {
    // approach 1: bellman-ford
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] prices = new int[n];
        // all inaccessiable at first
        Arrays.fill(prices, Integer.MAX_VALUE);
        // set the src price to be 0
        prices[src] = 0;
        for (int k = 0; k <= K; k++) {
            int[] temp = Arrays.copyOf(prices, n);
            for (int[] flight : flights) {
                int curr = flight[0], next = flight[1], price = flight[2];
                if (prices[curr] != Integer.MAX_VALUE) {
                    temp[next] = Math.min(temp[next], prices[curr] + price);
                }
            }
            // update prices
            prices = temp;
        }
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }
}

public class CheapestFlight {
    
}