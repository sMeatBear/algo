import java.util.*;

class SolutionRecursion {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        
        if (rowIndex == 0) {
            res.add(1);
            return res;   
        }
        
        if (rowIndex == 1) {
            res.add(1);
            res.add(1);
            return res;
        }
        
        res = getRow(rowIndex - 1);
        for (int i = 1; i < rowIndex + 1; ) {
            res.add(i, res.get(i - 1) + res.get(i));
            if (i == 1) {
                i = i + 2;
            } else {
                res.remove(i - 1);
                i++;
            }
        }
        return res;
    }

    public List<Integer> getRowByDeque (int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<Integer> rows = new ArrayDeque<Integer>();

        // row = 0 or 1
        if (rowIndex == 0) return res;
        if (rowIndex == 1) {
            res.add(1);
            return res;
        }

        // initiate
        Collections.addAll(rows, new Integer[]{0, 1, 0});

        // use queue to form the triangle, starting with first row deque
        // i is current deque row num, and j is jth element of ith row, plus 1 is for last row seperator 
        for (int i = 1; i < rowIndex - 1; i++) {
            for (int j = 0; j < i + 1; j++) {
                rows.add(rows.poll() + rows.peek());
            }
            // row seperator
            rows.add(0);
        }
        // handle last row
        for (int i = 0; i < rowIndex; i++) {
            res.add(rows.poll() + rows.peek());
        }

        return res;
    }
}

public class YanghuiTri {
    public static void main(String[] args) {
        SolutionRecursion s = new SolutionRecursion();
        System.out.println(s.getRowByDeque(3));
    }
}