class Solution {



    // 考虑以i开头，并且必须要包含i的这个逻辑。
    // 为什么要包含i呢？因为，i是整个这个区间之中，最小的？如果有一个比他还小的。
    // 那么就变成了这个最小的吧？



    // 先不考虑这么多，只看【i，j】这个区间，他的最大矩形是什么？



    // s【i，j】 =  （j-i） * min（heights【k】）；

    // 是这个逻辑对吧？

    // 假设，现在右边这个边，比我们当前的这个最小的值，>=，怎么处理呢？
    // 往后推。没有问题。因为一定j往右边更大。
    // 如果说，这个右边的值，比我们的这个最小值小呢？
    // 我们没办法判断了。但是肯定这个逻辑改变了。
    // 这个时候，我们就是不好处理，因为后面的可能还会更大。因为更长了啊？


    // 重新考虑一下。
    // s【i】，我们就是说，这个是以i结尾的，这个最大的值？
    // 既然以i结尾了。

    // 那么，也就是似乎就是【0，i】的这个最小值，然后乘一下？
    // 这个也不对。似乎还有一个单调的特性才对。我的理解。
    // i -> j
    // 双指针，然后 i不动，j逐步扩大，再之后。i逐步收缩？
    // 一个线性的扫描？
    // 关键点，你每一次，至少要走一步吗？
    // 核心点，每次要直接排除掉就是说一个方向？
    // 可以做到吗？




    // 总归有一个最小值。然后要i+1 or j+1，这样就可以了？
    // 


    // 换一个思路。
    // 以i作为最小值，然后左右拓展？



    // 这样的话，就是排除了很多？



    // 两边，分别计算出来。
    // 向右 -》这个元素，然后就是说第一个比他绝对小的index。
    // 向左 -》这个元素，第一个比他绝对小的index。

    // 之后的话，s【i】（也就是包含了这个s【i】的最大的矩形？实际上就是前面的这个两个index的差距点。）


    public int largestRectangleArea(int[] heights) {
        int[] rightSmallerIndex = new int[heights.length];
        int[] leftSmallerIndex = new int[heights.length];
        // 设置为-1，标记就是说不存在这个？
        for(int i = 0;i< heights.length;i++){
            rightSmallerIndex[i] = -1;
            leftSmallerIndex[i] = -1;
        }



        // 增长栈。
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i = 0;i<heights.length;i++){
            if(stack.size()==0) {
                // 直接加入，没办法比较了。
                stack.add(i);
            }else{
                // 判断一下，当前的这个栈顶元素，是否就是比目前的小于等于呢？
                while(stack.size()!=0 && heights[stack.getLast()] > heights[i]){
                    // pop 出来，更新。
                    rightSmallerIndex[stack.getLast()] = i;
                    stack.removeLast();
                }
                stack.add(i);
            }
        }

        stack.clear();
        // 增长栈。

        for(int i = heights.length-1;i>=0;i--){
            if(stack.size()==0){
                stack.add(i);
            }else{
                while(stack.size()!=0 && heights[stack.getLast()] > heights[i]){
                    leftSmallerIndex[stack.getLast()] = i;
                    stack.removeLast();
                }
                stack.add(i);
            }
        }
        stack.clear();

        for(int i = 0;i<heights.length;i++){
            System.out.print(rightSmallerIndex[i]+" ");
        }
        System.out.println();

        for(int i = 0;i<heights.length;i++){
            System.out.print(leftSmallerIndex[i]+" ");
        }
        System.out.println();




        // 现在，两侧都已经就绪了。开始处理。
        int ans = 0;
        for(int i = 0;i<heights.length;i++){
            // 以第i个为最小点的。
            int startIndex = leftSmallerIndex[i]==-1?0:(leftSmallerIndex[i]+1);
            int endIndex = rightSmallerIndex[i]==-1?heights.length-1:  (rightSmallerIndex[i]-1);

            ans = Math.max(ans,(endIndex-startIndex+1) * heights[i]);
        }
        return ans;
    }
}
