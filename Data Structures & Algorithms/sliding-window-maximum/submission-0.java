// OK。考虑之前的这个o（1）输出栈的解法。
// 这个没问题。因为加入/弹出都是一个状态。
// 所以说，你在加入的时候判断一下，那么弹出的时候自然的话，就把这个状态弹出了。
// 但是目前的话，考虑这个结构呢？
// 一个队列。左边弹出，右边加入。

// 右边加入，比较一下和顶部的，然后塞入，这个似乎问题不大。
// 但是左边弹出，这个逻辑复杂。破坏了结构。所以这个不行。

// 重新思考。


// 加入的时候，如果状态是最大，塞入，问题不大？
// pop出去，比较麻烦，要思考一下。pop出去，如果这个怎么办呢？逻辑上，他应该得更新一下其他的值才行了？
// 这个就不能接受了。我们一定得处理好呢？
// 左侧只是出。右侧是加入。

// 存储这个最大值的index呢？


// List<Node>
// 存储这个当前节点的index，以及说值。插入进去。
// 这个时候，有一个清理的逻辑。把所有比他小的，都可以删掉了。
// 因为，这些前面的节点，比他小的，完全没有贡献作用我的理解。
// 而且你这个就是顺序的删除。从右侧开始往前，知道删干净。
// 然后的话，这个结构就足够回答了。
// 再之后，增加一个，那么就update这队列。
// 删除一个，这个时候，OK。我们就看下顶部是不是？是的话，那就移除。然后返回顶部元素。这样就OK了。
// 为什么时间复杂度o（n）呢？原因是在于，每一个元素，都只会进入一次，删除一次？
// pop出去o（1）。
// add进去，每一个元素只可能被add一次，每一个元素只可能被移除一次，所以全局就是o（n）了。

class Solution {

    // public static class Node{
    //     int index;
    //     int value;
    // }
    // 似乎直接存储这个index就够了？


    // 假设，nums是7个，k = 3个，你应该输出？
    // 0，1，2；1，2，3；2，3，4；3，4，5；4，5，6
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];

        LinkedList<Integer> maxValueIndex = new LinkedList<>();

        // OK，开始构建这样的对象。
        // 逻辑上，就是说，把这个所有的nums中的元素，逐个加入到这数据结构中。
        // 之后的话，按需进行pop。
        int ansIndex = 0;
        for(int i = 0;i<nums.length;i++){
            // add
            // 都需要add
            add(maxValueIndex,nums,i);

            // remove if need
            if(i>(k-1)){
                // 需要删除。（i-k）这个元素。
                remove(maxValueIndex,(i-k));
            }

            // get max value if need

            if(i>=(k-1)){
                // 需要输出。
                ans[ansIndex++] = nums[maxValueIndex.get(0)];
            }
        }
        return ans;
        
    }

    public void add(List<Integer> maxValueIndex , int[] nums, int index){
        // 把第index个元素，压入这个队列。
        // 作为linkedlist，逻辑上，就是我们可以比较方便的删除。
        // 自然的，我们就是说从后向前删除。直到，我们遇到了就是说，一个比我们大的。
        // 在这个基础之上，我们就是说再add我们这个新的值。这样比较方便一些。
        // 不要用iterator，直接使用index我的理解。
        for(int i =maxValueIndex.size()-1; i>=0 ;i--){
            if(nums[maxValueIndex.get(i)] > nums[index]){
                break;
            }else{
                // 当前的这个值，属于就是说<=，对于这个max而言，没有作用了。
                // 这个元素应该删除，当然实际上他就是最后一个。问题不大。
                maxValueIndex.remove(i);
            }
        }
        // 处理完毕了。现在塞入。
        maxValueIndex.add(index);
    }

    public void remove(LinkedList<Integer> maxValueIndex, int index){
        // 逻辑上，我们只用判断第一个就足够了。
        // 因为只有可能就是，当前最大的就是我们的才有影响，否则的话，就是说，是其他的节点，这种时候，就没有影响了。
        if(maxValueIndex.getFirst() == index){
            maxValueIndex.removeFirst();
        }else{
            // do nothing;
        }
    }
}
