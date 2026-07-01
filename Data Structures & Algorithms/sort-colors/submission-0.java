class Solution {
    public void sortColors(int[] nums) {
        int nextZeroIndex = 0;
        int nextTwoIndex = nums.length-1;
        int i = 0;
        while(i<=nextTwoIndex){
            if(nums[i]==0){
                swap(nums,i,nextZeroIndex);
                if(i==nextZeroIndex){
                    i++;
                }
                nextZeroIndex++;
                continue;
            }
            if(nums[i]==1){
                i++;
                continue;
            }
            if(nums[i]==2){
                swap(nums,i,nextTwoIndex);
                nextTwoIndex--;
                continue;
            }
        }   
    }

    public void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}