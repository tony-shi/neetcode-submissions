class Solution {
    public int lastStoneWeight(int[] stones) {
        if(stones.length==1){
            return stones[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // 先加入进去。
        for(int i = 0;i<stones.length;i++){
            maxHeap.add(stones[i]);
        }

        while(maxHeap.size()!=1){
            int a = maxHeap.poll();
            int b = maxHeap.poll();
            if(a==b){
                if(maxHeap.size()==0){
                    return 0;
                }
            }else{
                maxHeap.add( a>b?(a-b):(b-a));
            }
        }

        return maxHeap.poll();
    }
}
