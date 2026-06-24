class Solution {



    // 多次买入。
    // 买入后，你需要锁定一天不能买入。
    // 求最大值。
    // 如果没有锁定，很显然，是一个单调栈。
    // 这个没问题。但是单调栈出现后，会影响后续的决策了。

    // 如何处理呢？


    // 不考虑任何cool down。自然是增加。
    // 考虑cool down呢？需要一个递推的架构逻辑。

    // 假设，我们已经知道了，在

    // sell[i] 在第i个卖出，之后，能够累计获得点收入值。
    // noSell[i]，不论在第i个卖出，在第i不做卖出，对应可以获得的最大的收入值。

    // ans[i] 实际上就是这两者的max值。



    // noSell[i] = max(noSell[i-1],sell[i-1]) ？
    // sell[i] = max( noSell[k-1] + (prices[i]-prices[k]) )
    //  max (    noSell[k-1] +    prices[i]-prices[k])


    // k的取值呢？k可以从0开始的？
    // 注意这里，no sell的话，


    // 差了一步这个递推的共识。

    // sell[i] = max (noSell[k]-prices[k]) + prices[i]
    // 这里的话，实际上就是，所有的


    // 考虑这个逻辑，既然我们在这个i卖出，对吧。那么往前找？
    // 首先他的这个收益得小于这个i。这个没问题？
    // 这个可能就是说有多个决策了。对吧？
    // 然后，我们就得去找，前面的值的最大的值？
    //



    // 
    public int maxProfit(int[] prices) {
        if(prices.length==0 || prices.length==1){
            return 0;
        }


        int[] noSell = new int[prices.length];
        int[] sell = new int[prices.length];

        // noSell[k]-prices[k]

        // 第一个的话，没有什么意义。
        noSell[0] = 0;
        sell[0] = 0;
        // 第二个元素的话，开始分析
        noSell[1] = 0;
        sell[1] = (prices[1]-prices[0]);

        int kthMax = noSell[0]-prices[1];


        for(int i = 2;i<prices.length;i++){
            noSell[i] = Math.max(noSell[i-1],sell[i-1]);
            kthMax = Math.max(kthMax, noSell[i-2]-prices[i-1]);
            sell[i] = Math.max(kthMax,-1 * prices[0])+ prices[i];
        }





        return Math.max(Math.max(noSell[prices.length-1],sell[prices.length-1]),0);
    }
}
