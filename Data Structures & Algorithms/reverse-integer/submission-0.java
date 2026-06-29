// Return x after reversing each of its digits. After reversing, 
// if x goes outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0 instead.

// Solve the problem without using integers that are outside the signed 32-bit integer range.


class Solution {
    public int reverse(int x) {
        // 逻辑上，今天的新的认知点，就是说，溢出是比较特别的。
        // 溢出的逻辑，就是正+正 = 负。
        // 负+负 = 正。则溢出了。
        // 正常的，正+负永远不会溢出。
        // 正数，比较简单我理解？就是一个数 * 10 + 当前数？
        // 注意这里，*10，怎么识别溢出？逻辑上，可以采用10个加法的吧？虽然取巧了，但是也还可以？
        // 那么对于负数呢？
        // 一个数，最后一位，负数，这个没太大问题？
        // 然后就是再进行计算。
        // 问题在于，怎么计算这个最后一位的余数？
        // 一个想法，看一下，到底是怎么计算余数的？
        // 要知道，你应该是不能够直接*-1然后处理的？
        // 最好的办法，模拟一下。
        // OK，模拟后发现，java的负数的%，看上去，并不会转化为严格的数学上的取mod。而是感觉是保留了负数的值？
        // 逻辑上，这样更好了。

        if(x==0) return 0;

        if(x > 0){
            int result = 0;
            while(x!=0){
                int tmp = result;
                for(int i =0;i<9;i++){
                    result+=tmp;
                    if(result < 0){
                        return 0;
                    }
                }
                result =  result + x % 10;
                if(result < 0){
                    return 0;
                }
                x = x /10;
            }
            return result;
        }else{
            int result = 0;
            while(x!=0){
                int tmp = result;
                for(int i =0;i<9;i++){
                    result= result + tmp;
                    if(result > 0){
                        return 0;
                    }
                }
                result =  result + x % 10;
                if(result > 0){
                    return 0;
                }
                x = x /10;
            }
            return result;
        }   
    }
}
