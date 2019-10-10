package app.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public double calculateMaxInfoGain(List<Double> petal_length, List<String> species) {
        double HL = H(species);
        
        double max = 0;
        
        for (double bound : petal_length) {
            List<String> l1 = new ArrayList<>();
            List<String> l2 = new ArrayList<>();    
            for (int i = 0; i < species.size(); i++) {
                if (petal_length.get(i) <= bound) {
                    l1.add(species.get(i));                    
                } else {
                    l2.add(species.get(i));
                }
            }
            max = Math.max(max, gain(HL, species.size(), l1, l2));
        }        
        
        return max;
    }
    
    public double gain(double total, int mainSize, List<String> l1, List<String> l2) {
        double HL1 = 0, HL2 = 0;
        if (l1.size() != 0) {
            HL1 = mainSize / l1.size() * H(l1);
        }
        
        if (l2.size() != 0) {
            HL2 = mainSize / l2.size() * H(l2);
        }
        return total - HL1 - HL2;
    }
    
    public double H(List<String> species) {
        Map<String, Integer> count = new HashMap<>();
        
        for (int i = 0; i < species.size(); i++) {
            count.put(species.get(i), count.getOrDefault(species.get(i), 0) + 1);
        }
        
        double log2 = Math.log(2);
        double entropy = 0;
        double size = species.size();
        for (int val : count.values()) {
            double p = (val) / size;
            entropy = - p * (Math.log(p) / log2); 
        }
        
        return entropy;
    }
}

public class Gain {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<Double> length = new ArrayList<>();
        List<String> species = new ArrayList<>();
        species.add("a");
        species.add("b");
        species.add("a");
        species.add("b");
        length.add(0.5);
        length.add(2.3);
        length.add(1.0);
        length.add(1.5);

        
        System.out.println(s.calculateMaxInfoGain(length, species));
    }
}