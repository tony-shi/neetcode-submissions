// Hand of Straights
// Medium
// Topics
// Company Tags
// Hints
// You are given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize.

// You want to rearrange the cards into groups so that each group is of size groupSize, and card values are consecutively increasing by 1.

// Return true if it's possible to rearrange the cards in this way, otherwise, return false.

// Example 1:

// Input: hand = [1,2,4,2,3,5,3,4], groupSize = 4

// Output: true
// Explanation: The cards can be rearranged as [1,2,3,4] and [2,3,4,5].

// Example 2:

// Input: hand = [1,2,3,3,4,5,6,7], groupSize = 4

// Output: false
// Explanation: The closest we can get is [1,2,3,4] and [3,5,6,7], but the cards in the second group are not consecutive.

// Constraints:

// 1 <= hand.length <= 1000
// 0 <= hand[i] <= 1000
// 1 <= groupSize <= hand.length


// hand=[1,2,4,2,3,5,3,4]
// groupSize=4

// 

class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        // 一个逻辑上，要是groupSize不对称，我理解直接不用判断了？
        if(hand.length % groupSize !=0){
            return false;
        }

        // 仍旧是一个变种。
        // 也就是只看最小的，然后从最小的，找紧密挨着的。

        // 时间复杂度分析，逻辑上，TreeMap的add的话，应该是lgn。
        // 查询第一个O（1）

        // 那么就是先add，对应的应该是n * lgn了？
        // 可以。
        // hand【i】，因为有限度，就是0-1000，从这个角度上说，似乎可以更快？
        // 不太行吧，因为你要找最小的？
        // 每次找最小的这个动作不太好做？
        // 先考虑treemap把，解决了再说。
        TreeMap<Integer,Integer> counts = new TreeMap<>();
        for(int i = 0;i<hand.length;i++){
            if(!counts.containsKey(hand[i])){
                counts.put(hand[i],0);
            }
            counts.put(hand[i],counts.get(hand[i])+1);
        }


        // 初始化完毕，开启查询。
        // 获取treemap里面的最小值，之后开始走。
        int processedCount = 0;
        int current = -1;
        for(int i = 0;i<groupSize && processedCount < hand.length;){
            // print(counts);
            if(i==0){
                // 第一个，找tree map中最小的。
                 current = counts.firstKey();
                //  System.out.println(current);
                 if(counts.get(current)==1){
                    counts.remove(current);
                 }else{
                    counts.put(current,counts.get(current)-1);
                 }
                 current++;
            }else{
                // 现在，让我们找这个current的
                if(!counts.containsKey(current)){
                    return false;
                }
                if(counts.get(current)==1){
                    counts.remove(current);
                }else{
                    counts.put(current,counts.get(current)-1);
                }
                current++;
            }
            processedCount++;
            if(i==groupSize-1){
                // 就绪找
                i = 0;
            }else{
                i++;
            }
        }
        return true;        
    }

    public void print(TreeMap<Integer,Integer> map){
        for(Integer key : map.keySet()){
            System.out.print(key +":"+map.get(key)+",");
        }
        System.out.println();
    }
}



