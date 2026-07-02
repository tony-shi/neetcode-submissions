// Asteroid Collision
// Medium
// Topics
// Company Tags
// You are given an array asteroids of integers representing asteroids in a row. 
// The indices of the asteriod in the array represent their relative position in space.

// For each asteroid, the absolute value represents its size, 
// and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

// Find out the state of the asteroids after all collisions. 
// If two asteroids meet, the smaller one will explode. 
// If both are the same size, both will explode. 
// Two asteroids moving in the same direction will never meet.

// Example 1:

// Input: asteroids = [2,4,-4,-1]

// Output: [2]
// Example 2:

// Input: asteroids = [5,5]

// Output: [5,5]
// Example 3:

// Input: asteroids = [7,-3,9]

// Output: [7,9]
// Constraints:

// 2 <= asteroids.length <= 10,000.
// -1000 <= asteroids[i] <= 1000
// asteroids[i] != 0



// 显然，一个方向上的，似乎就不用看。
// 例如，你可以拆分开？
// 之后的话，去看


// 1 3 2      -2 -4 -1

// 最后的话，1是计算剩余的size的？
// 注意，相对位置显然是有用的，我的感觉。

// 考虑这个某一个元素

// i->看这边的负的值。-1 -2 -4
// 考虑一个最大的i，这边有很多负的值。
// 然后，去一个一个走？
// i  -1 -2 -4
// 如果销毁了，那就往这边走？
// 这个时候，变成了一个比较大的？
// 然后呢？
// 1 -1 2 -3 4 -1 5 -2 7 -1 -11
// 放上这个面向左侧的id
// 关键是什么，处理完了之后的话，右侧的逻辑似乎变了？

// 因为，似乎你得新的填充新的元素了？

// 也就是，每一个的元素，他应该只看比他大的感觉？
// 但是很多被下掉了，这个怎么实现？
// 处理的时候加入进去？
// 似乎是可以的？

// 那为什么从这个开始呢？
// 如果任意开始？似乎不行？
// 因为被前面的挡掉了？


// 两个stack？
// 先走到一个特定的点

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> leftStack = new LinkedList<>();
        LinkedList<Integer> rightStack = new LinkedList<>();
        for(int i = 0;i<asteroids.length;i++){
            if(asteroids[i]>0){
                leftStack.add(i);
            }
        }
        int initReachIndex = leftStack.size()==0?-1:leftStack.getLast();
        for(int i = asteroids.length-1 ;i>initReachIndex;i--){
            rightStack.add(i);
        }


        // OK，现在栈已经ready了。开始推进了。
        LinkedList<Integer> ans = new LinkedList<>();
        while(leftStack.size()!=0){
            if(rightStack.size()==0){
                // 出，因为现在已经不需要了？
                ans.addFirst(asteroids[leftStack.getLast()]);
                int leftIndex = leftStack.removeLast();
                int reachIndex = leftStack.size()==0? -1 :leftStack.getLast();
                for(int i = leftIndex-1;i>reachIndex;i--){
                    // 这里，一定是负数！
                    rightStack.add(i);
                }

                continue;
            }
            // 比较顶部。
            if(asteroids[leftStack.getLast()] > -1 * asteroids[rightStack.getLast()]){
                // OK，left的话，比-1要大。这个时候，右侧被销毁了。
                rightStack.removeLast();
            }else{
                if(asteroids[leftStack.getLast()] == -1 * asteroids[rightStack.getLast()]){
                    rightStack.removeLast();
                    // 注意，现在要更新了。
                }

                // print(leftStack);
                int leftIndex = leftStack.removeLast();
                // print(leftStack);
                int reachIndex = leftStack.size()==0? -1 :leftStack.getLast();
                for(int i = leftIndex-1;i>reachIndex;i--){
                    // 这里，一定是负数！
                    // System.out.println("add "+i);
                    rightStack.add(i);
                }
            }
        }
        // 最终，已经加入完了？
        // 把右边的堆栈加入进去。
        // 逐步getlast？
        for(Integer right : rightStack){
            ans.addFirst(asteroids[right]);
        }

        int[] nums = new int[ans.size()];
        int  i = 0;
        for(Integer element : ans ){
            nums[i++] =  element;
        }
        return nums;
    }

    public void print(LinkedList<Integer> stack){
        for(Integer i : stack){
            System.out.print(i);
        }
        System.out.println();
    }

}