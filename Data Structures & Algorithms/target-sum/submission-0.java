class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        Map<Integer,Integer> counts = new HashMap<>();
        counts.put(0,1);
        for(int i = 0;i<nums.length;i++){
            Map<Integer,Integer> next = new HashMap<>();
            for(Integer value: counts.keySet()){
                Integer count = counts.get(value);
                if(!next.containsKey(value+nums[i])){
                    next.put(value+nums[i],0);
                }
                if(!next.containsKey(value-nums[i])){
                    next.put(value-nums[i],0);
                }
                next.put(value+nums[i],next.get(value+nums[i])+count);
                next.put(value-nums[i],next.get(value-nums[i])+count);
            }
            counts = next;
        }
        if(!counts.containsKey(target)){
            return 0;
        }
        return counts.get(target);
    }
}
