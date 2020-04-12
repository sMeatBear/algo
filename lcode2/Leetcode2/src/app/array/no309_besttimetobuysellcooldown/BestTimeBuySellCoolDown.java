package app.array.no309_besttimetobuysellcooldown;

class Solution {
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) return 0;
        
        /*
         */
        int rest = 0, buy = -prices[0], sell = 0;
        for (int i = 1; i < prices.length; i++) {
            // update rest, buy and sell if it happens at day i
            int newRest = Math.max(rest, sell);
            // buy at this time
            int newBuy = Math.max(rest - prices[i], buy);
            // sell at this time
            int newSell = buy + prices[i];
            
            rest = newRest;
            buy = newBuy;
            sell = newSell;
        }
        
        return Math.max(sell, rest);
    }
    
    // failed
    public int maxProfit1(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) return 0;
        // perserve previous increase part and compare with current one
        int maxProfit = 0;
        int curMinIdx = -1, prevMinIdx = -1, prevMaxIdx = -1, prevIncrease = 0;
        
        // find the first part increase part
        int i = 1;
        while (i < prices.length) {
            int curIncrease = 0;
            // find minima
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            if (i == prices.length - 1) break;
            
            curMinIdx = i;
            curIncrease = -prices[curMinIdx];
            
            // find maxima
            while (i < prices.length && prices[i - 1] <= prices[i]) {
                i++;
            }
            
            int curMaxIdx = i - 1;
            curIncrease += prices[curMaxIdx];
            if (prevMaxIdx != -1 && curMinIdx - prevMaxIdx == 1) {
                maxProfit -= prevIncrease;
                int curGiveIn = prices[curMinIdx + 1] - prices[curMinIdx];
                int prevGiveIn = prices[prevMaxIdx] - prices[prevMaxIdx - 1];

                // three conditions:
                int tmpCur = 0, tmpPrev = 0;
                int tmpTotal = prices[curMaxIdx] - prices[prevMinIdx];
                if ((tmpCur = prevIncrease - prevGiveIn + curIncrease) >= 
                    (tmpPrev = curIncrease - curGiveIn + prevIncrease)) {
                        if (tmpCur >= tmpTotal) {
                            maxProfit += tmpCur;
                            // update
                            prevMaxIdx = curMaxIdx;
                            prevIncrease = tmpCur;
                            prevMinIdx = curMinIdx;
                        } else {
                            maxProfit += tmpTotal;
                            prevMaxIdx = curMaxIdx;
                            prevIncrease = tmpTotal;
                        }
                } else {
                    if (tmpPrev >= tmpTotal) {
                        maxProfit += tmpPrev;
                        prevIncrease = tmpPrev;
                    } else {
                        maxProfit += tmpTotal;
                        prevMaxIdx = curMaxIdx;
                        prevIncrease = tmpTotal;
                    }
                }
            } else {
                maxProfit += curIncrease;
                // update
                prevMaxIdx = curMaxIdx;
                prevIncrease = curIncrease;
                prevMinIdx = curMinIdx;
            }
            
        }
        
        return maxProfit;
    }
}

public class BestTimeBuySellCoolDown {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] prices = new int[] {2,3,5,8,3,8,2,6};
        System.out.println(s.maxProfit(prices));
    }
}