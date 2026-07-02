// Subarray Sum Equals K
// Medium
// Topics
// Company Tags
// You are given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

// A subarray is a contiguous non-empty sequence of elements within an array.

// Example 1:

// Input: nums = [2,-1,1,2], k = 2

// Output: 4
// Explanation: [2], [2,-1,1], [-1,1,2], [2] are the subarrays whose sum is equals to k.

// Example 2:

// Input: nums = [4,4,4,4,4,4], k = 4

// Output: 6
// Constraints:

// 1 <= nums.length <= 20,000
// -1,000 <= nums[i] <= 1,000
// -10,000,000 <= k <= 10,000,000


class Solution {
    public int subarraySum(int[] nums, int k) {
        // 1 非空
        // 2 要求连续


        // 也就是说，估计不能排序？排序了就乱序了。
        // 考虑 sum[i,j] 二维的话，逻辑上我们就是扫描一下，看有多少个=k的就可以了？
        // 而且只用看对角。也就是i<=j，对角线上的。
        // 时间复杂度o(n^2)？
        // 应该不是这样吧？这样太简单了。
        // 还能不能更快？


        // 现在，多了一个元素第j个了。
        //  前面的，不包含这个j的，已经知道了。
        // 包含j的呢？每一个你都得更新啊，否则下一个怎么记录呢？
        // 看上去，我估计最快也就是o（n^2）了，你还得输出呢？
        // 不考虑空间问题。先落地。
        // 第i行的，第j个，只取决于前面的一个了。似乎，逻辑上能优化吗？
        // 总是感觉不行啊。

        int ans = 0;
        int[] sum = new int[nums.length];
        // 爆了内存了。优化。
        for(int i = 0;i< nums.length;i++){
            sum[i] = nums[i];
            if(sum[i]==k){
                ans++;
            }
            for(int j = i+1;j<nums.length;j++){
                sum[j] = sum[j-1]+nums[j];
                if(sum[j]==k){
                    ans++;
                }
            }
        }
        return ans;
    }
}
