package app.search.no733_floodfill;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * An image is represented by a 2-D array of integers, each integer representing
 * the pixel value of the image (from 0 to 65535).
 * 
 * Given a coordinate (sr, sc) representing the starting pixel (row and column)
 * of the flood fill, and a pixel value newColor, "flood fill" the image.
 * 
 * To perform a "flood fill", consider the starting pixel, plus any pixels
 * connected 4-directionally to the starting pixel of the same color as the
 * starting pixel, plus any pixels connected 4-directionally to those pixels
 * (also with the same color as the starting pixel), and so on. Replace the
 * color of all of the aforementioned pixels with the newColor.
 * 
 * At the end, return the modified image.
 * 
 * Example 1: Input: image = [[1,1,1],[1,1,0],[1,0,1]] sr = 1, sc = 1, newColor
 * = 2 Output: [[2,2,2],[2,2,0],[2,0,1]] Explanation: From the center of the
 * image (with position (sr, sc) = (1, 1)), all pixels connected by a path of
 * the same color as the starting pixel are colored with the new color. Note the
 * bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel. Note:
 * 
 * The length of image and image[0] will be in the range [1, 50]. The given
 * starting pixel will satisfy 0 <= sr < image.length and 0 <= sc <
 * image[0].length. The value of each color in image[i][j] and newColor will be
 * an integer in [0, 65535].
 */
class Solution {
    // approach 2: dfs recursive
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length
                || image[sr][sc] == newColor) {
            return image;
        }

        dfs(image, sr, sc, newColor, image[sr][sc]);
        return image;
    }

    private static void dfs(int[][] image, int row, int col, int newColor, int oriColor) {
        if (row >= 0 && row < image.length && col >= 0 && col < image[0].length && image[row][col] == oriColor) {
            image[row][col] = newColor;
            // up
            dfs(image, row - 1, col, newColor, oriColor);
            // down
            dfs(image, row + 1, col, newColor, oriColor);
            // left
            dfs(image, row, col - 1, newColor, oriColor);
            // right
            dfs(image, row, col + 1, newColor, oriColor);

        }
    }

    // dfs iteratively
    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {
        // helper method to check if the given point is valid in the matrix
        if (image == null || !isValid(image, sr, sc)) {
            return image;
        }

        // initalize a stack and push the first
        Deque<int[]> stack = new ArrayDeque<>();
        boolean[][] visited = new boolean[image.length][image[0].length];
        int oriColor = image[sr][sc];
        image[sr][sc] = newColor;
        visited[sr][sc] = true;
        stack.push(new int[] { sr, sc });

        // dfs and set node as visited
        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            int currR = curr[0], currC = curr[1];

            // check the validation and the color for 4 directions
            // up
            int newR = currR - 1;
            if (isValid(image, newR, currC, oriColor) && !visited[newR][currC]) {
                // set visited
                visited[newR][currC] = true;
                image[newR][currC] = newColor;
                stack.push(new int[] { newR, currC });
            }
            // right
            int newC = currC + 1;
            if (isValid(image, currR, newC, oriColor) && !visited[currR][newC]) {
                // set visited
                visited[currR][newC] = true;
                image[currR][newC] = newColor;
                stack.push(new int[] { currR, newC });
            }
            // down
            newR = currR + 1;
            if (isValid(image, newR, currC, oriColor) && !visited[newR][currC]) {
                // set visited
                visited[newR][currC] = true;
                image[newR][currC] = newColor;
                stack.push(new int[] { newR, currC });
            }
            // left
            newC = currC - 1;
            if (isValid(image, currR, newC, oriColor) && !visited[currR][newC]) {
                // set visited
                visited[currR][newC] = true;
                image[currR][newC] = newColor;
                stack.push(new int[] { currR, newC });
            }
        }

        return image;
    }

    // check if the coordinate is valid
    public static boolean isValid(int[][] image, int row, int col) {
        return row >= 0 && row < image.length && col >= 0 && col < image[0].length;
    }

    public static boolean isValid(int[][] image, int row, int col, int oriColor) {
        return row >= 0 && row < image.length && col >= 0 && col < image[0].length && image[row][col] == oriColor;
    }
}

public class FloodFill {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] image = new int[][] { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
        int sr = 1, sc = 1, newColor = 2;
        System.out.println(Arrays.deepToString(sol.floodFill(image, sr, sc, newColor)));
    }
}