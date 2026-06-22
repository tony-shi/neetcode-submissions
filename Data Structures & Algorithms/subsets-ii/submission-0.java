class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Map<Integer,Integer> counts = new HashMap<>();
        for(int i =0;i<nums.length;i++){
            if(counts.containsKey(nums[i])){
                counts.put(nums[i],counts.get(nums[i]) + 1);
            }else{
                counts.put(nums[i],1);
            }
        }


        List<List<Integer>> result = new ArrayList<>();
        result.add(new LinkedList<>());

        for(Integer num : counts.keySet()){
            int preResultSize = result.size();
            int count = counts.get(num);
            int processed = 0;
            while(processed < preResultSize){
                for(int i =1;i<=count;i++){
                    List<Integer> oneAns = result.get(processed);
                    // print(oneAns);
                    List<Integer> newAns = add(oneAns,i,num);
                    // print(newAns);
                    result.add(newAns);
                }
                processed++;
            }
            // 额外的，前面的，100%是空的呢？这个得有？所以少了一个？
        }
        return result;
    }

    public List<Integer> add(List<Integer> list,int count,int num){
        List<Integer> copy  = new LinkedList<Integer>(list);
        for(int i = 0;i<count;i++){
            copy.add(num);
        }
        return copy;
    }

    public void print(List<Integer> list){
        for(Integer value : list){
            System.out.print(value+" ");
        }
        System.out.println();

    }
}
