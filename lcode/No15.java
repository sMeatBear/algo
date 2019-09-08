import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

class Solution15 {
    /**
     * Read a file from outer
     * 
     * @param f     Get the filepath
     */
    public int[] readFile(String filePath) {
        try {
            // 1. find the file
            File f = new File(filePath);
            // 2. build the scanner
            Scanner sc = new Scanner(f);
            // storage
            ArrayList<Integer> list = new ArrayList<Integer>();
            // 3. define delimiters using regex
            sc.useDelimiter(Pattern.compile("[\\[\\],]"));

            // 4. read data
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
            int[] res = list.stream().mapToInt(i -> i).toArray();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /*
    public boolean isGroupExisted(List<List<Integer>> res, List<Integer> current) {
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).equals(current))
                return true;
        }
        return false;   
    }
    */
    
    
    public List<List<Integer>> threeSum(int[] nums) {
        // use hashmap to construct O(n^2) algo
        HashSet<List<Integer>> res = new HashSet<List<Integer>>();
        Map<Integer, Integer> map  = new HashMap<Integer, Integer>();
        
        Arrays.sort(nums);

        // construct hashmap
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        
        
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                /* brute force
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> temp = new ArrayList<Integer>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        if (!isGroupExisted(res, temp)) res.add(temp);
                    }
                }
                */
               
                int exp = 0 - nums[i] - nums[j];
                if (map.containsKey(exp)) {
                    int index = map.get(exp);
                    if (index != i && index != j) {
                        List<Integer> temp = new ArrayList<Integer>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(exp);
                        Collections.sort(temp);
                        res.add(temp);
                    }
                }
            }
        }
        return new ArrayList<List<Integer>>(res);
    }
}
public class No15 {
    public static void main(String[] args) {
        Solution15 s = new Solution15();
        // int[] nums = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        
        // test read file
        int[] nums = s.readFile("no15_input.txt");
        System.out.println(s.threeSum(nums));
    }
}