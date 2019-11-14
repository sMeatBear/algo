package app.search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

class SolutionKeysandRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // use bfs
        Deque<Integer> queue = new ArrayDeque<>();
        int numVisited = 0;
        int numRoom = rooms.size();
        boolean visited[] = new boolean[numRoom];
        
        
        // initialize
        queue.offer(0);
        visited[0] = true;
        numVisited++;
        // bfs
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int nextRoom : rooms.get(curr)) {
                if (!visited[nextRoom]) {
                    visited[nextRoom] = true;
                    numVisited++;
                    if (numVisited == numRoom) return true;
                    queue.offer(nextRoom);
                }
            }
        }
        
        if (numRoom == numVisited) return true;
        return false;
    }
}

public class KeysRooms {
    public static void main(String[] args) {
        SolutionKeysandRooms s = new SolutionKeysandRooms();

        List<Integer> l1 = new ArrayList<>(Arrays.asList(new Integer[]{1,3})),
        l2 = new ArrayList<>(Arrays.asList(new Integer[]{3,0,1})),
        l3 = new ArrayList<>(Arrays.asList(new Integer[]{2})),
        l4 = new ArrayList<>(Arrays.asList(new Integer[]{2}));
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(l1);
        rooms.add(l2);
        rooms.add(l3);
        rooms.add(l4);
       
        System.out.println(s.canVisitAllRooms(rooms));
    }
}