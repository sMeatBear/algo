import java.util.ArrayList;

class Solution {
    public int reverse(int x) {
        String str = x + "";
        StringBuilder strBuilder = new StringBuilder(str);
        strBuilder.reverse();
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        arrList.add(1);
        arrList.remove(arrList.size() - 1);
        return Integer.parseInt(strBuilder.toString());
    }
}