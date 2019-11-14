package app.search;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

class Solution01Matrix {
    class Node {
        Node parent;
        Integer[] coord;
        
        public Node(Node parent, Integer[] coord) {
            this.parent = parent;
            this.coord = coord;
        }
        public Node(Integer[] coord) {
            this.coord = coord;
        }
    }
    // bfs
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return matrix;
        
        boolean[][] computed = new boolean[matrix.length][matrix[0].length];
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0 && !computed[i][j]) {
                    // start point
                    // use queue to realize bfs
                    Deque<Node> queue = new ArrayDeque<>();
                    Deque<Integer> depth = new ArrayDeque<>();
                    
                    queue.offer(new Node(new Integer[] {i, j}));
                    depth.offer(0);
                    
                    boolean[][] visited = new boolean[matrix.length][matrix[0].length];
                    visited[i][j] = true;
                    // meet 0
                    boolean found = false;
                    // len
                    int len = Integer.MAX_VALUE;
                    // if computed lens
                    PriorityQueue<Integer> mins = new PriorityQueue<>();
                    mins.offer(Integer.MAX_VALUE);
    
                    while (!queue.isEmpty()) {
                        Node currNode = queue.pop(); 
                        Integer[] curr = currNode.coord;
                        int currDepth = depth.pop();
                        int r = curr[0], c = curr[1];
                        
                        // if it has been computed, then don't expand
                        if (computed[r][c]) {
                            mins.offer(matrix[r][c] + currDepth);
                            continue;
                        }
                        // if current depth exceeds the min length
                        if (currDepth >= mins.peek()) continue;
                            
                        Integer[][] nexts = new Integer[][] {
                            {r - 1, c}, // up
                            {r, c + 1}, // right
                            {r + 1, c}, // down
                            {r, c - 1}  // left
                        };
                        
                        
                        // next level's node
                        for (int k = 0; k < 4; k++) {
                            int nextR = nexts[k][0], nextC = nexts[k][1];
                            if (nextR >= 0 && nextR < matrix.length 
                                && nextC >= 0 && nextC < matrix[0].length && 
                               !visited[nextR][nextC]) {
                                // marked as visited
                                visited[nextR][nextC] = true;
                                // if reach the goal
                                if (matrix[nextR][nextC] == 0) {
                                    found = true;
                                    // shortest value
                                    Node p = currNode;
                                    int backLen =  1;
                                    
                                    while (p != null) {
                                        matrix[p.coord[0]][p.coord[1]] = backLen++;
                                        computed[p.coord[0]][p.coord[1]] = true;
                                        p = p.parent;
                                    }
                                    len = currDepth + 1;
                                    mins.offer(len);
                                    break;
                                }
                                // not meet 0
                                queue.offer(new Node(currNode, nexts[k]));
                                depth.offer(currDepth + 1);
                                
                            } // valid next
                            
                        } // end of next
                        
                        // find the shorest path
                        if (found) break;
                    }
                    // the shorest path value
                    matrix[i][j] = mins.peek();
                    computed[i][j] = true;
                } // end if
            }
        }
        
        return matrix;
    }
}

public class BFS01Matrix {
    public static void main(String[] args) {
        Solution01Matrix s = new Solution01Matrix();
        int[][] matrix = {
            {1,0,1,1,0,0,1,0,0,1},
            {0,1,1,0,1,0,1,0,1,1},
            {0,0,1,0,1,0,0,1,0,0},
            {1,0,1,0,1,1,1,1,1,1},
            {0,1,0,1,1,0,0,0,0,1},
            {0,0,1,0,1,1,1,0,1,0},
            {0,1,0,1,0,1,0,0,1,1},
            {1,0,0,0,1,1,1,1,0,1},
            {1,1,1,1,1,1,1,0,1,0},
            {1,1,1,1,0,1,0,0,1,1}
        };
        int[][] output = s.updateMatrix(matrix);
        for (int i = 0; i < output.length; i++) {
            System.out.println(Arrays.toString(output[i]));
        }
    }
}