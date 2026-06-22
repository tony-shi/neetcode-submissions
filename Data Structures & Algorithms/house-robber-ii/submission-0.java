class Solution {
    public int rob(int[] nums) {
        // 仍旧是DP，只是分两种方式处理。
        // 第一间我们偷了。并且，我们偷了第N个。注意，这里的话，first[1] = NIL，也就是说，这个是不合法的，要忽略它？
        // 第一间，我们不偷。并且我们偷了第N个。这里的话，所有的都是OK的？那这种场景，自然的first[0] = NIL。
        // 之后的话，就是两者的最大值。我的理解。


        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        if(nums.length==2) return Math.max(nums[0],nums[1]);


        int[] stolenFirst   = new int[nums.length];
        {
            stolenFirst[0] = nums[0];
            // 标记一下，说明是一个非法值。
            stolenFirst[1] = -1; 
            stolenFirst[2] = nums[0]+nums[2];
            // 标记一下，说明是一个非法值。
            stolenFirst[nums.length-1] = -1;
        }
        int[] noStolenFirst = new int[nums.length];
        {
            //第一个不偷。那么最后一个就可以偷了。
            // 第一个不偷的话，没有值，就叫做-1，标记永远不会选上。
            noStolenFirst[0] = -1;
            // 标记一下，说明是一个非法值。
            noStolenFirst[1] = nums[1]; 
            noStolenFirst[2] = nums[2];
        }


        // 之后的话，就可以计算了。除了关注一下最后一个值，一定是不合法之外。
        for(int i = 3; i< nums.length-1;i++){
            // 因为非法的值，我们设置为了-1，永远不会命中，所以似乎是OK的。
            stolenFirst[i] = Math.max(stolenFirst[i-2],stolenFirst[i-3]) + nums[i];
        }



        // 之后的话，就可以计算了。除了关注一下最后一个值，一定是不合法之外。
        for(int i = 3; i< nums.length;i++){
            // 因为非法的值，我们设置为了-1，永远不会命中，所以似乎是OK的。
            noStolenFirst[i] = Math.max(noStolenFirst[i-2],noStolenFirst[i-3]) + nums[i];
        }

        int ans = 0;
        for(int i=0;i<nums.length;i++){
            ans = Math.max(ans,stolenFirst[i]);
            ans = Math.max(ans,noStolenFirst[i]);
        }
        return ans;
    }
}
