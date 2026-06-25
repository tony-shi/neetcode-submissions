class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<List<Integer>>> sum = new ArrayList<>();
        for(int i = 0;i<=target;i++){
            sum.add(new ArrayList<>());
        }

        List<Integer> empty = new ArrayList<>();
        sum.get(0).add(empty);


        Map<Integer,Integer> counts = new HashMap<>();
        for(int i =0;i<candidates.length;i++){
            if(counts.containsKey(candidates[i])){
                counts.put(candidates[i],counts.get(candidates[i])+1);
            }else{
                counts.put(candidates[i],1);
            }
        }

        // 按照顺序，开始处理每一个元素。
        for(Integer key : counts.keySet()){
            Integer count = counts.get(key);

            // 从最高位的target 开始，倒序开始更新。
            for(int sumValue = target; sumValue>=0;sumValue--){
                // 累计添加count次数。从1，到count次。
                List<List<Integer>> sumValueList = sum.get(sumValue);
                // 对于每一个结果，分别去append，从1-N个这样的元素。然后更新到对应的sum。
                // 逻辑上，同样应该是从后面开始更新，避免和前面的重复产生冲突。
                for(int i = count;i>0;i--){
                    if(sumValue + i * key <= target){
                        for(List<Integer> original : sumValueList){
                           sum.get((sumValue + i * key)).add(copyAndAdd(original,i,key));
                        }
                    }
                }
                
            }
        }


        return sum.get(target);
    }


    public List<Integer> copyAndAdd(List<Integer> list,int k, int number){
        List<Integer> result = new ArrayList<>();
        for(Integer i : list){
            result.add(i);
        }
        for(int i = 0;i<k;i++){
            result.add(number);
        }
        return result;
    }
}
