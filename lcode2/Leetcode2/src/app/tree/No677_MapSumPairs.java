package app.tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class MapSum {
    Map<String, Integer> map;

    /** Initialize your data structure here. */
    public MapSum() {
        map = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        map.put(key, val);
    }
    
    public int sum(String prefix) {
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        int sum = 0;

        // traverse the keyset find the prefix
        while (it.hasNext()) {
            String str = it.next();
            if (str.startsWith(prefix)) {
                sum += map.get(str);
            }
        }

        return sum;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */

public class No677_MapSumPairs {
    public static void main(String[] args) {
        MapSum ms = new MapSum();
        ms.insert("apple", 3);
        ms.insert("apples", 2);
        ms.insert("aappe", 1);

        System.out.println(ms.sum("ap"));
    }
}