import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String[] find (String[] list1, String[] list2) {
        // result
        ArrayList<String> res = new ArrayList<String>();
        int minSum = list1.length + list2.length;
        // Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, Integer> map2 = new HashMap<String, Integer>();
        
        // construct map to accelerate search process
        for (int i = 0; i < list2.length; i++) map2.put(list2[i], i);
        
        for (int i = 0; i < list1.length; i++) {
            if (map2.containsKey(list1[i])) {
                int j = map2.get(list1[i]);
                int current = i + j;
                if (current < minSum) {
                    minSum = current;
                    res = new ArrayList<String>();
                    res.add(list1[i]);
                } else if (current == minSum) {
                    res.add(list1[i]);
                }
            }
        }

        return res.toArray(new String[res.size()]);
    }
    
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1.length == 0 || list2.length == 0) return new String[]{};
        
        if (list1.length > list2.length) return find(list2, list1);
        else return find(list1, list2);
    }
}

public class No {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] str1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] str2 = {"Tapioca Express", "Shogun", "KFC", "Burger King"};
        // List<String> list1 = new ArrayList<String>(Arrays.asList(str1));
        // List<String> list2 = new ArrayList<String>(Arrays.asList(str2));
        String[] res = s.findRestaurant(str1, str2);
        for (String str : res)
            System.out.println(str);
    }
}