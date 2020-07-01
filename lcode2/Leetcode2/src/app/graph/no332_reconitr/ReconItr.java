package app.graph.no332_reconitr;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival
 * airports [from, to], reconstruct the itinerary in order. All of the tickets
 * belong to a man who departs from JFK. Thus, the itinerary must begin with
 * JFK.
 * 
 * Note:
 * 
 * If there are multiple valid itineraries, you should return the itinerary that
 * has the smallest lexical order when read as a single string. For example, the
 * itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"]. All
 * airports are represented by three capital letters (IATA code). You may assume
 * all tickets form at least one valid itinerary. One must use all the tickets
 * once and only once. Example 1:
 * 
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"] Example 2:
 * 
 * Input:
 * [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"] Explanation: Another possible
 * reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in
 * lexical order.
 */

class Solution {
    // dfs
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new ArrayList<>();
        // sanity check
        if (tickets == null || tickets.size() == 0) {
            return result;
        }
        
        Map<String, List<String>> graph = new HashMap<>();
        // build the graph
        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            List<String> edge = graph.get(from);
            if (edge == null) {
                edge = new ArrayList<>();
                graph.put(from, edge);
            }
            edge.add(to);
        }
        
        // dfs from JFK
        // record edges
        Set<String> visited = new HashSet<>();
        String start = "JFK";
        Deque<String> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            String currFrom = stack.pop();
            result.add(currFrom);
            List<String> to = graph.get(currFrom);
            if (to != null) {
                to.sort(Collections.reverseOrder());
                for (String t : to) {
                    if (visited.add(currFrom + t)) {
                        stack.push(t);
                    }
                }
            }
        }
        
        return result;
    }
}

public class ReconItr {
    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList(new String[] {"JFK","SFO"}));
        tickets.add(Arrays.asList(new String[] {"JFK","ATL"}));
        tickets.add(Arrays.asList(new String[] {"SFO","ATL"}));
        tickets.add(Arrays.asList(new String[] {"ATL","JFK"}));
        tickets.add(Arrays.asList(new String[] {"ATL","SFO"}));
        System.out.println(sol.findItinerary(tickets));
    }
}